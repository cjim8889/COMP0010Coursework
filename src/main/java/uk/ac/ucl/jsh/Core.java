package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.app.App;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

public interface Core {
    Path getCurrentDirectory();
    Path getHomeDirectory();
    Descriptor getSystemType();
    String getPathSeparator();
    String getLineSeparator();
    OutputStream getOutputStream();
    Charset getEncoding();
    void setOutputStream(OutputStream outputStream);
    void writeOutputStream(String content) throws IOException;
    void writeOutputStreamLn(String content) throws IOException;
    void writeErrStreamLn(String err) throws IOException;
    void setCurrentDirectory(Path path);
    void registerTermination(App app, int status);
    OutputStream getRawOutputStream();
}
