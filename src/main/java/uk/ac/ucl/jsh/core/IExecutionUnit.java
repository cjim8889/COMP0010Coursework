package uk.ac.ucl.jsh.core;


import java.io.InputStream;
import java.io.OutputStream;

public interface IExecutionUnit {
    void run();
    void setArgs(String[] args) throws RuntimeException;
    InputStream getInputStream();
    OutputStream getOutputStream();
    void setOutputStream(OutputStream outputStream);
}
