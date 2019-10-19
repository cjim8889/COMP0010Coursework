package uk.ac.ucl.jsh.app;


import uk.ac.ucl.jsh.Core;

import java.io.File;
import java.nio.file.Paths;

public class Ls extends AbstractApp implements App{
    private String argument = "";


    public Ls(Core jshCore) {
        super(jshCore);
    }

    public void run() throws RuntimeException {
        File currentDir;
        if (argument.length() > 0) {
            currentDir = new File(Paths.get(argument).toString());
        } else {
            currentDir = new File(jshCore.getCurrentDirectory().toAbsolutePath().toString());
        }

        if (!currentDir.exists()) {
            throw new RuntimeException("No such directory exists");
        }

        if (!currentDir.isDirectory()) {
            throw new RuntimeException("Only directory path can be used as argument");
        }

        File[] files = currentDir.listFiles();
        for (File file: files){
            writeOutputStreamLn(file.getName());
        }
    }


    public void setArgs(String[] args) throws IllegalArgumentException {
        if(args.length > 1) {
            throw new RuntimeException("Arguments do not match with the program");
        }

        if(args.length == 0) {
            return;
        }

        argument = args[0];
    }
}
