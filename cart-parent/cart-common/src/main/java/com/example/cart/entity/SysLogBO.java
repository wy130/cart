package com.example.cart.entity;

import lombok.Data;

/**
 * @author 吴玥
 * @Title SysLogBO
 * @Description
 * @date 2020/1/16
 */

@Data
public class SysLogBO {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;
}

