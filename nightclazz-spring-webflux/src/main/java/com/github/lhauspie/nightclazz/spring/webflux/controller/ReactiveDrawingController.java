package com.github.lhauspie.nightclazz.spring.webflux.controller;

import com.github.lhauspie.nightclazz.spring.webflux.domain.Drawing;
import com.github.lhauspie.nightclazz.spring.webflux.domain.DrawingInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveDrawingController {
  
  public Mono<String> add(Mono<Drawing> drawing);

  public Flux<DrawingInfo> getDrawings();
}
