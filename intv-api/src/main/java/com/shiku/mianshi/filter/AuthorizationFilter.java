package com.shiku.mianshi.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import cn.xyz.commons.support.spring.SpringBeansUtils;
import cn.xyz.commons.utils.ReqUtil;
import cn.xyz.commons.utils.StringUtil;
import cn.xyz.service.KTipsServiceImpl;

import com.google.common.collect.Maps;

@WebFilter(filterName = "authorizationFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "enable", value = "true") })
public class AuthorizationFilter implements Filter {
	private boolean enable;
	private JedisPool jedisPool;
	private Map<String, String> requestUriMap;
	private AuthorizationFilterProperties properties;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		if (!enable) {
			arg2.doFilter(arg0, response);
			return;
		}

		HttpServletRequest request = (HttpServletRequest) arg0;
		String accessToken = request.getParameter("access_token");

		System.out.println("请求：" + request.getRequestURI());
		System.out.println("Content-Type：" + request.getContentType());
		System.out.println("User-Agent：" + request.getHeader("User-Agent"));
		System.out
				.println("**************************************************");

		// 需要登录
		if (isNeedLogin(request.getRequestURI())) {
			// 请求令牌是否包含
			if (StringUtil.isEmpty(accessToken)) {
				int tipsKey = 1030101;
				renderByTipsKey(response, tipsKey);
			} else {
				String userId = getUserId(accessToken);
				// 请求令牌是否有效
				if (null == userId) {
					int tipsKey = 1030102;
					renderByTipsKey(response, tipsKey);
				} else {
					ReqUtil.setLoginedUserId(Integer.parseInt(userId));
					arg2.doFilter(arg0, response);
				}
			}
		} else {
			String userId = getUserId(accessToken);
			if (null != userId) {
				ReqUtil.setLoginedUserId(Integer.parseInt(userId));
			}
			// request.getParameterMap().put("userId", new String[] { "1" });

			arg2.doFilter(arg0, response);
		}
	}

	private boolean isNeedLogin(String requestUri) {
		return !requestUriMap.containsKey(requestUri.trim());
	}

	private String getUserId(String _AccessToken) {
		Jedis resource = jedisPool.getResource();
		String userId = null;

		try {
			userId = resource.get(String.format("at_%1$s", _AccessToken));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(resource);
		}

		return userId;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		enable = Boolean.parseBoolean(arg0.getInitParameter("enable"));
		requestUriMap = Maps.newHashMap();
		jedisPool = SpringBeansUtils.getBean("jedisPool");
		properties = SpringBeansUtils.getContext().getBean(
				AuthorizationFilterProperties.class);

		for (String requestUri : properties.getRequestUriList()) {
			requestUriMap.put(requestUri, requestUri);
		}

	}

	private static final String template = "{\"resultCode\":%1$s,\"resultMsg\":\"%2$s\"}";

	private static void renderByTipsKey(ServletResponse response, int tipsKey) {
		String tipsValue = KTipsServiceImpl.getTipsValue(tipsKey);
		String s = String.format(template, tipsKey, tipsValue);

		output(response, s);
	}

	private static void output(ServletResponse response, String s) {
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(s);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
