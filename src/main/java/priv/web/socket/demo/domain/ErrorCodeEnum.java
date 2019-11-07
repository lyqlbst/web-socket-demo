package priv.web.socket.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lyqlbst
 * @description 错误码
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:57 PM
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /**
     * 成功返回码
     */
    SUCCESS(0, "成功"),

    /**
     * 服务器错误
     */
    ERROR(2, "服务器错误"),

    /**
     * rest参数错误
     */
    PARAM_ERROR(4, "参数错误"),

    /**
     * 发送消息时判断
     */
    SESSION_NOT_EXISTS(1_001,"session不存在");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
}
