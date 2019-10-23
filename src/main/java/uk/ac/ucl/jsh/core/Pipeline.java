package uk.ac.ucl.jsh.core;

import uk.ac.ucl.jsh.app.App;

import java.io.IOException;
import java.io.OutputStream;

public interface Pipeline {
    void run();
    void append(App app, String[] args) throws IOException;
    void setOutputStream(OutputStream outputStream);
}
