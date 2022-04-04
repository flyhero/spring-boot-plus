package io.github.flyhero.springboot.plus.generator;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/3 14:04
 */
@Component
public class IgnoreGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectInfo().getArtifactId() + File.separator +
                ".gitignore";
    }

    @Override
    public String getTemplate() {
        return "applications/gitignore.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return plusConfig;
    }

}
