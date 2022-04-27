package ${packageName};

/**
 * 错误码枚举类
 */
public enum ErrorEnum implements Errors{
    OK(200, "ok"),
    ERROR(500, "error"),
    ;

    private final int code;
    private final String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }
}
