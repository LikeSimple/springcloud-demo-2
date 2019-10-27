package com.newtouch.cloud.demo.service.userauthority.controller.criteria;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UsernameCriteria {

    @NotBlank(message = "用户名参数不能为空！")
    private String username;
}
