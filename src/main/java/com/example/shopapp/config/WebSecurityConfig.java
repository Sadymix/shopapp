package com.example.shopapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("""
                                select username, password, enabled
                                    from users
                                    where username=?
                                """)
                .authoritiesByUsernameQuery("""
                        SELECT
                            users.username, user_roles.role_name
                        FROM
                            users
                                LEFT OUTER JOIN
                            users_user_roles ON users.user_id = users_user_roles.user_user_id
                                LEFT OUTER JOIN
                            user_roles ON users_user_roles.user_roles_user_role_id = user_roles.user_role_id
                        WHERE username = ?
                        """)
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .getUserDetailsService();
    }

}
