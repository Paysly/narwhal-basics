package com.narwhal.basics.external.core.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.services.edit.BaseEditService;
import com.narwhal.basics.external.core.dao.EnvironmentVariableDao;
import com.narwhal.basics.external.core.model.EnvironmentVariable;

import java.io.Serializable;
import java.util.List;

@Singleton
public class EnvironmentVariableEditService extends BaseEditService<Serializable, EnvironmentVariable, EnvironmentVariable> {

    public static final int PAGE_SIZE = 1000;
    @Inject
    private EnvironmentVariableDao environmentVariableDao;


    @Override
    protected EnvironmentVariable initFromDTO(Serializable config, EnvironmentVariable dto) {
        EnvironmentVariable variable = new EnvironmentVariable();
        variable.init(dto.getId());
        variable.setValue(dto.getValue());
        return variable;
    }

    @Override
    protected List<EnvironmentVariable> getUpdatables(Serializable config) {
        return environmentVariableDao.getPageOfAll(EnvironmentVariable.class, PAGE_SIZE, null).getResultList();
    }

    @Override
    protected void saveAllEntities(List<EnvironmentVariable> modifiedUpdatables) {
        environmentVariableDao.saveAll(modifiedUpdatables);
    }

    @Override
    protected void deleteEntities(Serializable editDTO, List<EnvironmentVariable> toDeleteUpdatables) {
        environmentVariableDao.deleteEntities(toDeleteUpdatables);
    }
}
