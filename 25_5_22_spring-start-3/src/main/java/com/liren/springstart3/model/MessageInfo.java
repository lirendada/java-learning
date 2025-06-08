package com.liren.springstart3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageInfo {
    private String from;
    private String to;
    private String message;
}
