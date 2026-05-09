package com.college;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class CollegeApplicationTest {
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        outputStreamState.restore();
    }

    @Test
    void viewAllSchedulesPrintsNotFoundForEmptyRepository() throws Exception {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        CollegeApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllSchedules");

        assertTrue(outputStreamState.value().contains("не знайдено"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void viewAllSchedulesPrintsRowsWhenRepositoryHasData() throws Exception {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        Schedule schedule = new com.college.support.ScheduleTestDataBuilder().build();
        when(repository.findAll()).thenReturn(List.of(schedule));
        CollegeApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllSchedules");

        String output = outputStreamState.value();
        assertTrue(output.contains("Знайдено 1"));
        assertTrue(output.contains("Schedule {"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void dropAllSchedulesDeletesDataAndPrintsMessage() throws Exception {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        CollegeApplication app = appWithRepository(repository);

        invokePrivate(app, "dropAllSchedules");

        verify(repository, times(1)).deleteAll();
        assertTrue(outputStreamState.value().contains("видалено"));
    }

    private static CollegeApplication appWithRepository(ScheduleRepository repository) throws Exception {
        CollegeApplication app = new CollegeApplication();
        Field field = CollegeApplication.class.getDeclaredField("scheduleRepository");
        field.setAccessible(true);
        field.set(app, repository);
        return app;
    }

    private static void invokePrivate(CollegeApplication app, String methodName) throws Exception {
        Method method = CollegeApplication.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(app);
    }

    private static final class OutputStreamState {
        private final PrintStream original = System.out;
        private final ByteArrayOutputStream captured = new ByteArrayOutputStream();

        private OutputStreamState() {
            System.setOut(new PrintStream(captured, true, java.nio.charset.StandardCharsets.UTF_8));
        }

        private String value() {
            return captured.toString(java.nio.charset.StandardCharsets.UTF_8);
        }

        private void restore() {
            System.setOut(original);
        }
    }
}
