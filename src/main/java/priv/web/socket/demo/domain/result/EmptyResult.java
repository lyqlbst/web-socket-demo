package priv.web.socket.demo.domain.result;

/**
 * @author lyqlbst
 * @description 一个空的返回值
 * @email 1098387108@qq.com
 * @date 2019/11/7 4:45 PM
 */
public final class EmptyResult extends BaseResult {
    private static final EmptyResult INSTANCE = new EmptyResult();

    public static EmptyResult getInstance() {
        return new EmptyResult();
    }
}
