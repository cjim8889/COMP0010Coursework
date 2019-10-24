package uk.ac.ucl.jsh;

import org.junit.Test;
import uk.ac.ucl.jsh.app.*;
import uk.ac.ucl.jsh.core.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Files;
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
        jsh.eval("echo meimaobing", out);
        Scanner scn = new Scanner(in);
        assertEquals("meimaobing", scn.next());
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

    @Test
    public void testPipeline() throws Exception {
        Executor executor = new Executor();
        Pipeline pipeline = new IntelligentPipeline();

        JshCore jshCore = new JshCore();
        String target = jshCore.getCurrentDirectory().getParent().getParent().toString();

        pipeline.append(new Cd(jshCore), new String[]{".."});
        pipeline.append(new Pwd(jshCore), new String[]{});
        pipeline.append(new Cd(jshCore), new String[]{".."});

        executor.append(pipeline);

        executor.execute();
        System.out.println(jshCore.getCurrentDirectory().toString());
        assertEquals(target, jshCore.getCurrentDirectory().toString());
    }

    @Test
    public void testPipeline1() throws Exception {
        Executor executor = new Executor();
        Pipeline pipeline = new IntelligentPipeline();
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream(inputStream);
        Scanner scn = new Scanner(inputStream);

        JshCore jshCore = new JshCore();
        String target = jshCore.getCurrentDirectory().getParent().getParent().toString();

        pipeline.append(new Cd(jshCore), new String[]{".."});
        pipeline.append(new Cd(jshCore), new String[]{".."});
        pipeline.append(new Pwd(jshCore), new String[]{});

        pipeline.setOutputStream(outputStream);

        executor.append(pipeline);
        executor.execute();

        assertEquals(target, scn.nextLine());
    }

    @Test
    public void testPipeline2() throws Exception {
        Executor executor = new Executor();
        Pipeline pipeline = new IntelligentPipeline();
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream(inputStream);
        Scanner scn = new Scanner(inputStream);

        JshCore jshCore = new JshCore();
        String target = "laotie";

        pipeline.append(new Echo(jshCore), new String[]{"laotie"});
        pipeline.append(new Cat(jshCore), new String[]{});

        pipeline.setOutputStream(outputStream);

        executor.append(pipeline);
        executor.execute();

        assertEquals(target, scn.nextLine());
    }

    @Test
    public void testPipeline3() throws IOException {
        Executor executor=new Executor();
        Pipeline pipeline=new IntelligentPipeline();
        PipedInputStream inputStream= new PipedInputStream();
        PipedOutputStream outputStream=new PipedOutputStream(inputStream);
        Scanner scanner=new Scanner(inputStream);

        JshCore jshCore=new JshCore();
        String target= jshCore.getHomeDirectory().toString();
        String tg = Files.newDirectoryStream(jshCore.getHomeDirectory()).iterator().next().getFileName().toString();


//        pipeline.append(new Cd(jshCore),new String[]{"~"});
//        pipeline.append(new Pwd(jshCore),new String[]{});
        pipeline.append(new Cd(jshCore),new String[]{"~"});
        pipeline.append(new Ls(jshCore),new String[]{});

        pipeline.setOutputStream(outputStream);

        executor.append(pipeline);
        executor.execute();

        assertEquals(tg,scanner.nextLine());

    }
}
