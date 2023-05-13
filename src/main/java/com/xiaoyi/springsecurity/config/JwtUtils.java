package com.xiaoyi.springsecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author 王艺翔
 * @description JwtUtils jwt的服务层，生成、检查token
 * @date 2023/5/9 19:41
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github <a href="https://github.com/Tom-Collection">...</a>"
 */
@Component
public class JwtUtils {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	/**
	 * 提取用户邮箱
	 *
	 * @param token 令牌
	 * @return string
	 */
	public String extractUserEmail(String token) {
		/*
		* Claims::getSubject 等同于
		* new Function<>() {
			@Override
			public Object apply(Claims claims) {
				return claims.getSubject();
			}
		});
		* */
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * 提取Claim
	 *
	 * @param token      令牌
	 * @param getSubject getSubject()方法
	 * @param <T>        T
	 * @return T
	 */
	private <T> T extractClaim(String token, Function<Claims, T> getSubject) {
		Claims claims = extractAllClaim(token);
		return getSubject.apply(claims);
	}

	/**
	 * 提取所有的Claim
	 *
	 * @param token 令牌
	 * @return Claims
	 */
	private Claims extractAllClaim(String token) {
		return Jwts
						// 得到 io.jsonwebtoken.JwtParserBuilder 对象
						.parserBuilder()
						// 设置SigningKey
						.setSigningKey(getSigningKey())
						// 得到JwtParser对象
						.build()
						// 解析token
						.parseClaimsJws(token)
						// 获取Claims，包含所有声明
						.getBody();
	}

	/**
	 * 获取SigningKey
	 *
	 * @return key
	 */
	private Key getSigningKey() {
		// 从一个 Base64 编码的字符串 编码成 byte数组
		byte[] bytes = Decoders.BASE64.decode(secretKey);
		// 创建一个 HMAC-SHA 签名密钥
		return Keys.hmacShaKeyFor(bytes);
	}

	/**
	 * 检查token是否有效、过期
	 *
	 * @param token       令牌
	 * @param userDetails 用户信息
	 * @return flag
	 */
	public boolean isTokenValid(String token, UserDetails userDetails) {
		// 提取用户邮箱
		String email = extractUserEmail(token);
		// 判断token是否合法
		return userDetails.getUsername().equals(email) &&
						// 判断token是否过期
						!extractExpiration(token).before(new Date());
	}

	/**
	 * 获取token过期时间
	 *
	 * @param token 令牌
	 * @return date
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * 创建token
	 *
	 * @param user 用户信息
	 * @return string
	 */
	public String generateToken(UserDetails user) {
		// 调用重载的generateToken方法，传入一个空的HashMap和用户信息
		return generateToken(new HashMap<>(), user);
	}

	private String generateToken(Map<String, Objects> claims, UserDetails user) {
		// 调用buildToken方法，传入claims，用户信息和过期时间
		return buildToken(claims, user, expiration());
	}

	private String buildToken(Map<String, Objects> claims, UserDetails user, Long expiration) {
		return Jwts
						.builder() // 创建一个JWT token构建器
						.setClaims(claims) // 设置claims(空的hashmap，即为空值)
						.setSubject(user.getUsername()) // 设置subject为用户名
						.setIssuedAt(new Date(System.currentTimeMillis())) // 设置issuedAt为当前时间
						.setExpiration(new Date(System.currentTimeMillis() + expiration)) // 设置过期时间
						.signWith(getSigningKey(), SignatureAlgorithm.HS256) // 使用HS256算法进行签名
						.compact(); // 将JWT token构建器中的信息压缩为一个字符串
	}

	private Long expiration() {
		// 返回当前时间加上一天的时间戳
		return new Date(System.currentTimeMillis() + 1000 * 60 * 24).getTime();
	}
}
