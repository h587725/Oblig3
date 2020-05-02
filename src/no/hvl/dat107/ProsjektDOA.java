package no.hvl.dat107;

import java.util.List;

import javax.management.OperationsException;
import javax.persistence.*;
import javax.management.OperationsException;
import javax.management.InvalidAttributeValueException;

import no.hvl.dat107.Prosjekt;

public class ProsjektDOA {

	private EntityManagerFactory emf;

	public ProsjektDOA() {
        emf = Persistence.createEntityManagerFactory("Oblig3");
	}
	
	public Prosjekt finnProsjektMedId(int prId) {
        EntityManager em = emf.createEntityManager();
		Prosjekt p = null;
		
        try {
            p = em.find(Prosjekt.class, prId);
        } finally {
            em.close();
		}
		
        return p;
	}
	
	public List<Prosjekt> hentAlleProsjekter() {
		EntityManager em = emf.createEntityManager();
		List<Prosjekt> prosjekter = null;

		try {
			return em.createQuery("SELECT p FROM Prosjekt p ORDER BY p.prId", Prosjekt.class).getResultList();
		} finally {
			em.close();
		}
		
		
	}

    public int leggTil(Prosjekt prosjekt) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(prosjekt);

            tx.commit();
            return prosjekt.getprId();

        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return -1;
        } finally {
            em.close();
        }

    }

	public void oppdaterProsjekt(Prosjekt p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.merge(p);
			tx.commit();
		} finally {
			em.close();
		}
	}

	public boolean fjern(int prId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Prosjekt prosjekt = em.find(Prosjekt.class, prId);

            if (prosjekt.getDeltagere().size() > 0) {
                throw new OperationsException("Prøver å fjerne prosjekt som har deltagelse.");
            }

            em.remove(prosjekt);
            tx.commit();
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
        return false;
    }
}