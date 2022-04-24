package io.github.flyhero.springboot.plus.generator;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

@Component
public class JacksonConfigGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "config/" +
                "JacksonConfig.java";
    }

    @Override
    public String getTemplate() {
        return "config/JacksonConfig.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName() + ".config",
                "useXss", plusConfig.isUseXss());
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseJacksonConfig() || plusConfig.isUseXss();
    }
}
