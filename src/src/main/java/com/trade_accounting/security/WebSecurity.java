package src.main.java.com.trade_accounting.security;

import com.trade_accounting.services.impl.client.EmployeeDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

import static com.trade_accounting.config.SecurityConstants.SIGN_UP_URL;
import static com.trade_accounting.config.SecurityConstants.TOKEN_GENERATOR_URL;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",

            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private final EmployeeDetailsServiceImpl userDetailsService;

    public WebSecurity(EmployeeDetailsServiceImpl userService) {
        this.userDetailsService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) {
//        Security disabled
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .permitAll();

//        Security enabled
        try {
            http.cors()
                    .and()
                    .csrf()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                    .and()
                    .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers(HttpMethod.POST, SIGN_UP_URL)
                    .permitAll()
                    .antMatchers(HttpMethod.POST, TOKEN_GENERATOR_URL)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    Encode the Password on Registration
    //TODO implement method encode the password on registration

//    Encode the Password on Authentication
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.authenticationProvider(authProvider());
            configAuthentication(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configAuthentication(AuthenticationManagerBuilder auth) {
        try {
            auth.jdbcAuthentication().passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
