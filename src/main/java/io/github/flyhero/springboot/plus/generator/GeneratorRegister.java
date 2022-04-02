package io.github.flyhero.springboot.plus.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成器注册器
 *
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/2 11:39
 */
public final class GeneratorRegister {

    private static final List<AbstractGenerator> GENERATORS = new ArrayList<>();

    public static void register(AbstractGenerator generator) {
        GENERATORS.add(generator);
    }

    public static List<AbstractGenerator> getGenerators() {
        return GENERATORS;
    }
}
