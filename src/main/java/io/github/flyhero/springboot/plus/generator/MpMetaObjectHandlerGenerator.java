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
public class MpMetaObjectHandlerGenerator extends AbstractGenerator {
    @Override
    public File getFile(PlusConfig plusConfig) {
        return new File(plusConfig.getOutputDir() + plusConfig.getProjectInfo().getName()+"/"
                + PlusConfig.codePath + plusConfig.getPackagePath() + "config/",
                "MybatisPlusMetaObjectHandler.java");
    }

    @Override
    public String getTemplate() {
        return "config/MybatisPlusMetaObjectHandler.ftl";
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return ImmutableMap.of("packageName", plusConfig.getPackageName() + ".config");
    }

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseMybatisPlus();
    }
}
