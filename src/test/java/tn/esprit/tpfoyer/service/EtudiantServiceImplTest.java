package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John");
        etudiant.setPrenomEtudiant("Doe");
        etudiant.setCinEtudiant(123456L);
        etudiant.setDateNaissance(new java.util.Date());
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Given
        Etudiant etudiant1 = new Etudiant(1L, "John", "Doe", 123456L, new java.util.Date(), null);
        Etudiant etudiant2 = new Etudiant(2L, "Jane", "Doe", 789012L, new java.util.Date(), null);
        List<Etudiant> etudiants = List.of(etudiant1, etudiant2);

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // When
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEtudiant() {
        // Given
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // When
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testAddEtudiant() {
        // Given
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Then
        assertNotNull(result);
        assertEquals(etudiant, result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testModifyEtudiant() {
        // Given
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Then
        assertNotNull(result);
        assertEquals(etudiant, result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRemoveEtudiant() {
        // When
        etudiantService.removeEtudiant(1L);

        // Then
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererEtudiantParCin() {
        // Given
        long cin = 123456L;
        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.recupererEtudiantParCin(cin);

        // Then
        assertNotNull(result);
        assertEquals(etudiant, result);
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(cin);
    }
}
