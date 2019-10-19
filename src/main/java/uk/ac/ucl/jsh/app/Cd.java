package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;
import uk.ac.ucl.jsh.Descriptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (argument.equals("~")) {
            jshCore.setCurrentDirectory(jshCore.getHomeDirectory());
            return;
        } else if(argument.equals(".")) {
            return;
        } else if(argument.equals("..")) {
            jshCore.setCurrentDirectory(jshCore.getCurrentDirectory().getParent());
            return;
        }


        if (systemType == Descriptor.Windows) {
            runOnWindows();
        } else {
            runOnUnix();
        }
        try {
            changeDirectory();
            exit(0);
        } catch (RuntimeException e) {
            writeErrStreamLn(e.getMessage());
            exit(1);
        }
    }

    private void runOnWindows() {
        String windowsStartRegex = "([A-Za-z]:\\\\)";
        Pattern regex = Pattern.compile(windowsStartRegex);
        Matcher regexMatcher = regex.matcher(argument);
        if (regexMatcher.find()) {
            destinationPath = Paths.get(argument);
        } else {
            destinationPath = Paths.get(jshCore.getCurrentDirectory().toAbsolutePath().toString() + jshCore.getPathSeparator() + argument);
        }
    }

    private void runOnUnix() {
        if (argument.startsWith("/")) {
            destinationPath = Paths.get(argument);
        } else if (argument.startsWith("./")) {
            destinationPath = Paths.get(jshCore.getCurrentDirectory().toAbsolutePath().toString() + jshCore.getPathSeparator() + argument.substring(1));
        } else if (argument.startsWith("../")) {
            destinationPath = Paths.get(jshCore.getCurrentDirectory().getParent().toAbsolutePath().toString() + jshCore.getPathSeparator() + argument.substring(2));
        } else {
            destinationPath = Paths.get(jshCore.getCurrentDirectory().toAbsolutePath().toString() + jshCore.getPathSeparator() + argument);
        }
    }


    private void changeDirectory() throws RuntimeException {
        if (Files.exists(destinationPath)) {
            if (Files.isDirectory(destinationPath)) {
                jshCore.setCurrentDirectory(destinationPath);
            } else {
                throw new RuntimeException("Is not a path");
            }
        } else {
            throw new RuntimeException("No such path exists");
        }
    }


    public void setArgs(String[] args) throws IllegalArgumentException {
        if(args.length > 1) {
            writeErrStreamLn(new IllegalArgumentException("Arguments do not match with the program").getMessage());
            exit(1);
        }

        argument = args[0];
    }
}
