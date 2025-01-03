package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(100);
    }

    @Test
    void testRetrieveAllFoyers() {
        // Given
        Foyer foyer1 = new Foyer(1L, "Foyer A", 100, null, null);
        Foyer foyer2 = new Foyer(2L, "Foyer B", 200, null, null);
        List<Foyer> foyers = List.of(foyer1, foyer2);

        when(foyerRepository.findAll()).thenReturn(foyers);

        // When
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        // Given
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // When
        Foyer result = foyerService.retrieveFoyer(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdFoyer());
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testAddFoyer() {
        // Given
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // When
        Foyer result = foyerService.addFoyer(foyer);

        // Then
        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testModifyFoyer() {
        // Given
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // When
        Foyer result = foyerService.modifyFoyer(foyer);

        // Then
        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testRemoveFoyer() {
        // When
        foyerService.removeFoyer(1L);

        // Then
        verify(foyerRepository, times(1)).deleteById(1L);
    }
}
