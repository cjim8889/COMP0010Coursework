package uk.ac.ucl.jsh;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Paths;
import java.util.Scanner;

public class JshTest {
    public JshTest() {
    }

    @Test
    public void testJshEcho() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Jsh jsh = new Jsh();
        jsh.getJshCore().setOutputStream(out);
        jsh.eval("echo foasfdo", out);
        Scanner scn = new Scanner(in);
        assertEquals("foasfdo", scn.next());
    }

    @Test
    public void testJshPwd() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Jsh jsh = new Jsh();
        jsh.getJshCore().setOutputStream(out);
        jsh.eval("pwd", out);
        Scanner scn = new Scanner(in);
        assertEquals(Paths.get(System.getProperty("user.dir")).toAbsolutePath().toString(), scn.next());
    }
}
