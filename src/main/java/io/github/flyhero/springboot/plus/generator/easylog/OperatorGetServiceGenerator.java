package io.github.flyhero.springboot.plus.generator.easylog;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.generator.AbstractGenerator;
import org.springframework.stereotype.Component;

@Component
public class OperatorGetServiceGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "service/easylog/" +
                "OperatorGetService.java";
    }

    @Override
    public String getTemplate() {
        return "log/OperatorGetService.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName() + ".service.easylog");
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseEasyLog();
    }
}
