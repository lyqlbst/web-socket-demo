package priv.web.socket.demo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;

/**
 * @author lyqlbst
 * @description 服务端返回消息
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:40 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePOJO {
    private Session session;
    private String message;

    /**
     * 构建一个message
     *
     * @param session 客服端对应的session对象
     * @param message 要发送的消息
     * @return 一个message
     */
    public static MessagePOJO of(Session session, String message) {
        return new MessagePOJO(session, message);
    }

    @Override
    public String toString() {
        return String.format("%s (From Server, Session Id = %s)", message, session.getId());
    }
}
