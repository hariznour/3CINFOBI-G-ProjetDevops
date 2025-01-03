package tn.esprit.tpfoyer.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import java.util.Optional;

public class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Création d'un objet Chambre pour les tests
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE); // Assurez-vous d'utiliser un type valide

        // Simuler le comportement du repository pour certaines méthodes
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));
    }

    @Test
    public void testRetrieveChambreById() {
        // Test de la méthode retrieveChambre
        Chambre retrievedChambre = chambreService.retrieveChambre(1L);

        assertNotNull(retrievedChambre);
        assertEquals(1L, retrievedChambre.getIdChambre());
        assertEquals(101, retrievedChambre.getNumeroChambre());
    }

    @Test
    public void testAddChambre() {
        // Test de la méthode addChambre
        Chambre newChambre = new Chambre();
        newChambre.setNumeroChambre(102);
        newChambre.setTypeC(TypeChambre.DOUBLE);

        when(chambreRepository.save(newChambre)).thenReturn(newChambre);

        Chambre addedChambre = chambreService.addChambre(newChambre);

        assertNotNull(addedChambre);
        assertEquals(102, addedChambre.getNumeroChambre());
    }

    @Test
    public void testModifyChambre() {
        // Test de la méthode modifyChambre
        chambre.setNumeroChambre(103);

        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre modifiedChambre = chambreService.modifyChambre(chambre);

        assertNotNull(modifiedChambre);
        assertEquals(103, modifiedChambre.getNumeroChambre());
    }

    @Test
    public void testRemoveChambre() {
        // Test de la méthode removeChambre
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        // Vérifier que la méthode deleteById a été appelée avec le bon argument
        verify(chambreRepository, times(1)).deleteById(1L);
    }
}
