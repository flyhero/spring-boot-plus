package io.github.flyhero.springboot.plus.generator;

import io.github.flyhero.springboot.plus.config.DataLoader;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.model.Dependency;
import io.github.flyhero.springboot.plus.model.DependencyData;
import io.github.flyhero.springboot.plus.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 22:34
 */
@Order
@Component
public class PomGenerator extends AbstractGenerator {

    @Autowired
    private DataLoader dataLoader;

    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return plusConfig.getOutputDir() + plusConfig.getProjectConfig().getArtifactId() + File.separator + "pom.xml";
    }

    @Override
    public String getTemplate() {
        return "applications/pom.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        Map<String, Object> map = EntityUtils.entityToMap(plusConfig.getProjectConfig());
        plusConfig.getDependencies().add(plusConfig.getDataSourceConfig().getType().name());
        Set<String> dependenciesStrs = plusConfig.getDependencies();
        List<Dependency> dependencies = new ArrayList<>();
        for (DependencyData dependency : dataLoader.getDependencies()) {
            for (DependencyData.Value value : dependency.getValues()) {
                if (dependenciesStrs.contains(value.getId())){
                    dependencies.add(value.getDependency());
                }
            }
        }
        map.put("dependencies", dependencies);
        return map;
    }

}
