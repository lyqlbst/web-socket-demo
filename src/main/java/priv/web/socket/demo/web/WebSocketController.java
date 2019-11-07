package priv.web.socket.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.web.socket.demo.domain.result.DataResult;
import priv.web.socket.demo.domain.result.EmptyResult;
import priv.web.socket.demo.service.WSService;

/**
 * @author lyqlbst
 * @description web-socket rest api
 * @email 1098387108@qq.com
 * @date 2019/11/7 3:50 PM
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {
    private final WSService wsService;

    @Autowired
    public WebSocketController(WSService wsService) {
        this.wsService = wsService;
    }

    @PostMapping("/clients/{message}")
    public EmptyResult massMessage(@PathVariable("message") String message) {
        wsService.massMessage(message);
        return EmptyResult.getInstance();
    }

    @PostMapping("/clients/{sessionId}/{message}")
    public EmptyResult sendMessage(@PathVariable("sessionId") String sessionId, @PathVariable("message") String message) {
        wsService.sendMessage(sessionId, message);
        return EmptyResult.getInstance();
    }
}
