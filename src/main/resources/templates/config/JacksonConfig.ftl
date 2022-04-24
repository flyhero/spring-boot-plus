package ${packageName};

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
<if useXss>
import org.apache.commons.text.StringEscapeUtils;
</if>

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig implements InitializingBean {

    @Resource
    ObjectMapper objectMapper;

    private SimpleModule getSimpleModule() {

        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //长整形转字符串：解决长度过长，前端无法处理
/*      javaTimeModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
        javaTimeModule.addSerializer(Long.TYPE, ToStringSerializer.instance); */

        //处理 时间格式
        // javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // BigDecimal 保留两位小数
/*      javaTimeModule.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                DecimalFormat format = new DecimalFormat("#.##");
                gen.writeString(format.format(value));
            }
        }); */

    <if useXss>
        // 反序列化 xss过滤
        javaTimeModule.addDeserializer(String.class, new JsonDeserializer<String>() {
            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
                String value = jsonParser.getValueAsString();
                if (value != null) {
                    return StringEscapeUtils.escapeHtml4(value);
                }
                return value;
            }
        });
    </if>

        // 将null值转为""
/*      objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        }); */

        return javaTimeModule;
    }

    /**
     * 初始化完成后自动执行本方法
     */
    @Override
    public void afterPropertiesSet() {

        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的，省流量但前端如果解析不到可能会出错
        // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //有未知属性 要不要抛异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //确定解析器是否允许使用单引号(撇号，字符'\ ")
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        SimpleModule simpleModule = getSimpleModule();
        objectMapper.registerModule(simpleModule);
    }
}
