package no.hvl.dat107;

	import javax.persistence.*;


	public class AnsattProsjekt {

		@Id
		@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
		@JoinColumn(name = "ansId")
		private Ansatt ansatt;

		@Id
		@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
		@JoinColumn(name = "prId")
		private Prosjekt prosjekt;

		private int timer;
		private String rolle;

		public AnsattProsjekt() {
			this(null, null, 0, "Rolle");
		}

		public AnsattProsjekt(Ansatt a, Prosjekt p, int t, String r) {
			this.ansatt = a;
			this.prosjekt = p;
			this.timer = t;
			this.rolle = r;
		}

		public int getTimer() {
			return this.timer;
		}

		public void setTimer(int timer) {
			this.timer = timer;
		}

		public String getRolle() {
			return this.rolle;
		}

		public void setRolle(String rolle) {
			this.rolle = rolle;
		} 

		@Override
		public String toString() {
			return "String.format(\"AnsattProsjekt: ansatt, prosjekt, timer, rolle";
		}
}

