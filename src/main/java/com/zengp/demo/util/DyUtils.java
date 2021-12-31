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
	private static String shortchainUrl = "https://duanwangzhihuanyuan.bmcx.com/web_system/bmcx_com_www/system/file/duanwangzhihuanyuan/get/";
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
