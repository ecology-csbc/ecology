package com.ecology;

import static com.ecology.support.EcologyTestDataBuilder.anEcology;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class EcologyControllerTest {

    @Test
    void viewEcologyReturnsEcologyViewName() {
        EcologyRepository repository = mock(EcologyRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        EcologyController controller = new EcologyController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.viewEcology(model);

        assertEquals("ecology", viewName);
    }

    @Test
    void viewEcologyAddsAllEcologiesToModel() {
        EcologyRepository repository = mock(EcologyRepository.class);
        Ecology ecology = anEcology().build();
        when(repository.findAll()).thenReturn(List.of(ecology));
        EcologyController controller = new EcologyController(repository);
        Model model = new ExtendedModelMap();

        controller.viewEcology(model);

        verify(repository, times(1)).findAll();
        assertNotNull(model.getAttribute("ecologies"));
        assertEquals(1, ((List<?>) model.getAttribute("ecologies")).size());
    }

    @Test
    void showAddFormReturnsAddViewName() {
        EcologyRepository repository = mock(EcologyRepository.class);
        EcologyController controller = new EcologyController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showAddForm(model);

        assertEquals("add", viewName);
    }

    @Test
    void showAddFormAddsEmptyEcologyToModel() {
        EcologyRepository repository = mock(EcologyRepository.class);
        EcologyController controller = new EcologyController(repository);
        Model model = new ExtendedModelMap();

        controller.showAddForm(model);

        assertNotNull(model.getAttribute("ecology"));
    }

    @Test
    void addEcologySavesToRepositoryAndRedirects() {
        EcologyRepository repository = mock(EcologyRepository.class);
        EcologyController controller = new EcologyController(repository);
        Ecology ecology = anEcology().build();

        String viewName = controller.addEcology(ecology);

        verify(repository, times(1)).save(ecology);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void deleteEcologyRemovesFromRepositoryAndRedirects() {
        EcologyRepository repository = mock(EcologyRepository.class);
        EcologyController controller = new EcologyController(repository);

        String viewName = controller.deleteEcology("id-123");

        verify(repository, times(1)).deleteById("id-123");
        assertEquals("redirect:/", viewName);
    }
}
