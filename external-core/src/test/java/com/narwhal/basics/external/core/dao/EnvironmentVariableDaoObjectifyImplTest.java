package com.narwhal.basics.external.core.dao;


import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.testutils.BaseDaoTest;
import com.narwhal.basics.external.core.guice.ApplicationSettingsModule;
import com.narwhal.basics.external.core.model.EnvironmentVariable;

public class EnvironmentVariableDaoObjectifyImplTest extends BaseDaoTest<EnvironmentVariable, EnvironmentVariableDao> {


    public EnvironmentVariableDaoObjectifyImplTest() {
        super(EnvironmentVariable.class, EnvironmentVariableDao.class);
    }


    @Override
    protected SubModule getGuiceSubModule() {
        return new ApplicationSettingsModule();
    }

    @Override
    protected EnvironmentVariable newInstance() {
        EnvironmentVariable variable = new EnvironmentVariable();
        variable.init("app-id");
        variable.setSort(3);
        return variable;
    }
}
