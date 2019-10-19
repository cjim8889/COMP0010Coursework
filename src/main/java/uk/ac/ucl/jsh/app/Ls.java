package uk.ac.ucl.jsh.app;


import uk.ac.ucl.jsh.Core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Ls extends AbstractApp implements App{
    private File currentDir;
    private String argument = "";


    public Ls(Core jshCore) {
        super(jshCore);
    }

    public boolean checkArg(ArrayList<String> arg){
        boolean bool;
        if (arg.isEmpty()){
            currentDir = new File(String.valueOf(jshCore.getCurrentDirectory()));
            bool = true;
        }else if (arg.size()==1){
            currentDir = new File(arg.get(0));
            bool = true;
        }else {
            throw new RuntimeException("Incorrect Number Of Arguments!");
        }
        return bool;
    }

    public void run() {
        if (argument.length() > 0) {
            currentDir = new File(Paths.get(argument).toString());
        } else {
            currentDir = new File(jshCore.getCurrentDirectory().toAbsolutePath().toString());
        }

        if (!currentDir.exists()) {
            writeErrStreamLn("No such directory exists");
            exit(1);
            return;
        }

        if (!currentDir.isDirectory()) {
            writeErrStreamLn("Only directory path can be used as argument");
            exit(1);
            return;
        }

        File[] files = currentDir.listFiles();
        for (File file: files){
            writeOutputStreamLn(file.getName());
        }
    }


    public void setArgs(String[] args) throws IllegalArgumentException {
        if(args.length > 1) {
            writeErrStreamLn(new IllegalArgumentException("Arguments do not match with the program").getMessage());
            exit(1);
            return;
        }

        if(args.length == 0) {
            return;
        }

        argument = args[0] ;
    }
}
