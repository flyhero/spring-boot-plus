
1. CorFilter / WebMvConfigurer / @CrossOrigin 需要 SpringMVC 4.2以上版本才支持，对应springBoot 1.3版本以上
2. GlobalCorsFilterConfig 和 GlobalCorsWebMvcConfig 两种方式属于全局 CORS 配置，@CrossOrigin 属于局部 CORS配置。
        如果使用了局部跨域是会覆盖全局跨域的规则，所以可以通过 @CrossOrigin 注解来进行细粒度更高的跨域资源控制。