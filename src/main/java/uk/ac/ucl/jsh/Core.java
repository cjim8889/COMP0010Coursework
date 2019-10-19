package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public interface Core {
    Path getCurrentDirectory();
    Path getHomeDirectory();
    Descriptor getSystemType();
    void setOutputStream(OutputStream outputStream);
    void writeOutputStream(String content) throws IOException;
    void writeOutputStreamLn(String content) throws IOException;
}
