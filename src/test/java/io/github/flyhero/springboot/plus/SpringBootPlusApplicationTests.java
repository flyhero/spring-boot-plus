package io.github.flyhero.springboot.plus;

import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootPlusApplicationTests {

    @Autowired
    private IProjectGenerator projectGenerator;

    @Test
    void contextLoads() {
        PlusConfig.ProjectInfo projectInfo = new PlusConfig.ProjectInfo();
        projectInfo.setGroupId("com.github.flyhero");
        projectInfo.setArtifactId("demo");
        projectInfo.setName("demo");
        projectInfo.setDescription("desc");

        PlusConfig plusConfig = new PlusConfig();
        plusConfig.setUseCors(true);
        plusConfig.setUseMybatisPlus(true);
        plusConfig.setUseExceptionHandler(true);
        plusConfig.setProjectInfo(projectInfo);

        projectGenerator.doGenerate(plusConfig);
    }

}
