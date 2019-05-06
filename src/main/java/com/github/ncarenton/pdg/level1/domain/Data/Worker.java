package com.github.ncarenton.pdg.level1.domain.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class Worker {
    @NotNull
    @Min(1)
    Long id;

    @NotBlank
    String firstName;

    @NotNull
    @Min(1)
    Integer pricePerShift;

    public Worker(@JsonProperty("id") Long id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("price_per_shift") Integer pricePerShift) {
        this.id = id;
        this.firstName = firstName;
        this.pricePerShift = pricePerShift;
    }

}

