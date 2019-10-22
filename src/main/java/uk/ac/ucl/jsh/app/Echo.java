package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

public class Echo extends AbstractApp implements App{
    private String[] arguments;

    public Echo(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {
        for (String arg:arguments){
            writeOutputStream(arg);
            writeOutputStream(" ");
        }
        writeOutputStream(jshCore.getLineSeparator());
    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        arguments=args.clone();
    }
}
