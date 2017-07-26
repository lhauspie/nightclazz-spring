package com.github.lhauspie.nightclazz.spring.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.lhauspie.nightclazz.spring.webflux.controller.ReactiveDrawingController;
import com.github.lhauspie.nightclazz.spring.webflux.domain.Drawing;
import com.github.lhauspie.nightclazz.spring.webflux.domain.DrawingInfo;

@Configuration
public class RouterConfig {

//  @Bean
//  public RouterFunction<ServerResponse> routingFunction(ReactiveDrawingController controller) {
//    RouterFunction<ServerResponse> routerFunction = 
//          RouterFunctions.route(RequestPredicates.GET("/drawings"),
//              request -> 
//                  ServerResponse.ok()
//                    .contentType(MediaType.TEXT_EVENT_STREAM)
//                    .body(controller.getDrawings(), DrawingInfo.class))
//        .and(
//          RouterFunctions.route(RequestPredicates.POST("/drawing"),
//              request -> 
//                  ServerResponse.ok()
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .body(controller.add(request.bodyToMono(Drawing.class)), String.class)))
//        .and(
//            RouterFunctions.route(RequestPredicates.OPTIONS("/drawing*"),
//                request -> ServerResponse.ok().build())
//            );
//    return routerFunction;
//  }
}
