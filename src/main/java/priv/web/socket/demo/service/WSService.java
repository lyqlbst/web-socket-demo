package priv.web.socket.demo.service;

import javax.websocket.Session;

/**
 * @author lyqlbst
 * @description web-socket统一接口
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:21 PM
 */
public interface WSService {
    /**
     * 连接建立成功后调用的方法
     *
     * @param session 客服端对应的session对象
     */
    void onOpen(Session session);

    /**
     * 连接关闭后调用的方法
     *
     * @param session 客服端对应的session对象
     */
    void onClose(Session session);

    /**
     * 收到客户端的消息后调用的方法
     *
     * @param message 客户端发送的消息
     * @param session 客服端对应的session对象
     */
    void onMessage(String message, Session session);

    /**
     * 抛出异常后调用的方法
     *
     * @param session 客服端对应的session对象
     * @param e       抛出的异常
     */
    void onError(Session session, Throwable e);

    /**
     * 给所有客户端群发消息
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
