package com.liren.user.api.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名长度不能超过20")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Length(max = 20, message = "密码长度不能超过20")
    private String password;

    @Length(max = 64, message = "github地址不能超过64位")
    private String githubUrl;

    @NotBlank(message = "邮箱不能为空")
    private String email;
}
