package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;
import uk.ac.ucl.jsh.Descriptor;
import uk.ac.ucl.jsh.utility.IntelligentPath;

import java.nio.file.Files;
import java.nio.file.Path;

public class Cd extends AbstractApp implements App {

    private Descriptor systemType;
    private String argument = "";
    private Path destinationPath;

    public Cd(Core jshCore) {
        super(jshCore);
    }

    private void initialise() {
        systemType = jshCore.getSystemType();
    }

    public void run() {
        initialise();

        Path path = IntelligentPath.getPath(argument, jshCore.getCurrentDirectory());
        if (Files.isDirectory(path)) {
            destinationPath = path;
            jshCore.setCurrentDirectory(destinationPath);
        } else {
            throw new RuntimeException("Path is not a directory");
        }
    }


    public void setArgs(String[] args) throws IllegalArgumentException {
        if(args.length != 1) {
            throw new RuntimeException("Arguments do not match with the program");
        }

        argument = args[0];
    }
}
