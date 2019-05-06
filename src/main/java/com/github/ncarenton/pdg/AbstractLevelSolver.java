package com.github.ncarenton.pdg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.function.Supplier;

import static com.github.ncarenton.pdg.utils.Utils.commonObjectMapper;

@Slf4j
public abstract class AbstractLevelSolver<Data, Output> {

    private final ObjectMapper objectMapper;

    private final Validator validator;

    private final Class<Data> clazz;

    public AbstractLevelSolver(Class<Data> clazz) {
        objectMapper = commonObjectMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        this.clazz = clazz;
    }

    private Data deserialize(InputStream inputStream, Class<Data> clazz) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

    private void validate(Data data) {
        Set<ConstraintViolation<Data>> violations = validator.validate(data);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.toString());
        }
    }

    protected abstract Output compute(Data object);

    private String serialize(Output output) throws JsonProcessingException {
        return objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(output);
    }

    private void write(OutputStream outputStream, String output) throws IOException {
        outputStream.write(output.getBytes());
    }

    public void solve(Supplier<InputStream> inputStream, Supplier<OutputStream> outputStream) throws IOException, ValidationException {

        log.info("Data: \n" + IOUtils.toString(inputStream.get()));

        Data data = deserialize(inputStream.get(), clazz);
        validate(data);
        Output output = compute(data);
        String json = serialize(output);
        write(outputStream.get(), json);

        log.info("Output: \n" + json);
    }
}
