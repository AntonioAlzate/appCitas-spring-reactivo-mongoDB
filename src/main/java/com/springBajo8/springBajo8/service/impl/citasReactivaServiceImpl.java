package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    private static final String CANCELADA = "Cita Cancelada";
    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    @Override
    public Mono<citasDTOReactiva> cancelarCita(String idPaciente) {
        return IcitasReactivaRepository.findById(idPaciente)
                .flatMap(citasDTOReactiva -> {
                    citasDTOReactiva.setEstadoReservaCita(CANCELADA);
                    return IcitasReactivaRepository.save(citasDTOReactiva);
                });
    }

    @Override
    public Flux<citasDTOReactiva> consultarFechaYHora(LocalDate fechaReservaLocalDate, String horaReserva) {
        return IcitasReactivaRepository.findByFechaReservaCita(fechaReservaLocalDate)
                .flatMap(cita -> {
                    if(cita.getHoraReservaCita().equals(horaReserva)){
                        return Mono.just(cita);
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<String> consultarMedicoConsulta(String idPaciente) {
        Flux<citasDTOReactiva> citaReactiva = this.IcitasReactivaRepository.findByIdPaciente(idPaciente);

        return citaReactiva.single().flatMap(citasDTOReactiva -> {
            String nombres = citasDTOReactiva.getNombreMedico();
            String apellidos = citasDTOReactiva.getApellidosMedico();
            return Mono.just(nombres + " " + apellidos);
        });
    }

    @Override
    public Mono<citasDTOReactiva> agregarPadecimiento(String padecimiento, String id) {
        Mono<citasDTOReactiva> cita = this.IcitasReactivaRepository.findById(id);
        return cita.flatMap(citasDTOReactiva -> {
            citasDTOReactiva.agregarPadecimiento(padecimiento);
            return IcitasReactivaRepository.save(citasDTOReactiva);
        });
    }

    @Override
    public Mono<List<String>> consultarPadecimientos(String id) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva -> {
                    List<String> padecimientos = citasDTOReactiva.getPadecimientos();
                    return Mono.just(padecimientos);
                });
    }
}
