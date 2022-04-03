package io.github.flyhero.springboot.plus.generator;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Component
public class PomGenerator extends AbstractGenerator {
    @Override
    public File getFile(PlusConfig plusConfig) {
        return new File(plusConfig.getOutputDir() + plusConfig.getProjectInfo().getArtifactId(),
                "pom.xml");
    }

    @Override
    public String getTemplate() {
        return "applications/pom.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        Map<String, Object> map = EntityUtils.entityToMap(plusConfig.getProjectInfo());
        map.put("useMybatisPlus", plusConfig.isUseMybatisPlus());
        return map;
    }

}