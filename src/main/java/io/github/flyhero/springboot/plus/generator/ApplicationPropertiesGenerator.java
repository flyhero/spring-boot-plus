package io.github.flyhero.springboot.plus.generator;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Component
public class ApplicationPropertiesGenerator extends AbstractGenerator {
    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                + PlusConfig.resourcesPath + "application.properties";
    }

    @Override
    public String getTemplate() {
        return "applications/application.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        Map<String, Object> map = new HashMap<>();
        if (Objects.nonNull(plusConfig.getDataSourceConfig())) {
            map = EntityUtils.entityToMap(plusConfig.getDataSourceConfig());
            map.put("hasDbConfig", true);
        } else {
            map.put("hasDbConfig", false);
        }
        map.put("name", plusConfig.getProjectConfig().getName());
        map.put("useMybatisPlus", plusConfig.isUseMybatisPlus());
        map.put("dataSourceType", plusConfig.getDataSourceConfig().getType());
        map.put("logWay", plusConfig.getLogWay());
        return map;
    }
}
