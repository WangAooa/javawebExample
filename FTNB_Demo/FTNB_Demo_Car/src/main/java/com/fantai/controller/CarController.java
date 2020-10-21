package com.fantai.controller;

import com.fantai.dao.CarInfoMapper;
import com.fantai.entity.CarInfo;
import com.fantai.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiaoy_000 on 2017/9/12.
 */
@Controller
@RequestMapping("/car")
public class CarController {

    public static final int NFC_ERROR = 1;
    public static final int NFC_SUCCESS = 0;

    @Resource
    private CarInfoMapper carInfoMapper;

    @RequestMapping("/findByThing.do")
    public String findByThing(Model model, HttpServletRequest request){
        UserInfo u = (UserInfo)request.getSession().getAttribute("user");
        if(u.getSy_ui_role() == 2){
            List<CarInfo> list = carInfoMapper.findByUser(u.getSy_ui_id());
            model.addAttribute("module", "car_list");
            model.addAttribute("carinfos", list);
            return "car/findByUser";
        }
        return null;
    }

}
