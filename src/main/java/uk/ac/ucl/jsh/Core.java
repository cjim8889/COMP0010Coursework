package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;

public interface Core {
    String getCurrentDirectory();
    String getHomeDirectory();
    Descriptor getSystemType();
    void setOutputStream(OutputStream outputStream);
    void writeOutputStream(String content) throws IOException;
    void writeOutputStream(String content, boolean newLine) throws IOException;
}
