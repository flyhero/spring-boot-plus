package io.github.flyhero.springboot.plus;

import io.github.flyhero.springboot.plus.config.PlusConfig;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/2 22:18
 */
public interface IProjectGenerator {

    void doGenerate(PlusConfig plusConfig);
}
