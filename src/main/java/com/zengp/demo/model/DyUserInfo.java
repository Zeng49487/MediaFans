package com.zengp.demo.model;

import lombok.Data;

/**
  *  抖音用户具体信息类
  * 可以根据自己要求自行添加字段，全部字段可查看FANS.md
 * @author zengp
 *
 */
@Data
public class DyUserInfo {
	//关注
	private Long following_count;
	//粉丝
	private Long follower_count;
	//点赞
	private String total_favorited;
}
