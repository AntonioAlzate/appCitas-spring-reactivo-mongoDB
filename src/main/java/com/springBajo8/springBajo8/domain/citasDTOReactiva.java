package com.springBajo8.springBajo8.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "citas")
public class citasDTOReactiva {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);

    private String idPaciente;

    private String nombrePaciente;

    private String apellidosPaciente;

    private String nombreMedico;

    private String apellidosMedico;

    private LocalDate fechaReservaCita;

    private String horaReservaCita;

    private String estadoReservaCita;

    private List<String> padecimientos = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidosMedico() {
        return apellidosMedico;
    }

    public void setApellidosMedico(String apellidosMedico) {
        this.apellidosMedico = apellidosMedico;
    }

    public LocalDate getFechaReservaCita() {
        return fechaReservaCita;
    }

    public void setFechaReservaCita(LocalDate fechaReservaCita) {
        this.fechaReservaCita = fechaReservaCita;
    }

    public String getHoraReservaCita() {
        return horaReservaCita;
    }

    public void setHoraReservaCita(String horaReservaCita) {
        this.horaReservaCita = horaReservaCita;
    }

    public String getEstadoReservaCita() {
        return estadoReservaCita;
    }

    public void setEstadoReservaCita(String estadoReservaCita) {
        this.estadoReservaCita = estadoReservaCita;
    }

    public List<String> getPadecimientos() {
        return padecimientos;
    }

    public void setPadecimientos(List<String> padecimientos) {
        this.padecimientos = padecimientos;
    }

    public void agregarPadecimiento(String padecimiento){
        this.padecimientos.add(padecimiento);
    }

    //private Date sendedDate = new Date();


}
