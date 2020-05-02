package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Grensesnitt {

    private final AnsattDOA ansattDOA;
    private final AvdelingDOA avdelingDOA;
    private final ProsjektDOA prosjektDOA;
    private final AnsattProsjektDOA ansattProsjektDOA;
    private final Scanner input;


    public Grensesnitt() {
        ansattDOA = new AnsattDOA();
        avdelingDOA = new AvdelingDOA();
        prosjektDOA = new ProsjektDOA();
        ansattProsjektDOA = new AnsattProsjektDOA();
        
        input = new Scanner(System.in);
        
    }

    public void start() {


        System.out.println("Obligatorisk innlevering 3\n");

        Ansatt test = ansattDOA.FinnMedId(1);
        System.out.println(test.getBrukernavn());


        boolean done = false;
        while (!done) {
            done = this.meny();
        }
    }


    private boolean meny() {
        System.out.println("FÃ¸lgende valg er tilgjengelige:\n");

        System.out.println("  1. Søk etter ansatt ved id");
        System.out.println("  2. Søk etter ansatt ved brknavn");
        System.out.println("  3. List ansatte");
        System.out.println("  4. List avdelinger");
        System.out.println("  5. Legg til ansatt");
        System.out.println("  6. Legg til avdeling");
        System.out.println("  7. Oppdater lønn til ansatt");
        System.out.println("  8. List alle prosjekter");
        System.out.println(" 0. Avslutt program");

        System.out.println("\nSkriv inn tallet for ditt valg");

        int valg;

        try {
            valg = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("\nSkriv inn et tall.\n");
            return false;
        }

        switch (valg) {
        	case 1:
        		finnMedID();
        		break;
        	case 2:
        		finnMedBrukernavn();
        		break;
            case 3:
                listAnsatte();
                break;
            case 4:
            	avdelingDOA.ListAlleAvdelinger();
                break;
            case 5:
                leggTilAnsatt();
                break;
            case 6:
                
                break;
            case 7:
            	endreAnsattLonn();
            	break;
            case 8:
            	prosjektDOA.hentAlleProsjekter();
            case 0: 
            	System.out.println("Avslutter");
            	return true;
            default:
                System.out.println("\nUgyldig valg, prov igjen...\n");
        }
        return false;
    }

    private void listAnsatte() {
		List<Ansatt> ansatte = ansattDOA.hentAlle();
		for (Ansatt ansatt : ansatte) {
			System.out.println(ansatt);
		}
    }



    private void leggTilAnsatt() {

        System.out.println("Legg til ansatt\n");

        System.out.println("Fornavn?");
        String fornavn = input.nextLine();

        System.out.println("Etternavn?");
        String etternavn = input.nextLine();

        System.out.println("Brukernavn?");
        String brukernavn = input.nextLine();

        System.out.println("Stilling?");
        String stilling = input.nextLine();

        System.out.println("MÃ¥nedslÃ¸nn?");
        int lonn = -1;

        while (lonn < 0) {
            try {
                lonn = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Skriv inn et gyldig tall.");
            }
        }


        Ansatt ansatt = new Ansatt(brukernavn, fornavn, etternavn, LocalDate.now(), stilling, lonn);
        int ansattId = ansattDOA.leggTil(ansatt);

        if (ansattId == -1) {
			System.out.println("Noe gikk feil");
		} else {
			System.out.printf("La til ansatt med id=%d:\n", ansattId);
			System.out.println(ansatt);
		}
		System.out.println("Trykk enter for og fortsette...");
        input.nextLine();
    }
    
    private void finnMedID() {
    	System.out.println("Hvilken ansatt skal du finne(bruk idnmr)\n");
    	int id = validertInput();
    	Ansatt ansatt = ansattDOA.FinnMedId(id);
    	System.out.println(ansatt);
    }
   private void finnMedBrukernavn() {
	   Ansatt ansatt = null;
	   System.out.println("Brukernavn?");
	   String brukernavn = input.nextLine();
	   ansatt = ansattDOA.finnAnsattMedBrukernavn(brukernavn);
	   if (ansatt == null) { 
		   System.out.println("Fant ikkje ansatt");
	   }else {
		   System.out.println(ansatt);
	   }
   }
    
    private void endreAnsattLonn() {
    	System.out.println("Hvilken ansatt skal han ny lonn?(bruk idnmr)\n");
    	int id = validertInput();
    	Ansatt ansatt = ansattDOA.FinnMedId(id);
    	if (ansatt == null) { 
    		System.out.println("fant ikkje ansatt med id = " + id);
    	} else {
    		ansattDOA.oppdatermndlon(ansatt);
    		System.out.println("Oppdatert lønn for id =" + id);
    	}
    	
    }
    
   
    
    private int validertInput() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
    
    private void pressEnter() {
        System.out.println("\nTrykk enter for å fortsette...");
        input.nextLine();

    }
}
