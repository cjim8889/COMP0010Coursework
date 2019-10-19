package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

import java.io.IOException;

public class Echo extends AbstractApp implements App{
    private String[] arguments;

    public Echo(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {
        for (String arg:arguments){
            try {
                writeOutputStream(arg);
                writeOutputStream(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeOutputStreamLn("");
    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        arguments=args;
    }
}
