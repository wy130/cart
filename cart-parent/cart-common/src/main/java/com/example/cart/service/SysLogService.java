package com.example.cart.service;

import com.example.cart.entity.SysLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 吴玥
 * @Title SysLogService
 * @Description
 * @date 2020/1/16
 */
@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogBO sysLogBO){
        // 这里就不做具体实现了
        log.info(sysLogBO.getParams());
        return true;
    }
}

