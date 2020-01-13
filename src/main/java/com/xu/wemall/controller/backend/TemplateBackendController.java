package com.xu.wemall.controller.backend;

import com.xu.wemall.entry.WxUser;
import com.xu.wemall.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by xsw on 2017/10/12.
 */
@Controller
@RequestMapping(("/template"))
public class TemplateBackendController {

    @Autowired
    private IWxUserService iWxUserService;

    @RequestMapping("/chat_client")
    public String chatClient() {

        return "/chat_client";
    }

    @RequestMapping("/chat_room")
    public String chatRoom() {
        return "/chat_room";
    }

    @RequestMapping("/wxUserInfo")
    public String wxUserInfo(Model model) {

        WxUser wxUser = iWxUserService.getById(1);
        model.addAttribute("user", wxUser);
        model.addAttribute("hello", "hello");
        return "wx_userinfo";
    }

    @RequestMapping("/wxUserList")
    public String showUser(Model model){
        List<WxUser> list = iWxUserService.list();
        model.addAttribute("list",list);
        return "wx_user_list";
    }

}
