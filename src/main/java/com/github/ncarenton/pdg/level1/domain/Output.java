package com.github.ncarenton.pdg.level1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Output {

    List<WorkerPay> workers;

    @Value
    @Builder
    public static class WorkerPay {

        Long id;

        Integer price;

        public WorkerPay(@JsonProperty("id") Long id, @JsonProperty("price") Integer price) {
            this.id = id;
            this.price = price;
        }
    }

    public Output(@JsonProperty("workers") List<WorkerPay> workers) {
        this.workers = workers;
    }
}
