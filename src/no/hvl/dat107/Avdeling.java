package no.hvl.dat107;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdId;

	private String navn;

	@OneToOne
	@JoinColumn(name = "sjef", referencedColumnName = "id")
	private Ansatt sjef;

	
	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private final Set<Ansatt> ansatte;
	
	
	public Avdeling() {
		ansatte = new HashSet<>();
	}

	public Avdeling(int id, String navn, Ansatt sjef) {
		this.avdId = id;
		this.navn = navn;
		ansatte = new HashSet<>();
		ansatte.add(sjef);
	}

	public int getId() {
		return this.avdId;
	}
	public Ansatt getSjef() {
		return sjef;
	}
	
	public Set<Ansatt> getAnsatte() {
		return ansatte;
	}

	@Override
    public String toString() {
        var sb = new StringBuilder(String.format("%s(avdID: %d)\n", navn, avdId));
        sb.append(String.format("Sjef: %s %s\n", sjef.getFornavn(), sjef.getEtternavn()));
        for (Ansatt ansatt : ansatte) {
            sb.append(ansatt).append("\n");
        }

        return sb.toString();
    }
}