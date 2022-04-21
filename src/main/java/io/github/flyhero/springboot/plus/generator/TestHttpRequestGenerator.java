package io.github.flyhero.springboot.plus.generator;

import com.google.common.collect.ImmutableMap;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

@Component
public class TestHttpRequestGenerator extends AbstractGenerator {

    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.testPath + plusConfig.getPackagePath() + "HttpRequestTests.java";
    }

    @Override
    public String getTemplate() {
        return "test/HttpRequestTests.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName());
    }
}
