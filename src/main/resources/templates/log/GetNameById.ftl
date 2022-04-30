package ${packageName};

import io.github.flyhero.easylog.function.ICustomFunction;
import org.springframework.stereotype.Component;

/**
 *  自定义函数，在目标方法执行前调用
 */
@Component
public class GetNameById implements ICustomFunction {
    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return "getNameById";
    }

    @Override
    public String apply(String id) {
        // userService.getById(Long.valueOf(id));
        return "name";
    }
}
