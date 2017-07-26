package com.github.lhauspie.nightclazz.spring.webflux;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.lhauspie.nightclazz.spring.webflux.config.RouterConfig;
import com.github.lhauspie.nightclazz.spring.webflux.controller.ReactiveDrawingController;
import com.github.lhauspie.nightclazz.spring.webflux.domain.Drawing;
import com.github.lhauspie.nightclazz.spring.webflux.domain.DrawingInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouterConfigTest {
//  Doesn't work because controller is not in the Spring context !!!
//  @Mock
//  ReactiveDrawingController controller;

//  @MockBean
//  ReactiveDrawingController controller;

//  @Resource
//  ReactiveDrawingController controller;

  @Configuration
  @Import(RouterConfig.class)
  public static class TestConfiguration {
    @Bean
    public ReactiveDrawingController getController() {
      ReactiveDrawingController controller = Mockito.mock(ReactiveDrawingController.class);
      
      Mockito
          .when(controller.getDrawings())
          .thenReturn(Flux.range(0, 3)
              .map(String::valueOf)
              .map(id -> someDrawingInfo(id)));
      
      Mockito
          .when(controller.add(Mockito.any(Mono.class)))
          .thenReturn(Mono.just("3"));

      return controller;
    }
  }
  
  // initialiazed by the setRouterFunction
  private WebTestClient client;
  
  @Autowired
  public void setRouterFunction(RouterFunction<ServerResponse> routerFunction) {
    client = WebTestClient.bindToRouterFunction(routerFunction).build();
  }
  
  @Test
  public void getDrawingsTest() {
    client.get()
        .uri("/drawings")
        .accept(MediaType.TEXT_EVENT_STREAM)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(DrawingInfo.class)
        .hasSize(3)
        .contains(someDrawingInfo("0"), someDrawingInfo("1"), someDrawingInfo("2"));
  }
  
  @Test
  public void postDrawingTest() {
    String s = client.post()
        .uri("/drawing")
        .accept(MediaType.APPLICATION_JSON)
        .syncBody(someDrawing("base64Image"))
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .returnResult()
        .getResponseBody();
    
    Assert.assertEquals("3", s);
  }

  @Test
  public void getDrawingUrlNotFoundTest() {
    client.get()
        .uri("/drawing")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isNotFound();
  }

  @Test
  public void postDrawingsUrlNotFoundTest() {
    client.post()
        .uri("/drawings")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isNotFound();
  }
  
  private static Drawing someDrawing(String base64Image) {
    Drawing drawing= new Drawing();
    drawing.setId(null);
    drawing.setAuthor("lhauspie");
    drawing.setBase64Image("");
    return drawing;
  }

  private static DrawingInfo someDrawingInfo(String id) {
    DrawingInfo drawing= new DrawingInfo();
    drawing.setId(id);
    drawing.setAuthor("lhauspie");
    return drawing;
  }
}
