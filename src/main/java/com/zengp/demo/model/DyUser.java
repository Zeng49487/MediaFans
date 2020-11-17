package com.zengp.demo.model;

import lombok.Data;

/**
  * 抖音用户类
 * @author zengp
 *
 */
@Data
public class DyUser {
	private Integer status_code;
	
	private DyUserInfo user_info;
	
	private DyExtra extra;
}
