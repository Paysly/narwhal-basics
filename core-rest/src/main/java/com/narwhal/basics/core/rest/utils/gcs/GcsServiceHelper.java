package com.narwhal.basics.core.rest.utils.gcs;

import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.tools.cloudstorage.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

/**
 * Created by tomyair on 9/28/17.
 */
@Singleton
public class GcsServiceHelper {

    /**
     * Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB
     */
    private static final int BUFFER_SIZE = 2 * 1024 * 1024;

    @Inject
    private GcsService gcsService;
    @Inject
    private ImagesService imagesService;
    @Inject
    private BlobstoreService blobstoreService;

    public void retrieveFile(String objectName, OutputStream fileOutput) {
        try {
            String bucket = AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
            GcsFilename fileName = new GcsFilename(bucket, objectName);
            GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, BUFFER_SIZE);
            copy(Channels.newInputStream(readChannel), fileOutput);
        } catch (IOException e) {
            throw new BadRequestException("File could not be read", e);
        }
    }

    public void deleteFile(String objectName) {
        try {
            String bucket = AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
            GcsFilename fileName = new GcsFilename(bucket, objectName);
            gcsService.delete(fileName);
        } catch (IOException e) {
            throw new BadRequestException("File could not be deleted", e);
        }
    }

    /**
     * Upload the file with the object name to our default bucket.
     *
     * @param objectName
     * @param file
     * @return
     */
    public void uploadFile(String objectName, InputStream file) {
        try {
            String bucket = AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
            //
            GcsFileOptions instance = GcsFileOptions.getDefaultInstance();
            GcsFilename fileName = new GcsFilename(bucket, objectName);
            GcsOutputChannel outputChannel = gcsService.createOrReplace(fileName, instance);
            copy(file, Channels.newOutputStream(outputChannel));
        } catch (IOException e) {
            throw new BadRequestException("File could not be upload", e);
        }
    }


    /**
     * Upload the image with the object name to our default bucket.
     * Return the string to serve the image using ImagesAPI. Remember to stop Serving when delete.
     *
     * @param objectName
     * @param file
     * @return
     */
    public String uploadImage(String objectName, InputStream file) {
        deleteServingUrl(objectName);
        //
        String bucket = AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
        uploadFile(objectName, file);
        ///gs/app_default_bucket/projects/race-capital/images/logo/circle
        BlobKey blobKey = blobstoreService.createGsBlobKey("/gs/" + bucket + "/" + objectName);
        return imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
    }

    public void deleteServingUrl(String objectName) {
        String bucket = AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName();
        BlobKey blobKey = blobstoreService.createGsBlobKey("/gs/" + bucket + "/" + objectName);
        imagesService.deleteServingUrl(blobKey);
    }

    public void deleteImage(String objectName) {
        deleteServingUrl(objectName);
        deleteFile(objectName);
    }

    /**
     * Transfer the data from the inputStream to the outputStream. Then close both streams.
     */
    private void copy(InputStream input, OutputStream output) throws IOException {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = input.read(buffer);
            while (bytesRead != -1) {
                output.write(buffer, 0, bytesRead);
                bytesRead = input.read(buffer);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}
