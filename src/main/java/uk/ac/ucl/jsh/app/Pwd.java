package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.IOException;
import java.nio.file.Path;

public class Pwd extends AbstractApp implements App {

    private Path currentPath;

    public Pwd(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() {
        currentPath = this.jshCore.getCurrentDirectory();
        writeOutputStreamLn(currentPath.toAbsolutePath().toString());
        exit(0);
    }

    @Override
    public void setArgs(String[] args) {
        return;
    }


}
