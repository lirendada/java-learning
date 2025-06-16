package com.liren.transaction.service;

import com.liren.transaction.mapper.LogInfoMapper;
import com.liren.transaction.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    public void insertUser(String name, String op) {
        logInfoMapper.insertLog(name, op);
    }
}
