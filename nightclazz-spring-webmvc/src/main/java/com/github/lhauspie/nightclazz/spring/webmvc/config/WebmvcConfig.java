package com.github.lhauspie.nightclazz.spring.webmvc.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("drawing");
  }

  @Bean
  public JettyHttp2Customizer customizer(final ServerProperties serverProperties) {
      return new JettyHttp2Customizer(serverProperties);
  }

  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate(final MongoClient mongoClient) {
      return new ReactiveMongoTemplate(mongoClient, "nightclazz");
  }
}
