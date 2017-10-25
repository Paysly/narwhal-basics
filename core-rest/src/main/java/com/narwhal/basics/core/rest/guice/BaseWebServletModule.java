package com.narwhal.basics.core.rest.guice;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.inject.Provides;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import com.googlecode.objectify.impl.translate.opt.BigDecimalLongTranslatorFactory;
import com.narwhal.basics.core.rest.daos.OfyService;
import com.narwhal.basics.core.rest.exceptions.api.ApiExceptionMapper;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.inject.Singleton;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.*;
import java.util.logging.Level;

public abstract class BaseWebServletModule extends GuiceSystemServiceServletModule {

    private List<SubModule> servletModuleList;

    public BaseWebServletModule(SubModule... servletModuleList) {
        this.servletModuleList = Arrays.asList(servletModuleList);
    }

    @Override
    protected void configureServlets() {
        filter("/*").through(AsyncCacheFilter.class);
        filter("/*").through(ObjectifyFilter.class);
        //
        bind(ObjectifyFilter.class).in(Singleton.class);
        bind(AsyncCacheFilter.class).in(Singleton.class);
        //
        ObjectifyService.factory().getTranslators().add(new BigDecimalLongTranslatorFactory());
        //
        if (servletModuleList != null) {
            for (SubModule submodule : servletModuleList) {
                for (Class clazz : submodule.objectifyClasses()) {
                    OfyService.factory().register(clazz);
                }
                //
                for (Class clazz : submodule.apiClasses()) {
                    bind(clazz);
                }
                //
                for (Class clazz : submodule.taskClasses()) {
                    serve(URLPaths.findTerminatedPath(clazz)).with(clazz);
                }
                //
                for (Class clazz : submodule.cronClasses()) {
                    serve(URLPaths.findTerminatedPath(clazz)).with(clazz);
                }
                //
                doAdditionalSubmoduleBinding(submodule);
            }
        }
        //
        configureCustomServlets();
        // APIs
        bind(bindExceptionMapper());
        //
        // bind Jersey resources
        Map<String, String> params = new HashMap<String, String>();
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.sun.jersey.multipart.impl.MultiPartReaderServerSide");
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.sun.jersey.core.impl.provider.entity.MimeMultipartProvider");
        //
        serve("/api/*").with(GuiceContainer.class, params);
    }

    @Provides
    @Singleton
    public BlobstoreService provideBlobstoreService() {
        return BlobstoreServiceFactory.getBlobstoreService();
    }

    @Provides
    @Singleton
    public ImagesService provideImagesService() {
        return ImagesServiceFactory.getImagesService();
    }

    @Provides
    @Singleton
    public URLFetchService provideURLFetchService() {
        return URLFetchServiceFactory.getURLFetchService();
    }

    @Provides
    @Singleton
    public MemcacheService provideMemcacheService() {
        MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();
        memcacheService.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        return memcacheService;
    }

    @Provides
    @Singleton
    public AsyncMemcacheService provideAsyncMemcacheService() {
        AsyncMemcacheService asyncMemcacheService = MemcacheServiceFactory.getAsyncMemcacheService();
        asyncMemcacheService.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        return asyncMemcacheService;
    }

    @Provides
    @Singleton
    public GcsService provideGcsService() {
        GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
                .initialRetryDelayMillis(10)
                .retryMaxAttempts(10)
                .totalRetryPeriodMillis(15000)
                .build()
        );
        return gcsService;
    }

    @Provides
    @Singleton
    public VelocityEngine getVelocityEngine() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        Properties velocityProperties = new Properties();
        velocityProperties.put(RuntimeConstants.RESOURCE_LOADER, "class");
        velocityProperties.put("class.resource.loader.description", "Velocity Classpath Resource Loader");
        velocityProperties.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init(velocityProperties);
        return ve;
    }

    @Provides
    @Singleton
    public abstract MicroservicesContext provideMicroserviceContext();

    protected abstract void doAdditionalSubmoduleBinding(SubModule submodule);

    protected abstract void configureCustomServlets();

    protected Class<? extends ExceptionMapper> bindExceptionMapper() {
        return ApiExceptionMapper.class;
    }
}
