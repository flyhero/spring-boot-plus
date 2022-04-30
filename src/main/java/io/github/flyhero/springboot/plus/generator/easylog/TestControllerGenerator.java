package io.github.flyhero.springboot.plus.generator.easylog;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.generator.AbstractGenerator;
import org.springframework.stereotype.Component;

@Component
public class TestControllerGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "controller/" +
                "TestController.java";
    }

    @Override
    public String getTemplate() {
        return "log/TestController.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        plusConfig.getDependencies().add("easy-log");
        return ImmutableMap.of("packageName", plusConfig.getPackageName() + ".controller");
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseEasyLog();
    }
}
