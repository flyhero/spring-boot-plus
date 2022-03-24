package io.github.flyhero.springboot.plus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.config.PlusConfigManager;
import io.github.flyhero.springboot.plus.util.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;


/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/3/20 16:34
 */
public class Generator {

    public void execute(PlusConfig plusConfig) {
        createMybatisPlusAutoGenerator(plusConfig);
        appendDependencies("spring-boot-starter-web", "templates/dependencies/web.xml", plusConfig);
        appendDependencies("mysql-connector-java", "templates/dependencies/mysql.xml", plusConfig);

        String app = FileUtils.readResourcesToString("templates/applications/db/datasource." + plusConfig.getAppConfigFileSuffix())
                .replace("${db.username}", plusConfig.getDataSource().getUsername())
                .replace("${db.password}", plusConfig.getDataSource().getPassword())
                .replace("${db.url}", plusConfig.getDataSource().getUrl());
        FileUtils.appendTextIfNotExist(plusConfig.getResourcesPath() + "application." + plusConfig.getAppConfigFileSuffix(), app);

        if (plusConfig.isUseCors()) {
            createConfigFile("templates/config/cors/", "GlobalCorsFilterConfig.java", plusConfig);
        }
        if (plusConfig.isUseJacksonConfig()) {
            createConfigFile("templates/config/", "JacksonConfig.java", plusConfig);
        }

        if (plusConfig.isUseMybatisPlus()) {
            doMybatisPlus(plusConfig);
        }

        createResourcesFile("templates/log/", plusConfig.getLogWay() == 1 ? "logback-spring.xml" : "log4j2-spring.xml", plusConfig);

    }

    private void createMybatisPlusAutoGenerator(PlusConfig plusConfig) {
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(plusConfig.getDataSource().getUrl(), plusConfig.getDataSource().getUsername(), plusConfig.getDataSource().getPassword())
                .globalConfig(builder -> {
                    builder.outputDir(PlusConfigManager.plusConfig.getCodePath()); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PlusConfigManager.plusConfig.getPackagePath()) // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PlusConfigManager.plusConfig.getResourcesPath() + File.separator + "mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(plusConfig.getDataSource().getTableNames()) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .enableColumnConstant();
                })
                .templateEngine(new FreemarkerTemplateEngine());

        plusConfig.setFastAutoGenerator(fastAutoGenerator);
    }

    private void doMybatisPlus(PlusConfig plusConfig) {
        appendDependencies("mybatis-plus-boot-starter", "templates/dependencies/mybatis-plus.xml", plusConfig);

        //生成 mybatis-plus
        plusConfig.getFastAutoGenerator().execute();

        createConfigFile("templates/config/", "MybatisPlusConfig.java", plusConfig);
        createConfigFile("templates/config/", "MybatisPlusMetaObjectHandler.java", plusConfig);

        String mybatisPlus = FileUtils.readResourcesToString("templates/applications/mybatis/mybatis-plus." + plusConfig.getAppConfigFileSuffix());
        FileUtils.appendTextIfNotExist(plusConfig.getResourcesPath() + "application." + plusConfig.getAppConfigFileSuffix(), mybatisPlus);
    }

    private void appendDependencies(String artifactId, String path, PlusConfig plusConfig) {
        Document document = null;
        try {
            document = Jsoup.parse(new File(plusConfig.getProjectPath() + "pom.xml"), StandardCharsets.UTF_8.name(), "", Parser.xmlParser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element dependencies = document.selectFirst("project").selectFirst("dependencies");
        if (dependencies.toString().contains(artifactId)) {
            return;
        }
        dependencies.append(FileUtils.readResourcesToString(path));
        System.out.println(document.toString());
        FileUtils.createNewFile(plusConfig.getProjectPath() + "pom.xml", document.toString());
    }

    /**
     * 创建配置文件
     */
    private void createConfigFile(String path, String fileName, PlusConfig plusConfig) {
        String replace1 = FileUtils.readResourcesToString(path + fileName)
                .replace("${config.packagePath}", plusConfig.getPackagePath() + ".config");
        String filePath1 = plusConfig.getCodePackagePath() + File.separator + "config" + File.separator + fileName;
        FileUtils.createNewFile(filePath1, replace1);
    }

    private void createResourcesFile(String path, String fileName, PlusConfig plusConfig) {
        String replace1 = FileUtils.readResourcesToString(path + fileName);
        String filePath1 = plusConfig.getResourcesPath() + fileName;
        FileUtils.createNewFile(filePath1, replace1);
    }
}
