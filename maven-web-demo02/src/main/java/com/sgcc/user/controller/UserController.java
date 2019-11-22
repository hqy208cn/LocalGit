package com.sgcc.user.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgcc.user.pojo.User;
import com.sgcc.user.service.UserService;
@Controller
public class UserController {
    /**
     * ʹ��@AutowiredҲ���ԣ�@AutowiredĬ�ϰ�����װ��
     * @Resource Ĭ�ϰ�����װ�䣬���Ҳ���������ƥ���bean�Żᰴ����װ�䡣
     */
    @Resource 
    private UserService userService;
    /**
     * ���Բ�ѯ
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showUser")
    public String testtoshowUser(@RequestParam(value = "id") Integer id, Model model) {
        System.out.println("id:" + id);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "showUser";
    }
    /**
     * ���Բ�ѯ
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/deleteUser")
    public String testdeleteUser(@RequestParam(value = "id") Integer id, Model model) {
        System.out.println("ɾ��id:" + id);
        userService.delete(id);        
        return "deletePage";
    }
    /**
     * �����������
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/insertUser")
    public String testinsertUser() {
        User user = new User();
        user.setUserName("������");
        user.setPassword("3232322");
        user.setAge(22);
        int count = userService.insert(user);
        System.out.println("����" + count + "�����ݳɹ�");
        return "showUser";
    }
}
