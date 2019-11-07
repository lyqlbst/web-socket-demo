package priv.web.socket.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lyqlbst
 * @description 逻辑异常
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:57 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class CheckedException extends RuntimeException {
    private final int code;
    private final String message;

    private CheckedException(ErrorCodeEnum e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public static CheckedException of(ErrorCodeEnum errorCodeEnum) {
        return new CheckedException(errorCodeEnum);
    }
}
