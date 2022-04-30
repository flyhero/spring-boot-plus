package ${packageName};


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<#if hasDbConfig>
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("${packageName}.mapper")
</#if>
@SpringBootApplication
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }

}