package uk.ac.ucl.jsh.core;

public interface Pipeline {
    void run();
    void append(PipelineBlock pipelineBlock);
}
