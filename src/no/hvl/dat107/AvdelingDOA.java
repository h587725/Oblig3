
package no.hvl.dat107;

import javax.persistence.*;
import java.util.List;



public class AvdelingDOA {

    private final EntityManagerFactory emf;


    public AvdelingDOA() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }

    public Avdeling finnAvdelingMedId(int avdId) {

        EntityManager em = emf.createEntityManager();
        Avdeling a = null;

        try {
            a = em.find(Avdeling.class, avdId);
        } finally {
            em.close();
        }

        return a;
    }

    public int leggTilAvdeling(String navn, Ansatt nySjef) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Avdeling nyAvd = null;

        try {
            tx.begin();

            nySjef = em.merge(nySjef);

            em.persist(nyAvd);

            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        return nyAvd.getId();
    }

    public List<Avdeling> ListAlleAvdelinger() {

        EntityManager em = emf.createEntityManager();

        List<Avdeling> Avdelinger = null;
        try {
            TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a ORDER BY a.avdId", Avdeling.class);
            Avdelinger = query.getResultList();
        } finally {
            em.close();
        }
        return Avdelinger;
    }
    
    public void oppdaterAvdeling(Avdeling a) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(a);
			
			em.getTransaction().commit();
		} catch(Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
    

	public void oppdaterNySjef(Avdeling avdeling, Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			ansatt = em.merge(ansatt);
			avdeling = em.merge(avdeling);
			
			//avdeling.setSjef(ansatt);
			
			tx.commit();
		} finally {
			em.close();
		}
	}
    
	public void fjernAvdeling(Avdeling avdeling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			System.out.print("Sletter avdeling...");
			tx.begin();
			
			avdeling = em.merge(avdeling);
			em.remove(avdeling);
			
			tx.commit();
			System.out.println("OK!");
		} finally {
			em.close();
		}
	}
}