package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class AbstractApp implements App {

    Core jshCore;
    private InputStream inputStream;
    private OutputStream outputStream;
    private OutputStreamWriter outputStreamWriter;

    AbstractApp(Core jshCore) {
        this.jshCore = jshCore;

        //Input stream is default to be Sys.in
        //Output stream is default to be Sys.out unless otherwise defined
        //Err output stream is default to be Sys.err
        inputStream = System.in;
        outputStream = System.out;

        outputStreamWriter = new OutputStreamWriter(outputStream);
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

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public abstract void run() throws RuntimeException;
    public abstract void setArgs(String[] args) throws RuntimeException;
}
