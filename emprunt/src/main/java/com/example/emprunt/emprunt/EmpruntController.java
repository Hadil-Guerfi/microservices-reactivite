package com.example.emprunt.emprunt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/emprunts")
@RequiredArgsConstructor
public class EmpruntController {

  private final EmpruntService service;

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Emprunt> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Emprunt> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Emprunt> create(@RequestBody EmpruntRequest request) {
    return service.save(request);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    service.deleteById(id);
  }
}
