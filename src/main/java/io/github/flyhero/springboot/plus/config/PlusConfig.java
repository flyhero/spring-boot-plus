package io.github.flyhero.springboot.plus.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import lombok.Data;

import java.io.File;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/3/20 15:54
 */
@Data
public class PlusConfig {

    @Data
    public static class DataSource {
        private String username;
        private String password;
        private String url;
        private String[] tableNames;
    }

    private DataSource dataSource;

    // 总项目路径 (生成代码的路径)
    private String projectPath = "";
    // 代码路径
    private String codePath = "src/main/java/";
    //资源路径
    private String resourcesPath = "src/main/resources/";
    //包名
    private String packagePath = "com.github.project";

    private boolean useMaven = true;

    //覆盖文件
    private boolean fileOverride = true;

    //mybatis-plus生成器
    private FastAutoGenerator fastAutoGenerator;

    // 1.properties 2.yaml
    private String appConfigFileSuffix = "properties";

    private boolean useMybatisPlus;

    private boolean useCors;

    private boolean useJacksonConfig;

    /**
     * 全局异常统一处理
     */
    private boolean useExceptionHandler;

    // 1.logback 2.log4j2
    private int logWay = 1;


    /**
     * 获取代码完整路径
     */
    public String getCodePath() {
        String path = projectPath + File.separator + codePath + File.separator;
        return new File(path).getAbsolutePath() + File.separator;
    }

    // 根代码包完整路径
    public String getCodePackagePath() {
        String path = projectPath + File.separator + codePath + File.separator + packagePath.replace(".", File.separator);
        return new File(path).getAbsolutePath() + File.separator;
    }

    /**
     * 获取资源完整路径
     */
    public String getResourcesPath() {
        String path = projectPath + File.separator + resourcesPath + File.separator;
        return new File(path).getAbsolutePath() + File.separator;
    }


    //==========================get and set==============================

    public String getProjectPath() {
        return projectPath;
    }

    public PlusConfig setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }


    public PlusConfig setCodePath(String codePath) {
        this.codePath = codePath;
        return this;
    }

    public PlusConfig setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
        return this;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public PlusConfig setPackagePath(String packagePath) {
        this.packagePath = packagePath;
        return this;
    }

    public FastAutoGenerator getFastAutoGenerator() {
        return fastAutoGenerator;
    }

    public PlusConfig setFastAutoGenerator(FastAutoGenerator fastAutoGenerator) {
        this.fastAutoGenerator = fastAutoGenerator;
        return this;
    }

    public String getAppConfigFileSuffix() {
        return appConfigFileSuffix;
    }

    public PlusConfig setAppConfigFileSuffix(String appConfigFileSuffix) {
        this.appConfigFileSuffix = appConfigFileSuffix;
        return this;
    }

    public boolean isUseMybatisPlus() {
        return useMybatisPlus;
    }

    public PlusConfig setUseMybatisPlus(boolean useMybatisPlus) {
        this.useMybatisPlus = useMybatisPlus;
        return this;
    }

    public boolean isUseCors() {
        return useCors;
    }

    public PlusConfig setUseCors(boolean useCors) {
        this.useCors = useCors;
        return this;
    }

    public boolean isUseJacksonConfig() {
        return useJacksonConfig;
    }

    public PlusConfig setUseJacksonConfig(boolean useJacksonConfig) {
        this.useJacksonConfig = useJacksonConfig;
        return this;
    }

    public int getLogWay() {
        return logWay;
    }

    public PlusConfig setLogWay(int logWay) {
        this.logWay = logWay;
        return this;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public PlusConfig setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public PlusConfig setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
        return this;
    }

}
