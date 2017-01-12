package yinxi.zheng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by zhengyinxi on 2017/1/11.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhengyinxi").password("12801991").roles("USER").and()
                .withUser("admin").password("1234-qwer").roles("USER", "ADMIN");
    }

    /**
     * This section defines the security policy for the app.
     * - BASIC authentication is supported (enough for this REST-based demo)
     * - /employees is secured using URL security shown below
     * - CSRF headers are disabled since we are only testing the REST interface,
     *   not a web one.
     *
     * NOTE: GET is not shown which defaults to permitted.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.rememberMe()
//                .rememberMeParameter("remember-me")
//                .rememberMeServices(rememberMeServices())
//                .key("posc")
//                .authenticationSuccessHandler(successHandler)
//                .and();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                //.failureHandler(failureHandler).successHandler(successHandler)
                .and().csrf().ignoringAntMatchers("/**")
                .and().exceptionHandling().accessDeniedPage("/denied");

    }

}
