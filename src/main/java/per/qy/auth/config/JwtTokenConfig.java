package per.qy.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import per.qy.auth.dao.SystemAccountDao;
import per.qy.auth.entity.SystemAccount;
import per.qy.auth.util.GsonUtil;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class JwtTokenConfig {

    private static KeyPair KEY_PAIR;

    static {
        try {
            KEY_PAIR = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private SystemAccountDao systemAccountDao;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setKeyPair(KEY_PAIR);
        return accessTokenConverter;
    }

    @Bean
    public ResourceServerTokenServices resourceJwtTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 使用自定义的Token转换器
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        // 使用自定义的tokenStore
        defaultTokenServices.setTokenStore(jwtTokenStore());
        return defaultTokenServices;
    }

    /**
     * token信息扩展
     */
    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                Authentication userAuthentication = authentication.getUserAuthentication();
                if (userAuthentication != null) {
                    String userName = userAuthentication.getName();
                    List<SystemAccount> list = systemAccountDao.findByAccount(userName);
                    if (list != null && !list.isEmpty()) {
                        SystemAccount account = list.get(0);
                        Map<String, Object> additionalInformation = new HashMap<>();
                        Map<String, String> map = new HashMap<>();
                        map.put("account", account.getAccount());
                        map.put("createTime", account.getCreateTime().toString());
                        map.put("state", String.valueOf(account.getState()));
                        additionalInformation.put("user", GsonUtil.toJson(map));
                        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                    }
                }
                return accessToken;
            }
        };
    }
}
