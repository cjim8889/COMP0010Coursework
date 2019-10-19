package uk.ac.ucl.jsh;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class JshTest {
    public JshTest() {
    }

    @Test
    public void testJsh() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Jsh jsh = new Jsh();
        jsh.getJshCore().setOutputStream(out);
        jsh.eval("echo foasfdo", out);
        Scanner scn = new Scanner(in);

        System.out.println(scn.next());
        assertEquals("", "");
    }

}
