package com.example.user.livre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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


  @GetMapping("/{id}/nbr_exemplaire")
  public Mono<Integer> getNumberOfExemplaires(@PathVariable Long id) {
    return service.getNumberOfExemplaires(id);
  }

  @PutMapping("/{id}/decreaseLivre")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Void> decreaseLivre(@PathVariable Long id) {


    return service.decreaseNbrExemplaire(id);
  }

}
