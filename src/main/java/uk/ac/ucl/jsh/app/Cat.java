package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.*;

public class Cat extends AbstractApp implements App{
    private String[] arguments;

    public Cat(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {
        for (String arg : arguments){
            File curFile = new File(jshCore.getCurrentDirectory()+jshCore.getPathSeparator()+arg);
            try {
                FileInputStream fileInputStream = new FileInputStream(curFile);
                fileInputStream.transferTo(getRawOutputStream());
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found");
            } catch (IOException e) {
                throw new RuntimeException("IO exception");
            }
        }
    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        if (args.length < 1) {
            throw new RuntimeException("Arguments count does not match the program");
        }

        arguments = args;
    }
}
