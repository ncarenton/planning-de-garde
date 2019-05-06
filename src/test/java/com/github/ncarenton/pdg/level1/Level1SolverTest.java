package com.github.ncarenton.pdg.level1;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.validation.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import static com.github.ncarenton.pdg.TestUtils.grabResource;

public class Level1SolverTest {

    private Level1Solver level1Solver;

    @Before
    public void setUp() {
        level1Solver = new Level1Solver();
    }

    @Test(expected = InvalidFormatException.class)
    public void solve_should_throw_InvalidFormatException() throws IOException {
        level1Solver.solve(
            uncheck(() -> Files.newInputStream(getResourcePath("level1/data_invalid_field_wrong_type.json"))),
            uncheck(ByteArrayOutputStream::new));
    }

    @Test(expected = ValidationException.class)
    public void solve_should_throw_ValidationException() throws IOException {
        level1Solver.solve(
            uncheck(() -> Files.newInputStream(getResourcePath("level1/data_invalid_field_right_type.json"))),
            uncheck(ByteArrayOutputStream::new));
    }

    @Test
    public void solve_should_work() throws IOException, JSONException {

        //Given
        OutputStream outputStream = new ByteArrayOutputStream();

        // When
        level1Solver.solve(
            uncheck(() -> Files.newInputStream(getResourcePath("level1/data.json"))),
            uncheck(() -> outputStream));

        // Then
        JSONAssert.assertEquals(grabResource("/level1/output.json"), outputStream.toString(), true);
    }

    private Path getResourcePath(String resource) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(resource);
        if (url == null) {
            throw new RuntimeException("url is null");
        }

        return Paths.get(url.toURI());
    }

    private <T> Supplier<T> uncheck(StreamSupplier<T> ioSupplier) {
        return () -> {
            try {
                return ioSupplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private interface StreamSupplier<T> {
        T get() throws IOException, URISyntaxException;
    }

}
