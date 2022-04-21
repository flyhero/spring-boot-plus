package io.github.flyhero.springboot.plus.model;

import lombok.Data;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/10 21:25
 */
@Data
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;
    private String optional;
    private String scope;
}
