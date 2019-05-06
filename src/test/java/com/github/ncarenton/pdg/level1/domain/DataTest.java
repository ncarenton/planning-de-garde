package com.github.ncarenton.pdg.level1.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ncarenton.pdg.level1.domain.Data.Data;
import com.github.ncarenton.pdg.level1.domain.Output.WorkerPay;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.github.ncarenton.pdg.TestUtils.getResourceAsStream;
import static com.github.ncarenton.pdg.utils.Utils.commonObjectMapper;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DataTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = commonObjectMapper();
    }

    @Test
    public void getWorkerPays_should_work_default_data() throws IOException {

        // Given
        Data data = objectMapper.readValue(getResourceAsStream("/level1/data.json"), Data.class);

        // When
        List<WorkerPay> workerPays = data.getWorkerPays();

        // Then
        assertThat(workerPays).isEqualTo(objectMapper.readValue(getResourceAsStream("/level1/output.json"), Output.class).getWorkers());
    }

    @Test
    public void getWorkerPays_should_work_missing_worker() throws IOException {

        // Given
        Data data = objectMapper.readValue(getResourceAsStream("/level1/data_missing_worker.json"), Data.class);

        // When
        List<WorkerPay> workerPays = data.getWorkerPays();

        // Then
        assertThat(workerPays).isEqualTo(objectMapper.readValue(getResourceAsStream("/level1/output_missing_worker.json"), Output.class).getWorkers());
    }

    @Test
    public void getWorkerPays_should_work_empty_shifts() throws IOException {

        // Given
        Data data = objectMapper.readValue(getResourceAsStream("/level1/data_empty_shifts.json"), Data.class);

        // When
        List<WorkerPay> workerPays = data.getWorkerPays();

        // Then
        assertThat(workerPays).isEqualTo(objectMapper.readValue(getResourceAsStream("/level1/output_empty_shifts.json"), Output.class).getWorkers());
    }

    @Test
    public void getWorkerPays_should_work_empty_workers() throws IOException {

        // Given
        Data data = objectMapper.readValue(getResourceAsStream("/level1/data_empty_workers.json"), Data.class);

        // When
        List<WorkerPay> workerPays = data.getWorkerPays();

        // Then
        assertThat(workerPays).isEmpty();
    }

    @Test
    public void getWorkerPays_should_work_duplicate_workers_duplicate_shifts() throws IOException {

        // Given
        Data data = objectMapper
            .readValue(getResourceAsStream("/level1/data_duplicate_workers_duplicate_shifts.json"), Data.class);

        // When
        List<WorkerPay> workerPays = data.getWorkerPays();

        // Then
        assertThat(workerPays).isEqualTo(objectMapper.readValue(getResourceAsStream("/level1/output.json"), Output.class).getWorkers());
    }

}
