package ${config.packagePath};

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.math.BigInteger;

@Configuration
public class JacksonConfig implements InitializingBean{

    @Resource
    ObjectMapper objectMapper;

    private SimpleModule getSimpleModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return simpleModule;
    }
    
    @Override
    public void afterPropertiesSet() {
        SimpleModule simpleModule = getSimpleModule();
        objectMapper.registerModule(simpleModule);
    }
}
