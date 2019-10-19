package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;
import uk.ac.ucl.jsh.Descriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void run() throws RuntimeException {
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

        changeDirectory();
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

    public String output() {
        return null;
    }

    public void setArgs(String[] args) throws IllegalArgumentException {
        if(args.length > 1) {
            throw new IllegalArgumentException("Arguments do not match with the program");
        }

        argument = args[0];
    }
}
