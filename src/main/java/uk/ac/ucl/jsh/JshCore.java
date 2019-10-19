package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JshCore implements Core {

    private Path currentDirectory, homeDirectory;
    private String lineSeparator;
    private Descriptor systemType;
    private OutputStream outputStream;
    private OutputStreamWriter outputStreamWriter;

    public JshCore() {
        currentDirectory = Paths.get(System.getProperty("user.dir"));
        homeDirectory = Paths.get(System.getProperty("user.home"));
        lineSeparator = System.getProperty("line.separator");
        outputStream = System.out;


        //Initialisation
        systemType = System.getProperty("file.separator").equals("\\") ? Descriptor.Windows : Descriptor.Unix;
        outputStreamWriter = new OutputStreamWriter(outputStream);
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

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;

        resetOutputStreamWriter();
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

    private void resetOutputStreamWriter() {
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }
}
