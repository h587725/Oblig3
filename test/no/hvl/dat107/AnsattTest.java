package no.hvl.dat107;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnsattTest {


    AnsattDOA ansattDOA;

    public AnsattTest() {
        ansattDOA = new AnsattDOA();
    }

    @Test
    void hentAnsatte() {
        System.out.println("Henter ansatte...");
        List<Ansatt> ansatte = ansattDOA.hentAlle();
        assertNotNull(ansatte);
        assertTrue(ansatte.size() > 0);

        for (Ansatt ansatt : ansatte) {
            System.out.println(ansatt);
        }
    }
}