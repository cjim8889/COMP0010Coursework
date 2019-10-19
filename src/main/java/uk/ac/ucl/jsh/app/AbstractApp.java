package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class AbstractApp implements App {

    Core jshCore;
    private InputStream inputStream;
    private OutputStream outputStream, errOutputStream;
    private OutputStreamWriter outputStreamWriter, errOutputStreamWriter;
    int exitStatus = -1;

    AbstractApp(Core jshCore) {
        this.jshCore = jshCore;

        //Input stream is default to be Sys.in
        //Output stream is default to be Sys.out unless otherwise defined
        //Err output stream is default to be Sys.err
        inputStream = System.in;
        outputStream = System.out;
        errOutputStream = System.err;

        outputStreamWriter = new OutputStreamWriter(outputStream);
        errOutputStreamWriter = new OutputStreamWriter(errOutputStream);
    }

    public void execute() {
        if (exitStatus != -1) {
            return;
        }

        run();
    }


    void writeOutputStream(String content) throws IOException {
        if (content.isEmpty()) {
            return;
        }

        outputStreamWriter.write(content);
        outputStreamWriter.flush();
    }

    void writeOutputStreamLn(String content) {
        try {
            writeOutputStream(content + jshCore.getLineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeErrStream(String err) throws IOException {
        if (err.isEmpty()) {
            return;
        }

        errOutputStreamWriter.write(err);
        errOutputStreamWriter.flush();
    }

    void writeErrStreamLn(String err) {
        if (err.isEmpty()) {
            return;
        }

        try {
            writeErrStream(this.getClass().getSimpleName() + ": " + err + jshCore.getLineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public void setErrOutputStream(OutputStream errOutputStream) {
        this.errOutputStream = errOutputStream;
        errOutputStreamWriter = new OutputStreamWriter(errOutputStream);
    }

    void exit(int status) {
        exitStatus = status;
        jshCore.registerTermination(this, exitStatus);
    }


    public abstract void run() throws RuntimeException;
    public abstract void setArgs(String[] args) throws IllegalArgumentException;
}
