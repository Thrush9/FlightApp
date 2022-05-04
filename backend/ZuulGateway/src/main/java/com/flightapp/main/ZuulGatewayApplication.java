package com.flightapp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

//	@Bean
//    public FilterRegistrationBean<?> jwtFilter() {
//       
//    	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
//		
//		CorsConfiguration config=new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedMethod("*");
//		config.addAllowedHeader("*");
//		urlconfig.registerCorsConfiguration("/**", config);
//		
//		
//		FilterRegistrationBean filterbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
//		//filterbean.setFilter(new JwtFilter());
//		filterbean.addUrlPatterns("/FlightService/*");
//		return filterbean;
//		
//	}
}
