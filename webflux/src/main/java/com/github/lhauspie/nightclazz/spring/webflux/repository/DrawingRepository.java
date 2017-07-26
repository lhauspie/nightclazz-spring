package com.github.lhauspie.nightclazz.spring.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.github.lhauspie.nightclazz.spring.webflux.domain.Drawing;

import reactor.core.publisher.Mono;

@Repository
public interface DrawingRepository extends ReactiveCrudRepository<Drawing, String> {
  Mono<String> insert(Mono<Drawing> drawing);
}
