package com.besscroft.pisces.framework.common.constant;

/**
 * @Description security 常量
 * @Author Bess Croft
 * @Date 2022/2/3 21:06
 */
public interface AuthConstants {

    /** 认证信息 Http 请求头 */
    String JWT_TOKEN_HEADER = "Authorization";

    /** 用户信息 Http 请求头 */
    String USER_TOKEN_HEADER = "user";

    /** JWT令牌前缀 */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /** JWT存储权限前缀 */
    String AUTHORITY_PREFIX = "ROLE_";

    /** JWT存储权限属性 */
    String JWT_AUTHORITIES_KEY = "authorities";

    /** Redis 缓存权限规则 key */
    String PERMISSION_RULES_KEY = "auth:roleResourceMap";

    /** 默认密码编码方式 */
    String BCRYPT = "{bcrypt}";

    /** 系统 client_id */
    String SYSTEM_CLIENT_ID = "pisces-system";

    /** 启用账号 */
    Integer STATUS = 1;

    /** 客户端 id */
    String CLIENT_ID = "client_id";

    /** 系统机密 */
    String SYSTEM_CLIENT_SECRET = "123456";

    /** OAuth2 密码模式 */
    String OAUTH2_PASSWORD = "password";

}
