package com.github.lhauspie.nightclazz.spring.webmvc.controller;

import java.util.Base64;

import javax.servlet.http.PushBuilder;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lhauspie.nightclazz.spring.webmvc.domain.Drawing;
import com.github.lhauspie.nightclazz.spring.webmvc.domain.DrawingInfo;

@Controller
public class DrawingController {

  private ReactiveMongoTemplate template;
  
  public DrawingController(ReactiveMongoTemplate template) {
    this.template = template;
  }
  
  @GetMapping(path = "/drawing/{id}", produces = MediaType.IMAGE_PNG_VALUE)
  @ResponseBody
  public byte[] getDrawing(@PathVariable("id") String id) {
    return template.findById(id, Drawing.class)
        .map(Drawing::getBase64Image)
        .map(Base64.getDecoder()::decode)
        .block();
  }
  
  @GetMapping(path = "/", produces = MediaType.IMAGE_PNG_VALUE)
  public String getDrawings(PushBuilder builder) {
    template.findAll(DrawingInfo.class).doOnEach(drawingInfo -> {
      builder.path("/drawing/" + drawingInfo.get().getId());
      builder.push();
    });
    return "drawing.html";
  }
}
