package uk.ac.ucl.jsh.app;


public interface App {
    void setArgs(String[] args) throws IllegalArgumentException;
    void run();
    String output();
}
