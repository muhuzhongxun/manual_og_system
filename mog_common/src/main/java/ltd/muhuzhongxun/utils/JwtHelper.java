package ltd.muhuzhongxun.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
    private static long tokenExpiration = 24*60*60*1000;
    private static String tokenSignKey = "123456";

    public static String createToken(Integer userId, String loginName,Integer userType,boolean status,String avatar) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("name", loginName)
                .claim("roles", userType)
                .claim("status", status)
                .claim("avatar",avatar)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
    public static Integer getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("userId");
    }
    public static String getName(String token) {
        if(StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("name");
    }
    public static Integer getRoles(String token) {
        if(StringUtils.isEmpty(token)) return 0;
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer)claims.get("roles");
    }
    public static boolean getStatus(String token) {
        if(StringUtils.isEmpty(token)) return false;
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (boolean)claims.get("status");
    }
    public static String getAvatar(String token) {
        if(StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("avatar");
    }
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1, "55",4,true,"Kamado.png");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getName(token));
        System.out.println(JwtHelper.getRoles(token));
        System.out.println(JwtHelper.getStatus(token));
        System.out.println(JwtHelper.getAvatar(token));
    }
}
