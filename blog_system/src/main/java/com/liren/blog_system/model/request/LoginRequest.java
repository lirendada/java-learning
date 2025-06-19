package com.liren.blog_system.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotBlank
    private String userName;

    @NotBlank
    @Length(min = 5, max = 25)
    private String password;
}
