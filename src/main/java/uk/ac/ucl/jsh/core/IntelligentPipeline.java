package uk.ac.ucl.jsh.core;

import uk.ac.ucl.jsh.app.App;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IntelligentPipeline implements Pipeline {

    private List<PipelineBlock> pipelineBlockList;

    public IntelligentPipeline() {
        pipelineBlockList = new ArrayList<>();
    }

    @Override
    public void run() {
        pipelineBlockList.forEach(PipelineBlock::run);
    }
    @Override
    public void append(App app, String[] args) throws IOException {
        InputStream inputStream;
        OutputStream outputStream = new PipedOutputStream();

        if (pipelineBlockList.size() == 0) {
            inputStream = System.in;
        } else {
            inputStream = new PipedInputStream((PipedOutputStream) pipelineBlockList.get(pipelineBlockList.size() - 1).getOutputStream());
        }

        PipelineBlock pipelineBlock = new IntelligentBlock(app, inputStream, outputStream);
        pipelineBlock.setArgs(args);

        pipelineBlockList.add(pipelineBlock);
    }

    @Override
    public void setOutputStream(OutputStream outputStream) {
        PipelineBlock endBlock = pipelineBlockList.get(pipelineBlockList.size() - 1);
        endBlock.setOutputStream(outputStream);
    }
}
