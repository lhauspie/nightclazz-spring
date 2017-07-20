package com.github.lhauspie.nightclazz.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.github.lhauspie.nightclazz.spring.webflux.domain.Drawing;
import com.github.lhauspie.nightclazz.spring.webflux.domain.DrawingInfo;
import com.github.lhauspie.nightclazz.spring.webflux.repository.DrawingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ReactiveDrawingControllerImpl implements ReactiveDrawingController {

  @Autowired
  private ReactiveMongoTemplate template;

  @Autowired
  private DrawingRepository drawingRepository;
  
  
//  public ReactiveDrawingControllerImpl(ReactiveMongoTemplate template, DrawingRepository drawingRepository) {
//    this.template = template;
//    this.drawingRepository = drawingRepository;
//  }
//  
  @Override
  public Mono<String> add(Mono<Drawing> drawing) {
    return drawingRepository.saveAll(drawing).single().map(Drawing::getId);
  }

  @Override
  public Flux<DrawingInfo> getDrawings() {
    Query query = new Query();
    return template.tail(query , DrawingInfo.class);
  }
}
