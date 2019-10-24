package uk.ac.ucl.jsh.core;

import uk.ac.ucl.jsh.app.App;

import java.io.InputStream;
import java.io.OutputStream;

public class ExecutionUnit extends AbstractIExecutionUnit implements IExecutionUnit {
    ExecutionUnit(App app, InputStream inputStream, OutputStream outputStream) {
        super(app, inputStream, outputStream);
    }

    @Override
    public void run() {
        getApp().run();
    }
}
