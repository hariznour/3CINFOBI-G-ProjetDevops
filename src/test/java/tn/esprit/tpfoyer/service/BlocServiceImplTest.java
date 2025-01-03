package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Bloc;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlocServiceImplTest {

    @Autowired
    private BlocServiceImpl blocService;

    @Test
    void testRetrieveAllBlocs() {
        List<Bloc> blocs = blocService.retrieveAllBlocs();
        assertNotNull(blocs);
        assertTrue(blocs.size() > 0, "There should be at least one bloc");
    }

    @Test
    void testAddBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc Test");
        bloc.setCapaciteBloc(100);
        Bloc savedBloc = blocService.addBloc(bloc);
        assertNotNull(savedBloc);
        assertEquals("Bloc Test", savedBloc.getNomBloc());
    }

    @Test
    void testModifyBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc Modify");
        bloc.setCapaciteBloc(150);
        Bloc savedBloc = blocService.addBloc(bloc);

        savedBloc.setCapaciteBloc(200);
        Bloc updatedBloc = blocService.modifyBloc(savedBloc);

        assertEquals(200, updatedBloc.getCapaciteBloc());
    }

    @Test
    void testRemoveBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc To Remove");
        bloc.setCapaciteBloc(120);
        Bloc savedBloc = blocService.addBloc(bloc);

        blocService.removeBloc(savedBloc.getIdBloc());

        assertThrows(RuntimeException.class, () -> blocService.retrieveBloc(savedBloc.getIdBloc()));
    }
}

