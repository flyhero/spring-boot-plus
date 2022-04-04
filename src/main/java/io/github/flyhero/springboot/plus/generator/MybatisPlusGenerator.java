package io.github.flyhero.springboot.plus.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/4 15:22
 */
@Component
public class MybatisPlusGenerator extends AbstractGenerator {

    @Override
    public boolean isCreate(PlusConfig plusConfig) {
        return plusConfig.isUseMybatisPlus();
    }

    @Override
    public boolean isCustomProcess() {
        return true;
    }

    @Override
    public Object customProcessFun(PlusConfig plusConfig) {
        FastAutoGenerator.create(plusConfig.getDataSourceConfig().getUrl(), plusConfig.getDataSourceConfig().getUsername(), plusConfig.getDataSourceConfig().getPassword())
                .globalConfig(builder -> {
                    builder.outputDir(plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/"
                            + PlusConfig.codePath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(plusConfig.getPackageName()) // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    plusConfig.getOutputDir() + plusConfig.getProjectConfig().getName() + "/" + PlusConfig.resourcesPath + "mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(plusConfig.getDataSourceConfig().getTableNames()) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .controllerBuilder()
                            .enableRestStyle() //Rest风格
                            .entityBuilder()
                            .enableLombok()
                            .enableColumnConstant();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        return plusConfig.getDataSourceConfig().getTableNames();
    }

    @Override
    public String getFullFilePath(PlusConfig plusConfig) {
        return null;
    }

    @Override
    public String getTemplate() {
        return null;
    }

    @Override
    public Object getDataModel(PlusConfig plusConfig) {
        return null;
    }
}
