package ${packageName};

import io.github.flyhero.easylog.service.IOperatorService;
import org.springframework.stereotype.Service;

/**
 * 租户和操作者
 */
@Service
public class OperatorGetService implements IOperatorService {
    @Override
    public String getOperator() {
        return "operator";
    }

    @Override
    public String getTenant() {
        return "company";
    }
}
