package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.core.Core;


public class Pwd extends AbstractApp implements App {

    public Pwd(Core jshCore) {
        super(jshCore);
    }

    @Override
    public void run() {
        writeOutputStreamLn(this.jshCore.getCurrentDirectory().toString());
    }

    @Override
    public void setArgs(String[] args) { }
}
