package uk.ac.ucl.jsh.core;

import org.junit.jupiter.api.Test;
import uk.ac.ucl.jsh.app.App;
import uk.ac.ucl.jsh.app.Cd;
import uk.ac.ucl.jsh.app.Ls;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IntelligentPipeline implements Pipeline {

    private List<IExecutionUnit> IExecutionUnitList;

    public IntelligentPipeline() {
        IExecutionUnitList = new ArrayList<>();
    }

    @Override
    public void run() {
        IExecutionUnitList.forEach(IExecutionUnit::run);
    }

    @Override
    public void append(App app, String[] args) throws IOException {
        InputStream inputStream;
        OutputStream outputStream = new PipedOutputStream();

        if (IExecutionUnitList.size() == 0) {
            inputStream = System.in;
        } else {
            inputStream = new PipedInputStream((PipedOutputStream) IExecutionUnitList.get(IExecutionUnitList.size() - 1).getOutputStream());
        }

        IExecutionUnit IExecutionUnit = new ExecutionUnit(app, inputStream, outputStream);
        IExecutionUnit.setArgs(args);

        IExecutionUnitList.add(IExecutionUnit);
    }

    @Override
    public void setOutputStream(OutputStream outputStream) {
        IExecutionUnit endBlock = IExecutionUnitList.get(IExecutionUnitList.size() - 1);
        endBlock.setOutputStream(outputStream);
    }
}
