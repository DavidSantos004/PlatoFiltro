package com.example.Plato.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "plato")
@Builder

public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "plato_sequence",
        sequenceName = "plato_sequence",
        allocationSize = 1
    )
    private Integer idplato;

    @Column
    private String nombreplato;
    @Column
    private String descripcionplato;
    @Column
    private double precioplato;
    @Column
    private String colorplato;
    @Column
    private String materialplato;

    

    public Plato() {
    }

    public Plato(Integer idplato, String nombreplato, String descripcionplato, double precioplato, String colorplato,
            String materialplato) {
        this.idplato = idplato;
        this.nombreplato = nombreplato;
        this.descripcionplato = descripcionplato;
        this.precioplato = precioplato;
        this.colorplato = colorplato;
        this.materialplato = materialplato;
    }

    public Integer getIdplato() {
        return idplato;
    }

    public void setIdplato(Integer idplato) {
        this.idplato = idplato;
    }

    public String getNombreplato() {
        return nombreplato;
    }

    public void setNombreplato(String nombreplato) {
        this.nombreplato = nombreplato;
    }

    public String getDescripcionplato() {
        return descripcionplato;
    }

    public void setDescripcionplato(String descripcionplato) {
        this.descripcionplato = descripcionplato;
    }

    public double getPrecioplato() {
        return precioplato;
    }

    public void setPrecioplato(double precioplato) {
        this.precioplato = precioplato;
    }

    public String getColorplato() {
        return colorplato;
    }

    public void setColorplato(String colorplato) {
        this.colorplato = colorplato;
    }

    public String getMaterialplato() {
        return materialplato;
    }

    public void setMaterialplato(String materialplato) {
        this.materialplato = materialplato;
    }

    @Override
    public String toString() {
        return "Plato [idplato=" + idplato + ", nombreplato=" + nombreplato + ", descripcionplato=" + descripcionplato
                + ", precioplato=" + precioplato + ", colorplato=" + colorplato + ", materialplato=" + materialplato
                + "]";
    }

    
}
