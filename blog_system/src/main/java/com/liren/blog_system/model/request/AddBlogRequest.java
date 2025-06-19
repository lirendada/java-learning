package com.liren.blog_system.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBlogRequest {
    @NotNull
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
