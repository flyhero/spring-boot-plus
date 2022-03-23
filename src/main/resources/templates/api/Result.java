package {api.packagePath};

import lombok.Data;


@Data
public class Result<T> {
    private int code = 200;
    private String msg;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>();
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(int code, String msg){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(Errors errors){
        Result<T> result = new Result<>();
        result.setCode(errors.code());
        result.setMsg(errors.msg());
        return result;
    }
}
