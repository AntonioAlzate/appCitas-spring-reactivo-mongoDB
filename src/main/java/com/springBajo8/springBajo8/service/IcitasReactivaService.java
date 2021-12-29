package com.springBajo8.springBajo8.service;

//import com.yoandypv.reactivestack.messages.domain.Message;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface IcitasReactivaService {
    Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva);

    Mono<citasDTOReactiva> delete(String id);

    Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva);

    Flux<citasDTOReactiva> findByIdPaciente(String idPaciente);

    Flux<citasDTOReactiva> findAll();

    Mono<citasDTOReactiva> findById(String id);

    Mono<citasDTOReactiva> cancelarCita(String idPaciente);

    Flux<citasDTOReactiva> consultarFechaYHora(LocalDate fechaReservaLocalDate, String horaReserva);

    Mono<String> consultarMedicoConsulta(String idPaciente);

    Mono<citasDTOReactiva> agregarPadecimiento(String padecimiento, String id);

    Mono<List<String>> consultarPadecimientos(String id);
}
