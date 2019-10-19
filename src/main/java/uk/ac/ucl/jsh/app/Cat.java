package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cat extends AbstractApp implements App{
    private String[] arguments;

    public Cat(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {
        if (arguments.length < 1){
            throw new RuntimeException("cat: missing arguments");
        }else {
            for (String arg: arguments){
                File curFile = new File(jshCore.getCurrentDirectory()+jshCore.getPathSeparator()+arg);
                if (curFile.exists()){
                    Path curFilePath = Paths.get(jshCore.getCurrentDirectory()+jshCore.getPathSeparator()+arg);
                    try (BufferedReader bufferedReader = Files.newBufferedReader(curFilePath,jshCore.getEncoding())){
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            writeOutputStream(String.valueOf(line));
                            writeOutputStream(jshCore.getLineSeparator());
                        }
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }else {
                    throw new RuntimeException("cat: file does not exits");
                }
            }
        }
    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        arguments = args;
    }
}
