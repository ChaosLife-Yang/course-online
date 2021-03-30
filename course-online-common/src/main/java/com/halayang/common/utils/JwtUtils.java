package com.halayang.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

/**
 * 私钥加密 公钥解密
 *
 * @author helen
 * @since 2019/10/16
 */
@Slf4j
public class JwtUtils {

    /**
     * token过期时间 一周
     */
    public static final long EXPIRE = 604800000;
    /**
     * 秘钥
     */
    public static final String APP_SECRET = "kUPWKwnJSw2h2Gg2GBaRX/yVGY+YQmoKZQNXMWh28x0=";

    public static final Key KEY = Keys.hmacShaKeyFor(APP_SECRET.getBytes());

    private JwtUtils() {
    }

    /**
     * 生成token字符串的方法
     *
     * @param id       会员id
     * @param nickname 会员名称
     * @return
     */
    public static String getJwtToken(String id, String nickname) {

        return Jwts.builder()
                .setSubject("halayang-user")
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(KEY, SignatureAlgorithm.HS256)
                //设置token主体部分 ，存储用户信息
                .claim("id", id)
                .claim("nickname", nickname)
                .compact();
    }

    public static String getUserJwtToken(String nickname) {

        return Jwts.builder()
                .setSubject(nickname)
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static String getUserByJwtToken(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("解密有误");
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("解密有误");
            return false;
        }
        return true;
    }

    /**
     * 根据token字符串获取会员id
     *
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

    /**
     * 根据token字符串获取会员id
     *
     * @param jwtToken
     * @return
     */
    public static String getMemberIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

}
