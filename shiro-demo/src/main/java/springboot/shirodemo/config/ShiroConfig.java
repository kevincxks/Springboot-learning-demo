package springboot.shirodemo.config;


import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
//    @Bean
//    public DefaultWebSecurityManager  securityManager(Realm shiroRealm, ShiroCacheManager shiroCacheManager){
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//
//        securityManager.setRealm(shiroRealm);
//
//        //关闭shir o自带的session
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//
//        //自定义缓存管理
//        securityManager.setCacheManager(shiroCacheManager);
//        return securityManager;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//
//        // 添加jwt过滤器
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("jwt", jwtFilter());
//        filterMap.put("logout", new SystemLogoutFilter());
//        shiroFilter.setFilters(filterMap);
//
//        //拦截器
//        Map<String,String> filterRuleMap = new LinkedHashMap<>();
//        filterRuleMap.put("/logout", "logout");
//        filterRuleMap.put("/**", "jwt");
//        shiroFilter.setFilterChainDefinitionMap(filterRuleMap);
//
//        return shiroFilter;
//    }
//
//    @Bean
//    public JwtFilter jwtFilter(){
//        return new JwtFilter();此处为AccessToken
//    }



}
