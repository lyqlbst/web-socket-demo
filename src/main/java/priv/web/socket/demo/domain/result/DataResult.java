package priv.web.socket.demo.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lyqlbst
 * @description 返回一个对象
 * @email 1098387108@qq.com
 * @date 2019/11/7 4:22 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public final class DataResult<T> extends BaseResult{
    /**
     * 任意对象
     */
    private T data;

    public static<T> DataResult<T> of(T data){
        return new DataResult<>(data);
    }
}
