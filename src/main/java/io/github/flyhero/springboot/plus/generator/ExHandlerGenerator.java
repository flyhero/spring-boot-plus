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
public class ExHandlerGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectInfo().getName() + "/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "exception/" +
                "GlobalExceptionHandler.java";
    }

    @Override
    public String getTemplate() {
        return "api/GlobalExceptionHandler.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName());
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseExceptionHandler();
    }
}
