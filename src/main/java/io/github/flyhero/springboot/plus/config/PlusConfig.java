package io.github.flyhero.springboot.plus.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    private ProjectConfig projectConfig;

    private DataSourceConfig dataSourceConfig;

    private Set<String> dependencies = new HashSet<>();

    // jdbc  hib mybatis
    private String ormWay = "";

    private boolean useMybatisPlus;

    private boolean useCors;

    private boolean useInterceptor;

    private boolean useJacksonConfig;

    private boolean useXss;

    /**
     * 全局异常统一处理
     */
    private boolean useExceptionHandler;

    private boolean useI18n;

    // logback or log4j2
    private String logWay = "logback";

    /**
     * group + artifact
     * @return 包名称
     */
    public String getPackageName(){
        String lowerCase = this.projectConfig.getArtifactId().replace("-", ".").replace("_", ".").toLowerCase();
        return this.projectConfig.getGroupId() + "." + lowerCase;
    }
    /**
     * group + artifact
     * @return 包路径
     */
    public String getPackagePath(){
        return getPackageName().replace(".", "/") + "/";
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataSourceConfig {
        private String username;
        private String password;
        private String url;
        private String[] tableNames;
        //Hikari or Druid
        @Builder.Default
        private DataSourceType type = DataSourceType.Hikari;

        public enum DataSourceType{
            Hikari,Druid
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectConfig {
        private String groupId;
        private String artifactId;
        private String name;
        private String description;
        private String version;
        private String packageName;

        private String bootVersion;

        private String language;
        private String javaVersion;
        private String packaging;

    }

}
