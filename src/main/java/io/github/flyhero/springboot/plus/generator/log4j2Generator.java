package io.github.flyhero.springboot.plus.generator;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Component
public class log4j2Generator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectInfo().getName() + "/"
                + PlusConfig.resourcesPath + "log4j2-spring.xml";
    }

    @Override
    public String getTemplate() {
        return "log/log4j2-spring.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("name", plusConfig.getProjectInfo().getName());
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return "log4j2".equals(plusConfig.getLogWay());
    }
}
