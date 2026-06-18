package com.ecology;

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

class EcologyApplicationTest {
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        outputStreamState.restore();
    }

    @Test
    void viewAllEcologiesPrintsNotFoundForEmptyRepository() throws Exception {
        EcologyRepository repository = mock(EcologyRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        EcologyApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllEcologies");

        assertTrue(outputStreamState.value().contains("не знайдено"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void viewAllEcologiesPrintsRowsWhenRepositoryHasData() throws Exception {
        EcologyRepository repository = mock(EcologyRepository.class);
        Ecology ecology = new com.ecology.support.EcologyTestDataBuilder().build();
        when(repository.findAll()).thenReturn(List.of(ecology));
        EcologyApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllEcologies");

        String output = outputStreamState.value();
        assertTrue(output.contains("Знайдено 1"));
        assertTrue(output.contains("Ecology {"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void dropAllEcologiesDeletesDataAndPrintsMessage() throws Exception {
        EcologyRepository repository = mock(EcologyRepository.class);
        EcologyApplication app = appWithRepository(repository);

        invokePrivate(app, "dropAllEcologies");

        verify(repository, times(1)).deleteAll();
        assertTrue(outputStreamState.value().contains("видалено"));
    }

    private static EcologyApplication appWithRepository(EcologyRepository repository) throws Exception {
        EcologyApplication app = new EcologyApplication();
        Field field = EcologyApplication.class.getDeclaredField("ecologyRepository");
        field.setAccessible(true);
        field.set(app, repository);
        return app;
    }

    private static void invokePrivate(EcologyApplication app, String methodName) throws Exception {
        Method method = EcologyApplication.class.getDeclaredMethod(methodName);
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
