package priv.web.socket.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lyqlbst
 * @description 主页
 * @email 1098387108@qq.com
 * @date 2019/11/7 5:00 PM
 */
@Controller
public class IndexController {
    @GetMapping({"/", "/index", "/home"})
    public String index() {
        return "/index";
    }
}
