package com.retail;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class RetailApplicationTest {
    private final InputStreamState inputStreamState = new InputStreamState();
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        inputStreamState.restore();
        outputStreamState.restore();
    }

    @Test
    void addOrdersFromCsvReplacesExistingDataAndSavesParsedRows() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        RetailApplication app = appWithRepository(repository);

        invokePrivate(app, "addOrdersFromCsv");

        verify(repository, times(1)).deleteAll();
        verify(repository, times(1)).saveAll(anyList());
        assertTrue(outputStreamState.value().contains("CSV"));
    }

    @Test
    void addOrdersFromCsvPrintsFailureMessageWhenRepositoryThrows() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        doThrow(new RuntimeException("repository unavailable")).when(repository).deleteAll();
        RetailApplication app = appWithRepository(repository);

        invokePrivate(app, "addOrdersFromCsv");

        assertTrue(outputStreamState.value().contains("CSV"));
        verify(repository, times(1)).deleteAll();
    }

    @Test
    void viewAllOrdersPrintsNotFoundForEmptyRepository() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        RetailApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllOrders");

        assertTrue(outputStreamState.value().contains("не знайдено"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void viewAllOrdersPrintsRowsWhenRepositoryHasData() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        RetailOrder order = new com.retail.support.RetailOrderTestDataBuilder().build();
        when(repository.findAll()).thenReturn(List.of(order));
        RetailApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllOrders");

        String output = outputStreamState.value();
        assertTrue(output.contains("Знайдено 1"));
        assertTrue(output.contains("RetailOrder {"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void dropAllOrdersDeletesDataAndPrintsMessage() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        RetailApplication app = appWithRepository(repository);

        invokePrivate(app, "dropAllOrders");

        verify(repository, times(1)).deleteAll();
        assertTrue(outputStreamState.value().contains("видалено"));
    }

    @Test
    void runExecutesChoicesAndStopsWhenInputIsExhausted() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        RetailApplication app = appWithRepository(repository);
        inputStreamState.replace("1\n2\n3\nx\n");

        assertThrows(NoSuchElementException.class, () -> app.run());

        verify(repository, atLeast(2)).deleteAll();
        verify(repository, times(1)).saveAll(anyList());
        verify(repository, times(1)).findAll();
    }

    @Test
    void runPrintsMessageForOutOfRangeChoice() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        RetailApplication app = appWithRepository(repository);
        inputStreamState.replace("5\nx\n");

        assertThrows(NoSuchElementException.class, () -> app.run());

        assertTrue(outputStreamState.value().contains("некоректний"));
    }

    @Test
    void runPrintsValidationMessageForNonNumericInput() throws Exception {
        RetailOrderRepository repository = mock(RetailOrderRepository.class);
        RetailApplication app = appWithRepository(repository);
        inputStreamState.replace("abc\n");

        assertThrows(NoSuchElementException.class, () -> app.run());

        String output = outputStreamState.value();
        assertTrue(output.contains("Некоректне введення"));
        assertTrue(output.contains("1 до 4"));
    }

    private static RetailApplication appWithRepository(RetailOrderRepository repository) throws Exception {
        RetailApplication app = new RetailApplication();
        Field field = RetailApplication.class.getDeclaredField("retailOrderRepository");
        field.setAccessible(true);
        field.set(app, repository);
        return app;
    }

    private static void invokePrivate(RetailApplication app, String methodName) throws Exception {
        Method method = RetailApplication.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(app);
    }

    private static final class InputStreamState {
        private final java.io.InputStream original = System.in;

        private void replace(String input) {
            System.setIn(new ByteArrayInputStream(input.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
        }

        private void restore() {
            System.setIn(original);
        }
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
