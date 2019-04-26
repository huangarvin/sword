package com.huangsuip.api.config;

import com.huangsuip.api.security.CustomAuthenticationProvider;
import com.huangsuip.api.security.JWTTokenSecurityFilter;
import com.huangsuip.service.user.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

/**
 * @author HuangSuip
 */
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //GlobalAuthenticationConfigurerAdapter

    public WebSecurityConfig() {
        //可以关闭默认的拦截器
        //super(true);
    }

    @Lazy
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public AuthenticationProvider getAuthentication() {
        return new CustomAuthenticationProvider();
    }

    public JWTTokenSecurityFilter jwtTokenSecurityFilter(){
        return new JWTTokenSecurityFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthentication())
            .userDetailsService(userDetailService)
            //需要添加密码的加密规则
            .passwordEncoder(new BCryptPasswordEncoder());
    }

/*    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(getAuthentication());
        ProviderManager pm = new ProviderManager(providers);
        return pm;
    }
*/

    /*
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .anyExchange()
            .authenticated()
            .and()
            .httpBasic().and()
            .formLogin();
        return http.build();
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http//.authenticationProvider(getAuthentication())
            //取消csrf
            .csrf().disable()
            //登录页面
            .formLogin().disable()
            //异常处理
            .exceptionHandling().disable()
            //退出登录
            .logout().disable()
            .authorizeRequests()
            //允许静态资源加载不需要权限  还有swagger
//            .antMatchers(
//                    HttpMethod.GET,
//                    "/",
//                    "/*.html",
//                    "/favicon.ico",
//                    "/**/*.html",
//                    "/**/*.css",
//                    "/**/*.js",
//                    "/**/*.png",
//                    "/swagger-resources/**",
//                    "/v2/api-docs",
//                    "/**/images/**")
//            .permitAll()
//            .antMatchers(HttpMethod.POST, "/login").permitAll()
            //admin 开头的需要dba 和 admin 权限
            .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('DBA')")
            .antMatchers("/anonymous/**").anonymous()

            //除此之外任意请求
            .anyRequest().permitAll();

        //http.addFilterAfter(jwtTokenSecurityFilter, JWTTokenSecurityFilter.class);

        http.addFilterAfter(jwtTokenSecurityFilter(), WebAsyncManagerIntegrationFilter.class);
        //http.addFilterBefore(new AnonymousAuthenticationFilter("anonymousUser"), JWTTokenSecurityFilter.class);
/*            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/todo.html", true)
            .authenticationDetailsSource(context -> customAuthenticationProvider);*/

        //
        // http.addFilterAfter();
    }
}

/*
Configurer   Filter   功能说明
1. WebAsyncManagerIntegrationFilter
OpenIDLoginConfigurer   OpenIDAuthenticationFilter   处理OpenID授权请求
3. HeaderWriterFilter   HeadersConfigurer   在返回报文头中添加Security相关信息
CorsConfigurer   CorsFilter   提供跨域访问配置支持的Filter
7. SessionManagementConfigurer   SessionManagementFilter   会话管理Filter
PortMapperConfigurer   无   用于在Http及Https请求之间重定向时的端口判定
JeeConfigurer   J2eePreAuthenticatedProcessingFilter   添加J2EE预授权处理机制支持
X509Configurer   X509AuthenticationFilter   添加X509预授权处理机制支持
RememberMeConfigurer   RememberMeAuthenticationFilter   记住用户名及密码功能支持
8. ExpressionUrlAuthorizationConfigurer   FilterSecurityInterceptor   Security的主要Filter，通过调用权限管理器等进行Http访问的权限判断
4. RequestCacheConfigurer   RequestCacheAwareFilter   缓存请求并在必要的时候使用缓存的请求
** ExceptionHandlingConfigurer   ExceptionTranslationFilter   处理AccessDeniedException及AuthenticationException异常
2. SecurityContextConfigurer   SecurityContextPersistenceFilter   SecurityContext对象持久化Filter，用于在请求开始阶段初始化并持久化该对象，在后续的Filter中可以使用该对象来获取信息
5. ServletApiConfigurer   SecurityContextHolderAwareRequestFilter   在原始请求基础上包装一些方法供后续调用
** CsrfConfigurer   CsrfFilter   跨站请求伪造保护Filter；
** LogoutConfigurer   LogoutFilter   退出登录请求处理Filter
6. AnonymousConfigurer   AnonymousAuthenticationFilter   匿名请求控制Filter
** FormLoginConfigurer   UsernamePasswordAuthenticationFilter   表单登录请求处理Filter
OAuth2LoginConfigurer   OAuth2AuthorizationRequestRedirectFilter   OAuth2请求权限控制处理Filter，为其它网站提供本网站Oauth2方式登录，即其它网站通过本网站的账户密码进行登录授权
ChannelSecurityConfigurer   ChannelProcessingFilter   通道选择Filter，确保请求是通过正确的通道过来的，如Http或者Https
** HttpBasicConfigurer   BasicAuthenticationFilter   Security基础登录授权Filter，将其结果保存在SecurityContextHolder中
*/
