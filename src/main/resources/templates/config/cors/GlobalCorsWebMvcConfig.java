package io.github.flyhero.springboot.plus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class GlobalCorsWebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册 cors 拦截器
     * 注意 springboot 2.2 （spring 5.2）版本之下慎用：低版本会将cors拦截器添加到所有拦截器中最后一个，如果前面出错，是走不到cors的。
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new TestInterceptor())
                //拦截所有路径
                .addPathPatterns("/**")
                //除什么路径外
                .excludePathPatterns("/login")
                //springboot2+之后需要将静态资源文件的访问路径排除
                .excludePathPatterns( "/js/**", "/css/**", "/images/**");
    }

    /**
     * 测试拦截器：常用于 用户登录状态的拦截，日志的拦截等
     */
    public static class TestInterceptor implements HandlerInterceptor {

    }
}
