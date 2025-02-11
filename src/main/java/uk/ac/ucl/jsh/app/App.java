package uk.ac.ucl.jsh.app;


import java.io.InputStream;
import java.io.OutputStream;

public interface App {
    void setArgs(String[] args) throws RuntimeException;
    void run() throws RuntimeException;
    void setInputStream(InputStream inputStream);
    void setOutputStream(OutputStream outputStream);
}
