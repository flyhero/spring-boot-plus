package ${packageName};

import io.github.flyhero.easylog.annotation.EasyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志 easy-log 测试控制类
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @EasyLog(module = "测试模块", type = "修改", content = "测试前置函数 {getNameById{#userId}} , 执行结果：{{#_result}}",
            fail = "操作失败：{{#_errMsg}}", condition = "{{#userId == 1}}")
    public String test(@RequestParam Long userId) {
        return "用户信息";
    }

}
