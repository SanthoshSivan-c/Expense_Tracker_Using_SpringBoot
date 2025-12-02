package expense_tracker;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtfilter) throws Exception {
    	
    		http
    			.csrf(AbstractHttpConfigurer::disable)
    			.cors(cors->cors.configurationSource(corsconfig()))
    			.authorizeHttpRequests(auth->auth
    					.requestMatchers("/users/**").permitAll()
    					.anyRequest().authenticated()
    					)
    			.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    			
    															
    }
    
    @Bean
    public CorsConfigurationSource corsconfig() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.setAllowedOrigins(List.of("*"));
    	config.setAllowedHeaders(List.of("*"));
    	config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", config);
    	
    	return source;
    	
    	
    }
}

