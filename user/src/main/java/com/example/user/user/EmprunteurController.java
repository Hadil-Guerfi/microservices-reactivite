package com.example.user.user;

import com.example.emprunt.emprunt.EmpruntRequest;
import com.example.user.kafka.demandeEmpruntProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/emprunteurs")
@RequiredArgsConstructor
public class EmprunteurController {

  private final EmprunteurService service;
  private final demandeEmpruntProducer demandeEmpruntProd;


  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Emprunteur> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Emprunteur> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Emprunteur> create(@RequestBody EmprunteurRequest request) {
    return service.save(request);
  }

  @GetMapping("/filter")
  public Flux<Emprunteur> findByAuthor(@RequestParam String name) {
    return service.findByFirstname(name);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    service.deleteById(id);
  }





    @PostMapping("/demandeEmprunt")
    public String placeOrder(@RequestBody EmpruntRequest demandeEmp){


      System.out.println(demandeEmp.toString());

        EmpruntRequest demandeEmpruntEvent=new EmpruntRequest();

        demandeEmpruntEvent.setId_livre(demandeEmp.getId_livre());
        demandeEmpruntEvent.setId_empruteur(demandeEmp.getId_empruteur());
        demandeEmpruntEvent.setDate_debut(demandeEmp.getDate_debut());
        demandeEmpruntEvent.setDate_fin(demandeEmp.getDate_fin());

        demandeEmpruntProd.sendMessage(demandeEmpruntEvent);

        return "Order placed succefully ...";

    }

}
