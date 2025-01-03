package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EtudiantServiceImplTest {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertNotNull(etudiants);
        assertTrue(etudiants.size() > 0, "There should be at least one etudiant");
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Etudiant Test");
        etudiant.setCinEtudiant(12345);
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        assertNotNull(savedEtudiant);
        assertEquals("Etudiant Test", savedEtudiant.getNomEtudiant());
    }

    @Test
    void testModifyEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Etudiant Modify");
        etudiant.setCinEtudiant(54321);
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        savedEtudiant.setNomEtudiant("Etudiant Modified");
        Etudiant updatedEtudiant = etudiantService.modifyEtudiant(savedEtudiant);

        assertEquals("Etudiant Modified", updatedEtudiant.getNomEtudiant());
    }

    @Test
    void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Etudiant To Remove");
        etudiant.setCinEtudiant(67890);
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        etudiantService.removeEtudiant(savedEtudiant.getIdEtudiant());

        assertThrows(RuntimeException.class, () -> etudiantService.retrieveEtudiant(savedEtudiant.getIdEtudiant()));
    }
}
