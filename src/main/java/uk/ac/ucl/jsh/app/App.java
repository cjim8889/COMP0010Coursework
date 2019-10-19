package uk.ac.ucl.jsh.app;


public interface App {
    void setArgs(String[] args) throws RuntimeException;
    void execute() throws RuntimeException;
}
