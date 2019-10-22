package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;


public class Echo extends AbstractApp implements App{
    private String[] arguments;

    public Echo(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() throws RuntimeException {

        String result = String.join(" ", arguments);
        writeOutputStreamLn(result);

    }

    @Override
    public void setArgs(String[] args) throws RuntimeException {
        arguments=args.clone();
    }
}
