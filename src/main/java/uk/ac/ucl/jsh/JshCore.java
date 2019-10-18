package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class JshCore implements Core {

    private String currentDirectory, homeDirectory;
    private String lineSeparator;
    private Descriptor systemType;
    private OutputStream outputStream;
    private OutputStreamWriter outputStreamWriter;

    public JshCore() {
        currentDirectory = System.getProperty("user.dir");
        homeDirectory = System.getProperty("user.home");
        lineSeparator = System.getProperty("line.separator");
        outputStream = System.out;


        //Initialisation
        systemType = System.getProperty("file.separator").equals("\\") ? Descriptor.Windows : Descriptor.Unix;
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public String getHomeDirectory() {
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

    public void writeOutputStream(String content, boolean newLine) throws IOException {
        writeOutputStream(content + (newLine ? lineSeparator : ""));
    }

    private void resetOutputStreamWriter() {
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }
}
