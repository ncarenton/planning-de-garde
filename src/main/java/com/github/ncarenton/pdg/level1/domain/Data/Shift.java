package com.github.ncarenton.pdg.level1.domain.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class Shift {
    @NotNull
    @Min(1)
    Long id;

    @NotNull
    @Min(1)
    Long planningId;

    @NotNull
    @Min(1)
    Long userId;

    @NotNull
    LocalDate startDate;

    public Shift(@JsonProperty("id") Long id,
                 @JsonProperty("planning_id") Long planningId,
                 @JsonProperty("user_id") Long userId,
                 @JsonFormat(pattern = "yyyy-M-d")
                 @JsonProperty("start_date") LocalDate startDate) {
        this.id = id;
        this.planningId = planningId;
        this.userId = userId;
        this.startDate = startDate;
    }
}
