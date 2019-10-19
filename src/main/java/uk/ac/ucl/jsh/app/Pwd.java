package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.nio.file.Path;

public class Pwd extends AbstractApp implements App {

    private Path currentPath;

    public Pwd(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() {
        currentPath = this.jshCore.getCurrentDirectory();
    }

    @Override
    public void setArgs(String[] args) {
        return;
    }

    @Override
    public String output() {
        return currentPath.toAbsolutePath().toString();
    }
}
