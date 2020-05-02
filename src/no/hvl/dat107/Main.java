package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

		public static void main(String[] args) {
			Grensesnitt gr = new Grensesnitt();
			gr.start();
		}
}

