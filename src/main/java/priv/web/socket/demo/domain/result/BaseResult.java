package priv.web.socket.demo.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import priv.web.socket.demo.domain.ErrorCodeEnum;

/**
 * @author lyqlbst
 * @description rest统一返回参数
 * @email 1098387108@qq.com
 * @date 2019/11/7 4:03 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class BaseResult {
    /**
     * 错误码
     */
    private int code = ErrorCodeEnum.SUCCESS.getCode();
    /**
     * 错误信息
     */
    private String message = ErrorCodeEnum.SUCCESS.getMessage();
}
