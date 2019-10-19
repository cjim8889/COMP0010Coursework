package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JshCore implements Core {

    private Path currentDirectory, homeDirectory;
    private String lineSeparator, pathSeparator;
    private Descriptor systemType;
    private OutputStream outputStream, errStream;
    private OutputStreamWriter outputStreamWriter;
    private OutputStreamWriter errStreamWriter;

    public JshCore() {
        currentDirectory = Paths.get(System.getProperty("user.dir"));
        homeDirectory = Paths.get(System.getProperty("user.home"));
        lineSeparator = System.getProperty("line.separator");
        pathSeparator = System.getProperty("file.separator");

        outputStream = System.out;
        errStream = System.err;


        //Initialisation
        systemType = pathSeparator.equals("\\") ? Descriptor.Windows : Descriptor.Unix;
        outputStreamWriter = new OutputStreamWriter(outputStream);
        errStreamWriter = new OutputStreamWriter(errStream);
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public Path getHomeDirectory() {
        return homeDirectory;
    }

    public Descriptor getSystemType() {
        return systemType;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;

        outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public void writeOutputStream(String content) throws IOException {
        if (content.isEmpty()) {
            return;
        }

        outputStreamWriter.write(content);
        outputStreamWriter.flush();
    }

    public void writeOutputStreamLn(String content) throws IOException {
        writeOutputStream(content + lineSeparator);
    }

    private void writeErrStream(String err) throws IOException {
        if (err.isEmpty()) {
            return;
        }

        errStreamWriter.write(err);
        errStreamWriter.flush();
    }

    public void writeErrStreamLn(String err) throws IOException {
        if (err.isEmpty()) {
            return;
        }

        writeErrStream("[Error]Jsh: " + err + lineSeparator);
    }

    public void setCurrentDirectory(Path path) {
        currentDirectory = path;
    }

}
