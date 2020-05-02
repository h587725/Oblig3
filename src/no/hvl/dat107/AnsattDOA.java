package no.hvl.dat107;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;
import javax.management.OperationsException;
import javax.management.InvalidAttributeValueException;

public class AnsattDOA {

    private final EntityManagerFactory emf;

    public AnsattDOA() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }


    public Ansatt FinnMedId(int id) {


        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
       
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
    	EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Ansatt> query = em.createQuery("select a from Ansatt a where lower(a.brukernavn) like lower(:brukernavn)",
                    Ansatt.class);
            query.setParameter("brukernavn", brukernavn);
            if (query.getResultList().size() == 0) {
                return null;
            } else {
                return query.getSingleResult();

            }
        } finally {
            em.close();
        }
    }

    public int leggTil(Ansatt ansatt) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(ansatt);
            tx.commit();

            return ansatt.getId();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();

            return -1;
        } finally {
            em.close();
        }
    }


    public void oppdatermndlon(Ansatt ansatt) {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            String nymndlon = scanner.nextLine();
            ansatt.setMndlon(Integer.parseInt(nymndlon));

            em.getTransaction().commit();
            scanner.close();
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Ansatt> hentAlle() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT a FROM Ansatt a", Ansatt.class).getResultList();
        } finally {
            em.close();
        }
    }
}
