package io.github.flyhero.springboot.plus.model;

import lombok.Data;

import java.util.List;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/8 18:28
 */
@Data
public class ProjectInfoReq {

    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;
    private String packageName;

    private String bootVersion;

    private String language;
    private String javaVersion;
    private String packaging;

    private List<String> dependencies;
}
