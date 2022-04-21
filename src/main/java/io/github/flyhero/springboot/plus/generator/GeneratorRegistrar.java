package io.github.flyhero.springboot.plus.generator;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 生成器注册器
 *
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/2 11:39
 */
public final class GeneratorRegistrar {

    private static final List<AbstractGenerator> GENERATORS = new ArrayList<>();

    public static void register(AbstractGenerator generator) {
        GENERATORS.add(generator);
    }

    // 每次获取的时候都排序，性能不是很好
    public static List<AbstractGenerator> getGenerators() {
        return GENERATORS.stream().sorted(Comparator.comparing(v -> {
            Order order = v.getClass().getAnnotation(Order.class);
            if (Objects.isNull(order)){
                return Ordered.HIGHEST_PRECEDENCE;
            }
           return order.value();
        })).collect(Collectors.toList());
    }
}
