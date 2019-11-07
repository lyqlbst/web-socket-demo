package priv.web.socket.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.web.socket.demo.domain.CheckedException;
import priv.web.socket.demo.domain.ErrorCodeEnum;
import priv.web.socket.demo.domain.pojo.MessagePOJO;
import priv.web.socket.demo.service.WSService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lyqlbst
 * @description 实现ws的方法调用
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:25 PM
 */
@Slf4j
@Service
@ServerEndpoint("/ws/msg")
public class WSServiceImpl implements WSService {
    // 保存所有的与客户端对应的session对象
    private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    @Override
    public void massMessage(String message) {
        log.info("给所有客户端发送消息...");

        for (Session session : SESSIONS.values()) {
            sendMessage(session, message);
        }
    }

    @Override
    public void sendMessage(String sessionId, String message) {
        boolean sessionExists = SESSIONS.containsKey(sessionId);
        if (!sessionExists) {
            throw CheckedException.of(ErrorCodeEnum.SESSION_NOT_EXISTS);
        }

        Session session = SESSIONS.get(sessionId);
        sendMessage(session, message);
    }

    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.put(session.getId(), session);

        log.info("有新的连接创建，sessionId: {}，当前连接数: {}", session.getId(), SESSIONS.size());
        sendMessage(session, "连接成功");
    }

    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session.getId());

        log.info("有需要关闭的连接，sessionId: {}，当前连接数: {}", session.getId(), SESSIONS.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端的消息: {}", message);
        sendMessage(session, "已收到消息");
    }

    @OnError
    public void onError(Session session, Throwable e) {
        log.error("session: {} 发生错误!", session.getId(), e);
    }

    /**
     * 发送消息
     *
     * @param session 客服端对应的session对象
     * @param message 要发送的消息
     */
    private void sendMessage(Session session, String message) {
        MessagePOJO responseMessage = MessagePOJO.of(session, message);
        try {
            session.getBasicRemote().sendText(responseMessage.toString());
        } catch (IOException e) {
            log.error("给客户端发送消息错误!", e);
        }
    }
}
