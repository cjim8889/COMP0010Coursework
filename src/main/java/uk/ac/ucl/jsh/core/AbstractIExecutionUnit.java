package uk.ac.ucl.jsh.core;

import uk.ac.ucl.jsh.app.App;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractIExecutionUnit implements IExecutionUnit {
    private App app;
    private InputStream inputStream;
    private OutputStream outputStream;

    AbstractIExecutionUnit(App app, InputStream inputStream, OutputStream outputStream) {
        this.app = app;

        this.inputStream = inputStream;
        this.outputStream = outputStream;

        this.app.setInputStream(this.inputStream);
        this.app.setOutputStream(this.outputStream);
    }

    public void setArgs(String[] args) throws RuntimeException {
        app.setArgs(args);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        app.setOutputStream(outputStream);
    }

    App getApp() {
        return app;
    }
}
