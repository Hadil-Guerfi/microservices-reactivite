package com.example.user.livre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/livres")
@RequiredArgsConstructor
public class LivreController {

  private final LivreService service;

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Livre> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Livre> findById(@PathVariable Long id) {
    return service.findById(id);
  }

//  @PostMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  public Mono<Livre> create(@RequestBody LivreRequest request) {
//    return service.save(request);
//  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    service.deleteById(id);
  }
}
