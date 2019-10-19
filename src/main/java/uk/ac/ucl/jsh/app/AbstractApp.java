package uk.ac.ucl.jsh.app;

import uk.ac.ucl.jsh.Core;

public abstract class AbstractApp implements App {

    protected Core jshCore;

    AbstractApp(Core jshCore) {
        this.jshCore = jshCore;
    }

    public abstract void run();
    public abstract String output();
    public abstract void setArgs(String[] args) throws IllegalArgumentException;
}
