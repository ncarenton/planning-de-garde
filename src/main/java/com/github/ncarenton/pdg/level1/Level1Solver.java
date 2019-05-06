package com.github.ncarenton.pdg.level1;

import com.github.ncarenton.pdg.AbstractLevelSolver;
import com.github.ncarenton.pdg.level1.domain.Data.Data;
import com.github.ncarenton.pdg.level1.domain.Output;

public class Level1Solver extends AbstractLevelSolver<Data, Output> {

    public Level1Solver() {
        super(Data.class);
    }

    @Override
    protected Output compute(Data data) {
        return Output.builder()
                     .workers(data.getWorkerPays())
                     .build();
    }

}
