package io.github.flyhero.springboot.plus.config;

import io.github.flyhero.springboot.plus.model.DependencyData;
import io.github.flyhero.springboot.plus.util.FileUtils;
import io.github.flyhero.springboot.plus.util.JsonUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/21 17:53
 */
@Component
public class DataLoader implements ApplicationRunner {

    private List<DependencyData> dependencyDatas;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String data = FileUtils.readResourcesToString("/data/dependency_data.json");
        try {
            dependencyDatas = JsonUtil.json2List(data, DependencyData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DependencyData> getDependencies(){
        return this.dependencyDatas;
    }
}
