package com.college;

import static com.college.support.ScheduleTestDataBuilder.aSchedule;
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

class ScheduleControllerTest {

    @Test
    void viewScheduleReturnsScheduleViewName() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.viewSchedule(model);

        assertEquals("schedule", viewName);
    }

    @Test
    void viewScheduleAddsAllSchedulesToModel() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        Schedule schedule = aSchedule().build();
        when(repository.findAll()).thenReturn(List.of(schedule));
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        controller.viewSchedule(model);

        verify(repository, times(1)).findAll();
        assertNotNull(model.getAttribute("schedules"));
        assertEquals(1, ((List<?>) model.getAttribute("schedules")).size());
    }

    @Test
    void showAddFormReturnsAddViewName() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showAddForm(model);

        assertEquals("add", viewName);
    }

    @Test
    void showAddFormAddsEmptyScheduleToModel() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        controller.showAddForm(model);

        assertNotNull(model.getAttribute("schedule"));
    }

    @Test
    void addScheduleSavesToRepositoryAndRedirects() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Schedule schedule = aSchedule().build();

        String viewName = controller.addSchedule(schedule);

        verify(repository, times(1)).save(schedule);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void deleteScheduleRemovesFromRepositoryAndRedirects() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);

        String viewName = controller.deleteSchedule("id-123");

        verify(repository, times(1)).deleteById("id-123");
        assertEquals("redirect:/", viewName);
    }
}
