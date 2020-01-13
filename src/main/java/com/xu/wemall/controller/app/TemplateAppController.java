package com.xu.wemall.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by xsw on 2017/10/12.
 */
@ApiIgnore
@Controller
@RequestMapping(("/app/template"))
public class TemplateAppController {

    @RequestMapping("/user")
    public String chatClient(Model model){

        return "wx_userinfo";
    }

}
