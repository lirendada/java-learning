package com.liren.blog_system.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    @NotNull
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
