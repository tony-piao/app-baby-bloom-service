package com.babybloom.web.controller;

import com.babybloom.web.model.dto.WeixinLoginUpDto;
import com.babybloom.web.model.entity.GeneralJsonResult;
import com.babybloom.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webapi/user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 微信登录
     *
     * @param weixinLoginUpDto
     * @return
     */
    @RequestMapping(value = "weixinLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public GeneralJsonResult weixinLogin(@RequestBody WeixinLoginUpDto weixinLoginUpDto) {
        return GeneralJsonResult.success(userService.weixinLogin(weixinLoginUpDto));
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @RequestMapping(value = "getLoginUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public GeneralJsonResult getLoginUserInfo() {
        Long uid = authAndGetUid();
        return GeneralJsonResult.success(userService.getUserByGuid(uid));
    }
}
