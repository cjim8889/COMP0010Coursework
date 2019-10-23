package uk.ac.ucl.jsh.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {
    private List<Pipeline> pipelineList;

    Executor() {
        pipelineList = new ArrayList<>();
    }

    public void append(Pipeline pipeline) {
        pipelineList.add(pipeline);
    }

    public void appendAll(Pipeline[] pipelines) {
        pipelineList.addAll(Arrays.asList(pipelines));
    }


    //Blocking as I do not know how to write concurrent java programme.
    public void execute() {
        pipelineList.forEach(Pipeline::run);
    }
}
