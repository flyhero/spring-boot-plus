package io.github.flyhero.springboot.plus.generator;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Component
public class WebMvcConfigGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "config/" +
                "GlobalWebMvcConfig.java";
    }

    @Override
    public String getTemplate() {
        return "config/GlobalWebMvcConfig.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName() + ".config",
                "useCors", plusConfig.isUseCors(), "useInterceptor", plusConfig.isUseInterceptor());
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseCors() || plusConfig.isUseInterceptor();
    }
}
