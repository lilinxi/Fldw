package core;

import javacc.FldwCompiler;
import javacc.ParseException;

import java.io.*;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static junit.framework.Assert.*;

public class UnitTest {
    public final static boolean UnitTest = true;

    @Test
    public void TestFlowExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                """);
    }

    @Test
    public void TestBlockExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2, 3, 4] | [a, b, c, d] | stdout
                    {
                        [5, 6, 7, 8] | [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                stdout: SymbolData{type=Int, value=4, symbol='d'}
                stdout: SymbolData{type=Int, value=5, symbol='a'}
                stdout: SymbolData{type=Int, value=6, symbol='b'}
                stdout: SymbolData{type=Int, value=7, symbol='c'}
                stdout: SymbolData{type=Int, value=8, symbol='d'}
                stdout: SymbolData{type=Int, value=5, symbol='a'}
                stdout: SymbolData{type=Int, value=6, symbol='b'}
                stdout: SymbolData{type=Int, value=7, symbol='c'}
                stdout: SymbolData{type=Int, value=8, symbol='d'}
                """);
    }

    @Test
    public void TestBlockExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            FldwCompiler.parse("""
                    import std.Std
                    {
                        [1, 2, 3, 4] | [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                stdout: SymbolData{type=Int, value=4, symbol='d'}
                stdout: SymbolData{type=null, value=null, symbol='a'}
                stdout: SymbolData{type=null, value=null, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                stdout: SymbolData{type=null, value=null, symbol='d'}
                """);
    }

    public static void main(String[] args) throws ParseException {
        Result result = JUnitCore.runClasses(UnitTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.getDescription());
        }
        System.err.println(result.wasSuccessful());
//        /*****************************************************************/
//        /***************************Flow Test*****************************/
//        /*****************************************************************/
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    [5, 6, 3, 2, 7, 8] | stdout
//                    """);
//
//            Assert("Flow Example 1", output.toString(), """
//                stdout: TerminalData{type=Int, value=5}
//                stdout: TerminalData{type=Int, value=6}
//                stdout: TerminalData{type=Int, value=3}
//                stdout: TerminalData{type=Int, value=2}
//                stdout: TerminalData{type=Int, value=7}
//                stdout: TerminalData{type=Int, value=8}
//                    """);
//
//            System.setOut(System.out);
//        }
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    [5, 6, 3, 2, 7, 8] | sym | sym1 | stdout
//                    """);
//
//            Assert("Flow Example 2", output.toString(), """
//                stdout: TerminalData{type=Int, value=5}
//                stdout: TerminalData{type=Int, value=6}
//                stdout: TerminalData{type=Int, value=3}
//                stdout: TerminalData{type=Int, value=2}
//                stdout: TerminalData{type=Int, value=7}
//                stdout: TerminalData{type=Int, value=8}
//                    """);
//
//            System.setOut(System.out);
//        }
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    [5, 6, 3, 2, 7, 8] | [a, b, c, d] | [e, f, g, h, i, j] | stdout
//                    """);
//
//            Assert("Flow Example 3", output.toString(), """
//                stdout: SymbolData{type=Int, value=5, symbol='e'}
//                stdout: SymbolData{type=Int, value=6, symbol='f'}
//                stdout: SymbolData{type=Int, value=3, symbol='g'}
//                stdout: SymbolData{type=Int, value=2, symbol='h'}
//                stdout: SymbolData{type=null, value=null, symbol='i'}
//                stdout: SymbolData{type=null, value=null, symbol='j'}
//                    """);
//
//            System.setOut(System.out);
//        }
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    [5, 6, 3, 2, 7, 8] | [head;tail]
//                    tail | stdout
//                    [head] | stdout
//                    """);
//
//            Assert("Flow Example 4", output.toString(), """
//                stdout: TerminalData{type=Int, value=6}
//                stdout: TerminalData{type=Int, value=3}
//                stdout: TerminalData{type=Int, value=2}
//                stdout: TerminalData{type=Int, value=7}
//                stdout: TerminalData{type=Int, value=8}
//                stdout: SymbolData{type=Int, value=5, symbol='head'}
//                    """);
//
//            System.setOut(System.out);
//        }
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//            System.setIn(new ByteArrayInputStream("1, 1.213, true, \"dada\"".getBytes()));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    stdin | stdout
//                    """);
//
//            Assert("Flow Example 5", output.toString(), """
//                stdout: TerminalData{type=Int, value=1}
//                stdout: TerminalData{type=Double, value=1.213}
//                stdout: TerminalData{type=Bool, value=true}
//                stdout: TerminalData{type=String, value="dada"}
//                    """);
//
//            System.setOut(System.out);
//            System.setIn(System.in);
//        }
//        /*****************************************************************/
//        /***************************Block Test****************************/
//        /*****************************************************************/
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    [1, 2, 3, 4] | [a, b, c, d] | stdout
//                    {
//                        [5, 6, 7, 8] | [a, b, c, d] | stdout
//                    }
//                    [a, b, c, d] | stdout
//                    """);
//
//            Assert("Block Example 1", output.toString(), """
//                stdout: SymbolData{type=Int, value=1, symbol='a'}
//                stdout: SymbolData{type=Int, value=2, symbol='b'}
//                stdout: SymbolData{type=Int, value=3, symbol='c'}
//                stdout: SymbolData{type=Int, value=4, symbol='d'}
//                stdout: SymbolData{type=Int, value=5, symbol='a'}
//                stdout: SymbolData{type=Int, value=6, symbol='b'}
//                stdout: SymbolData{type=Int, value=7, symbol='c'}
//                stdout: SymbolData{type=Int, value=8, symbol='d'}
//                stdout: SymbolData{type=Int, value=5, symbol='a'}
//                stdout: SymbolData{type=Int, value=6, symbol='b'}
//                stdout: SymbolData{type=Int, value=7, symbol='c'}
//                stdout: SymbolData{type=Int, value=8, symbol='d'}
//                    """);
//
//            System.setOut(System.out);
//        }
//        {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(output));
//
//            FldwCompiler.parse("""
//                    import std.Std
//                    {
//                        [1, 2, 3, 4] | [a, b, c, d] | stdout
//                    }
//                    [a, b, c, d] | stdout
//                    """);
//
//            Assert("Block Example 2", output.toString(), """
//                stdout: SymbolData{type=Int, value=1, symbol='a'}
//                stdout: SymbolData{type=Int, value=2, symbol='b'}
//                stdout: SymbolData{type=Int, value=3, symbol='c'}
//                stdout: SymbolData{type=Int, value=4, symbol='d'}
//                stdout: SymbolData{type=null, value=null, symbol='a'}
//                stdout: SymbolData{type=null, value=null, symbol='b'}
//                stdout: SymbolData{type=null, value=null, symbol='c'}
//                stdout: SymbolData{type=null, value=null, symbol='d'}
//                    """);
//
//            System.setOut(System.out);
//        }

    }
}
