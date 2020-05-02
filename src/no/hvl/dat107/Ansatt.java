package no.hvl.dat107;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.time.LocalDate;


@Entity
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate datoans;
    private String stilling;
    private int mndlon;

    public Ansatt() {}


    public Ansatt(String brukernavn, String fornavn, String etternavn,
				  LocalDate datoans, String stilling, int mndlon) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.brukernavn = brukernavn;
        this.datoans = datoans;
        this.stilling = stilling;
        this.mndlon = mndlon;


    }    
   
    public int getId() {
        return id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public LocalDate getDatoans() {
        return datoans;
    }

    public void setDatoans(LocalDate datoans) {
        this.datoans = datoans;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public int getMndlon() {
        return mndlon;
    }

    public void setMndlon(int mndlon) {
        this.mndlon = mndlon;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return String.format("Ansatt: id=%d, brukernavn=%s, navn=%s %s Stilling: %s, mndlon: %d", id, brukernavn, fornavn, etternavn, stilling, mndlon);
    }
}
	
