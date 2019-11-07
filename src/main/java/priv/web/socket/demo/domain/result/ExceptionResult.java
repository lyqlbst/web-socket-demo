package priv.web.socket.demo.domain.result;

import lombok.AllArgsConstructor;
import priv.web.socket.demo.domain.ErrorCodeEnum;

/**
 * @author lyqlbst
 * @description 返回错误的实体类
 * @email 1098387108@qq.com
 * @date 2019/11/7 4:05 PM
 */
@AllArgsConstructor
public final class ExceptionResult extends BaseResult {
    private ExceptionResult(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    private ExceptionResult(int code, String message) {
        super(code, message);
    }

    public static ExceptionResult of(ErrorCodeEnum errorCodeEnum) {
        return new ExceptionResult(errorCodeEnum);
    }

    public static ExceptionResult of(int code, String message) {
        return new ExceptionResult(code, message);
    }
}
