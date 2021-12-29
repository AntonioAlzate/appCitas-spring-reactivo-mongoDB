package com.springBajo8.springBajo8.web;


import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @GetMapping("/cancelacion/{id}")
    private Mono<citasDTOReactiva> cancelarCita(@PathVariable("id") String id){
        Mono<citasDTOReactiva> existe = icitasReactivaService.findById(id);

        if(existe == null)
            return Mono.empty();

        return this.icitasReactivaService.cancelarCita(id);
    }

    @GetMapping("citasReactivas/consulta/{fechaReserva}/{horaReserva}")
    private Flux<citasDTOReactiva> consultarFechaHora(@PathVariable("fechaReserva") String fechaReserva, @PathVariable("horaReserva") String horaReserva) {
        LocalDate fechaReservaLocalDate = LocalDate.parse(fechaReserva);
        return this.icitasReactivaService.consultarFechaYHora(fechaReservaLocalDate, horaReserva);
    }

    @GetMapping("/consulta/medico/{idPaciente}")
    private Mono<String> consultarMedicoConsulta(@PathVariable("idPaciente") String idPaciente){
        return this.icitasReactivaService.consultarMedicoConsulta(idPaciente);
    }

    @PostMapping("/agregar/padecimiento/{id}")
    private Mono<citasDTOReactiva> agregarPadecimiento(@RequestBody String  padecimiento, @PathVariable String id){
        return this.icitasReactivaService.agregarPadecimiento(padecimiento, id);
    }

    @GetMapping("/consultar/padecimientos/{id}")
    private Mono<List<String>> consultarPadecimientos(@PathVariable("id") String id){
        return this.icitasReactivaService.consultarPadecimientos(id);
    }

}
