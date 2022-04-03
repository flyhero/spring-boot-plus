package ${packageName};

import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ${className}Tests {

    @Autowired
    private IProjectGenerator projectGenerator;

    @Test
    void contextLoads() {

    }

}
