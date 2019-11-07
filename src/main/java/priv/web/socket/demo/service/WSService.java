package priv.web.socket.demo.service;

/**
 * @author lyqlbst
 * @description web-socket统一接口
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:21 PM
 */
public interface WSService {
    /**
     * 给所有客户端群发消息
     *
     * @param message 要发送的消息
     */
    void massMessage(String message);

    /**
     * 给指定session发送消息
     *
     * @param sessionId session标识
     * @param message   要发送的消息
     */
    void sendMessage(String sessionId, String message);
}
