# MediaFans
爬虫抖音、快手和小红书
v1.0 
第一个版本只做了抖音非官方爬虫粉丝，后续研究完了会更新快手和小红书。
抖音有开放平台，可以走官方oauth
抖音开放平台:https://open.douyin.com/platform
本项目仅供学习使用，请勿商业使用！
本项目仅供学习使用，请勿商业使用！

csdn博客文章放这里了,csdn有毛病不给发布这种博客 
某音有开放平台，可以走oauth授权。
不过由于wx小程序封杀了某音，所以wx小程序要走某音官方这条路就行不通了。不多说，直接上源码

DyUtils

package com.zengp.demo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.zengp.demo.constant.CommConstants;
import com.zengp.demo.model.DyUser;
/**
  * 获取抖音粉丝等相关信息(不走官方授权)
  * 此工具类可直接使用，也可根据其逻辑自行优化
 * @author zengp
 * 
 */
public class DyUtils {
	// 短链转长链，目的是将短链解析出来拿到sec_uid，可自行优化
	private static String shortchainUrl = "https://duanwangzhihuanyuan.51240.com/web_system/51240_com_www/system/file/duanwangzhihuanyuan/get/";
	// 抖音官方接口(未开放)
	private static String dyUrl = "https://www.iesdouyin.com/web/api/v2/user/info/";
	
	public static DyUser dyFans (String link) {
		link = clearDyHomeLink(link);
		RestTemplate restT = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("turl", link);
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map,headers);
		ResponseEntity<String> response = restT.postForEntity(shortchainUrl, request, String.class);
		String dyResult = response.getBody();
		String secUid = getSecUid(dyResult);
		DyUser dyUser = getDyFans(secUid);
		return dyUser;
	}
	
	/**
	  * 获取抖音的sec_uid
	 * @param str
	 * @return
	 */
	private static String getSecUid (String str) {
		String result = "";
		StringBuffer sb = new StringBuffer(str);
		int start = sb.indexOf(CommConstants.secUid) + CommConstants.secUid.length() + CommConstants.cursor;
		int end = start + CommConstants.uidLength;
		result = sb.substring(start, end);
		return result;
	}
	/**
	  *  获取抖音相关信息
	 * @param secUid
	 * @return
	 */
	private static DyUser getDyFans (String secUid) {
		RestTemplate restT = new RestTemplate();
		String url = dyUrl + CommConstants.secUidUrl + secUid;
		ResponseEntity<DyUser> response = restT.getForEntity(url, DyUser.class);
		DyUser dyUser = response.getBody();
		return dyUser;
	}
	
	/**
	  *  纯净化抖音主页分享链接
	  * 保险起见建议还是让用户自行去掉中文
	 * @param link
	 * @return
	 */
	private static String clearDyHomeLink (String link) {
		String result = "";
		// 这里可以优化
		result = link.replaceAll(CommConstants.REGEX_CHINESE, "");
		result = result.replaceAll(CommConstants.REGEX_COMMA, "");
		result = result.replaceAll(CommConstants.REGEX_EXCLAMATORY_MARK, "");
		result = result.trim();
		return result;
	}
}

主要是通过抖某音的分享链接短链解析长链拿到secuid，然后通过某音非官方接口请求到数据。所以就做到了通过用户主页链接就能拿到该用户的所有公开资料，可以自行选取自己要的字段，我主要是要粉丝数、点赞数和关注数。

使用示例

用雷猴的某音主页分享链接



运行结果



实际对比，实际对比本来有的 因为一些原因就没了

源码中涉及到的接口，如有侵权，请联系博主。

原创不易，转载请标明出处。

本文仅供学习使用，请勿商业使用。

完整源码

https://github.com/Zeng49487/MediaFans
