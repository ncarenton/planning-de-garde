package com.github.ncarenton.pdg.level1.domain.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ncarenton.pdg.level1.domain.Output.WorkerPay;
import lombok.Value;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class Data {

    private static final long DEFAULT_SHIFT_COUNT = 0;

    @Valid
    private final Set<Worker> workers;

    @Valid
    private final Set<Shift> shifts;

    public Data(
        @JsonProperty("workers") @Valid Set<Worker> workers,
        @JsonProperty("shifts") @Valid Set<Shift> shifts) {
        this.workers = workers;
        this.shifts = shifts;
    }

    public List<WorkerPay> getWorkerPays() {
        Map<Long, Long> userIdToShiftCount = getShiftCountByWorker();

        return workers
            .stream()
            .map(w -> WorkerPay.builder()
                               .id(w.getId())
                               .price(userIdToShiftCount.getOrDefault(w.getId(), DEFAULT_SHIFT_COUNT).intValue()
                                      * w.getPricePerShift())
                               .build())
            .sorted(Comparator.comparing(WorkerPay::getId))
            .collect(Collectors.toList());
    }

    private Map<Long, Long> getShiftCountByWorker() {
        return shifts
            .stream()
            .collect(Collectors.groupingBy(Shift::getUserId, Collectors.counting()));
    }

}
