package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.app.App;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JshCore implements Core {

    private Path currentDirectory, homeDirectory;
    private String lineSeparator, pathSeparator;
    private Charset encoding;
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

        encoding = StandardCharsets.UTF_8;

        //Initialisation
        systemType = pathSeparator.equals("\\") ? Descriptor.Windows : Descriptor.Unix;
        outputStreamWriter = new OutputStreamWriter(outputStream);
        errStreamWriter = new OutputStreamWriter(errStream);
    }

    public Charset getEncoding() {
        return encoding;
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

    public String getLineSeparator() { return lineSeparator; }

    public OutputStream getOutputStream() { return outputStream; }

    public void registerTermination(App app, int status) {
        if (status == 1) {
            try {
                writeOutputStreamLn(app.getClass().getSimpleName().toString() + " has terminated with status " + status);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
