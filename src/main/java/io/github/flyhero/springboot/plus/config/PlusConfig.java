package io.github.flyhero.springboot.plus.config;

import lombok.Data;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/1 21:52
 */
@Data
public class PlusConfig {

    // 代码路径
    public static final String codePath = "src/main/java/";
    // 测试代码路径
    public static final String testPath = "src/test/java/";
    // 资源路径
    public static final String resourcesPath = "src/main/resources/";

    private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp/";;

    private ProjectInfo projectInfo;

    private DataSourceConfig dataSourceConfig;

    private boolean useMybatisPlus;

    private boolean useCors;

    private boolean useJacksonConfig;

    /**
     * 全局异常统一处理
     */
    private boolean useExceptionHandler;

    // logback or log4j2
    private String logWay = "logback";

    //Hikari or Druid
    private String dataSource = "Hikari";

    /**
     * group + artifact
     * @return 包名称
     */
    public String getPackageName(){
        return this.projectInfo.getGroupId() + "." +this.projectInfo.getArtifactId();
    }
    /**
     * group + artifact
     * @return 包路径
     */
    public String getPackagePath(){
        return getPackageName().replace(".", "/") + "/";
    }

    @Data
    public static class DataSourceConfig {
        private String username;
        private String password;
        private String url;
        private String[] tableNames;
    }

    @Data
    public static class ProjectInfo {
        private String groupId;
        private String artifactId;
        private String name;
        private String description;
    }


}
