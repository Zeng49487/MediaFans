package com.zengp.demo.test;

import com.zengp.demo.model.DyUser;
import com.zengp.demo.util.DyUtils;
/**
 * 测试类
 * @author zengp
 *
 */
public class Test {

	public static void main(String[] args) {
		String link = "在抖音，记录美好生活！ https://v.douyin.com/Jm81L4j/";
		DyUser dyUser = DyUtils.dyFans(link);
		System.out.println("关注:" + dyUser.getUser_info().getFollowing_count());
		System.out.println("粉丝:" + dyUser.getUser_info().getFollower_count());
		System.out.println("点赞:" + dyUser.getUser_info().getTotal_favorited());
	}
	
}
