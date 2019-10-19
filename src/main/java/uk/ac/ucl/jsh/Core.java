package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public interface Core {
    Path getCurrentDirectory();
    Path getHomeDirectory();
    Descriptor getSystemType();
    String getPathSeparator();
    void setOutputStream(OutputStream outputStream);
    void writeOutputStream(String content) throws IOException;
    void writeOutputStreamLn(String content) throws IOException;
    void writeErrStreamLn(String err) throws IOException;
    void setCurrentDirectory(Path path);
}
