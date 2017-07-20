package com.github.lhauspie.nightclazz.spring.webflux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

  
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    WebFluxConfigurer.super.addCorsMappings(registry);
    
    registry.addMapping("/drawings")
        .allowedOrigins("https://localhost:8443")
        .allowedMethods(HttpMethod.GET.toString())
        .allowedHeaders(HttpHeaders.CONTENT_TYPE);
    
    registry.addMapping("/drawing")
        .allowedOrigins("https://localhost:8443")
        .allowedMethods(HttpMethod.POST.toString())
        .allowedHeaders(HttpHeaders.CONTENT_TYPE);
  }
}
