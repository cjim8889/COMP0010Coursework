package uk.ac.ucl.jsh.app;


import uk.ac.ucl.jsh.Core;
import uk.ac.ucl.jsh.utility.IntelligentPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ls extends AbstractApp implements App{
    private String argument = "";


    public Ls(Core jshCore) {
        super(jshCore);
    }

    public void run() throws RuntimeException {

        Path path = IntelligentPath.getPath(argument, jshCore.getCurrentDirectory());

        if (!Files.isDirectory(path)) {
            throw new RuntimeException("Only directory path can be used as argument");
        }

        try {
            Files.list(path).forEach(fpath -> {
                writeOutputStreamLn(fpath.getFileName().toString());
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
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
