package com.example.cart.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author 吴玥
 * @Title JwtUtils
 * @Description
 * @date 2019/12/23
 */
@Component
@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    //签名私钥
    private String key ;
    //签名的失效时间
    private Long ttl;

    /**
     * 设置认证token
     *      id:登录用户id
     *      subject：登录用户名
     *
     */
    public String createJwt(String id, String name, Map<String,Object> map) {
        //1.设置失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //3.根据map设置claims
        for(Map.Entry<String,Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }
    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法
     *
     * @param claims 保存在Payload（有效载荷）中的内容
     * @return token字符串
     */
    public String createJWT( Map<String, Object> claims) {
        //指定签名的时候使用的签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //1.设置失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;


        //创建一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //保存在Payload（有效载荷）中的内容
                .setClaims(claims)
                //iat: jwt的签发时间
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(exp))
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);

        return builder.compact();
    }


    /**
     * 解析token字符串获取clamis
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

}
