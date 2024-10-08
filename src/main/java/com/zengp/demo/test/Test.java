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
		String link = "4- 长按复制此条消息，打开抖音搜索，查看TA的更多作品。 https://v.douyin.com/iBuXRGpP/ 8@8.com :9pm";
		DyUser dyUser = DyUtils.dyFans(link);
		System.out.println("链接:" + link);
		System.out.println("关注:" + dyUser.getUser_info().getFollowing_count());
		System.out.println("粉丝:" + dyUser.getUser_info().getMplatform_followers_count());
		System.out.println("点赞:" + dyUser.getUser_info().getTotal_favorited());
	}
	
}
