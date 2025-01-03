package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testRetrieveAllChambres() {
        // Given
        Chambre chambre1 = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
        Chambre chambre2 = new Chambre(2L, 102, TypeChambre.DOUBLE, null, null);
        List<Chambre> chambres = List.of(chambre1, chambre2);

        when(chambreRepository.findAll()).thenReturn(chambres);

        // When
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();  // Verify repository interaction
    }

    @Test
    void testRetrieveChambre() {
        // Given
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // When
        Chambre result = chambreService.retrieveChambre(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testAddChambre() {
        // Given
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre result = chambreService.addChambre(chambre);

        // Then
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testModifyChambre() {
        // Given
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // When
        Chambre result = chambreService.modifyChambre(chambre);

        // Then
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        // When
        chambreService.removeChambre(1L);

        // Then
        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Given
        Chambre chambre1 = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
        Chambre chambre2 = new Chambre(2L, 102, TypeChambre.SIMPLE, null, null);
        List<Chambre> chambres = List.of(chambre1, chambre2);

        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(chambres);

        // When
        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Given
        long cin = 123456L;
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        // When
        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);

        // Then
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}
