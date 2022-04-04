package io.github.flyhero.springboot.plus;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import io.github.flyhero.springboot.plus.generator.AbstractGenerator;
import io.github.flyhero.springboot.plus.generator.GeneratorRegister;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目生成器
 *
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/4/2 22:20
 */
@Component
public class ProjectGenerator implements IProjectGenerator {

    @Override
    public void doGenerate(PlusConfig plusConfig) {
        List<AbstractGenerator> generators = GeneratorRegister.getGenerators();
        for (AbstractGenerator generator : generators) {
            generator.process(plusConfig);
        }
    }
}
