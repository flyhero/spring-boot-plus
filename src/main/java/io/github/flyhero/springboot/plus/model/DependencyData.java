package io.github.flyhero.springboot.plus.model;

import lombok.Data;

import java.util.List;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/21 17:59
 */
@Data
public class DependencyData {

    private String name;
    private List<Value> values;


    @Data
    public static class Value{
        private String id;
        private Dependency dependency;
    }
}
