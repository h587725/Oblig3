package no.hvl.dat107;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


public class Prosjekt {
	
	@Id
	private int prId;
	private String navn;
	private String beskrivelse;


	@OneToMany(mappedBy = "prosjekt")
	public Set<AnsattProsjekt> deltagere = new HashSet<>();

	public Prosjekt() {		
	}
	
	
	public Prosjekt(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		
	}
	
	public int getprId() {
		return prId;
	}
	
	public String getNavn( ) {
		return navn;
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}

    public Set<AnsattProsjekt> getDeltagere() {
        return deltagere;
    }


    @Override
    public String toString() {
        return "Prosjekt{" +
                "prId=" + prId +
                ", navn='" + navn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                '}';
    }
}