package io.github.flyhero.springboot.plus.generator;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Component
public class logbackGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectInfo().getName() + "/"
                + PlusConfig.resourcesPath + "logback-spring.xml";
    }

    @Override
    public String getTemplate() {
        return "log/logback-spring.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return plusConfig;
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return "logback".equals(plusConfig.getLogWay());
    }
}
