package com.example.emprunt.emprunt;

import com.example.emprunt.kafka.notification.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/emprunts")
@RequiredArgsConstructor
@Slf4j
public class EmpruntController {

  private final EmpruntService service;
  private final Producer producer;

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


  @GetMapping("/expired")
  public Flux<Emprunt> findEmpruntWithDateFinExpired() {
    Flux<Emprunt> result = service.findEmpruntWithDateRetourExpire();

    // Convert each Emprunt object to its string representation
    Flux<String> stringResult = result.map(emprunt -> emprunt.toString());

    // Log the string representation of the result
    stringResult.subscribe(
            empruntStr -> {
//              log.info(empruntStr);
              producer.sendMessage(empruntStr);
            },
            error -> log.error("Error converting Emprunt to string", error)
    );

    return result;
  }


}
