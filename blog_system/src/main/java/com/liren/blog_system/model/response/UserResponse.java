package com.liren.blog_system.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
}
