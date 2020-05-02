package no.hvl.dat107;

public class AnsattProsjektPK {
	private int ansattId;
	private int prosjektId;

	public AnsattProsjektPK(Ansatt a, Prosjekt p) {
		this.ansattId = a.getId();
		this.prosjektId = p.getprId();
	}

}
