package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session){
        System.out.println(adminService);
        try {
            adminService.login(admin);
            session.setAttribute("admin",admin);
            return "redirect:/main/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        try {
            session.removeAttribute("admin");
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index.jsp";
        }
    }
}
