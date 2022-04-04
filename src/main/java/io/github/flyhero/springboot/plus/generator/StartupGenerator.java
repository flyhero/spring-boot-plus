package io.github.flyhero.springboot.plus.generator;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 21:57
 */
@Component
public class StartupGenerator extends AbstractGenerator {

    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        String applicationJavaName = toCamel(plusConfig.getProjectInfo().getArtifactId());
        return plusConfig.getOutputDir() + plusConfig.getProjectInfo().getName() + "/" + PlusConfig.codePath
                + plusConfig.getPackagePath() + applicationJavaName + ".java";
    }

    @Override
    public String getTemplate() {
        return "applications/startup.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        String applicationJavaName = toCamel(plusConfig.getProjectInfo().getArtifactId());
        return ImmutableMap.of("packageName", plusConfig.getPackageName(), "className", applicationJavaName);
    }

    // 驼峰命名
    private String toCamel(String artifactId) {
        String camel = artifactId.contains("_") ? CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, artifactId)
                : CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, artifactId);
        return camel.substring(0, 1).toUpperCase() + camel.substring(1) + "Application";
    }
}
