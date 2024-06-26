package com.university.shophub.frontend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final String[] publicPages = new String[]{"/**", "/svg/**", "/script/**", "/css/**"};
    private final String[] privateAdminPages = new String[]{"/management/**"};
    private final String[] privateSellerPages = new String[]{"product/create/**", "/product/edit/**"};
    private final String[] requireLoginPages = new String[]{"/cart/**", "/payment/**"};
    private final String[] noCSRFProtectionPages = new String[]{};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**")
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers(privateAdminPages).hasRole("ADMIN")
                        .requestMatchers(requireLoginPages).hasAnyRole("USER", "SELLER", "ADMIN")
                        .requestMatchers(privateSellerPages).hasRole("SELLER")
                        .requestMatchers(publicPages).permitAll()
                        .anyRequest().denyAll())
                .formLogin(form -> form.loginPage("/p4/login/")
                        .permitAll()
                        .defaultSuccessUrl("/p4/"))
                .logout(logout -> logout.logoutUrl("/p4/logout/")
                        .logoutSuccessUrl("/p4/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .csrf(csrf -> csrf.ignoringRequestMatchers(noCSRFProtectionPages));
        return http.build();
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
