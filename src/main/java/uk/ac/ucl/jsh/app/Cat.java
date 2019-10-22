package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;
import uk.ac.ucl.jsh.utility.IntelligentPath;

import java.io.*;

public class Cat extends AbstractApp implements App{
    private String[] arguments;

    public Cat(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {
        try {
            if (arguments != null) {
                outputFiles();
            } else {
                outputStdIn();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void outputStdIn() throws IOException {
        System.in.transferTo(getRawOutputStream());
    }
    private void outputFiles() {
        for (String arg : arguments){
            File curFile = new File(IntelligentPath.getPath(arg, jshCore.getCurrentDirectory()).toAbsolutePath().toString());
            try {
                FileInputStream fileInputStream = new FileInputStream(curFile);
                fileInputStream.transferTo(getRawOutputStream());
            } catch (IOException ignored) { }
        }
    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        if (args.length < 1) {
            arguments = null;
            return;
        }

        arguments = args;
    }
}
