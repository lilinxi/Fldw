package examples;

import core.SymbolTable;
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
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """);
    }

    @Test
    public void TestFlowExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] | sym | sym1 | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                sym: SymbolItem{symbol='sym', type=Flow, value=ListFlow{dataList=[], nextFlow=ListFlow{dataList=[], nextFlow=StdOutFlow{}, symbol='sym1'}, symbol='sym'}}
                sym1: SymbolItem{symbol='sym1', type=Flow, value=ListFlow{dataList=[], nextFlow=StdOutFlow{}, symbol='sym1'}}
                }
                """);
    }

    @Test
    public void TestFlowExample3() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] | [a, b, c, d] | [e, f, g, h, i, j] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals(output.toString(), """
                stdout: SymbolData{type=Int, value=5, symbol='e'}
                stdout: SymbolData{type=Int, value=6, symbol='f'}
                stdout: SymbolData{type=Int, value=3, symbol='g'}
                stdout: SymbolData{type=Int, value=2, symbol='h'}
                stdout: SymbolData{type=null, value=null, symbol='i'}
                stdout: SymbolData{type=null, value=null, symbol='j'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=5, symbol='a'}}
                b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=6, symbol='b'}}
                c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=2, symbol='d'}}
                e: SymbolItem{symbol='e', type=Data, value=SymbolData{type=Int, value=5, symbol='e'}}
                f: SymbolItem{symbol='f', type=Data, value=SymbolData{type=Int, value=6, symbol='f'}}
                g: SymbolItem{symbol='g', type=Data, value=SymbolData{type=Int, value=3, symbol='g'}}
                h: SymbolItem{symbol='h', type=Data, value=SymbolData{type=Int, value=2, symbol='h'}}
                i: SymbolItem{symbol='i', type=Data, value=SymbolData{type=null, value=null, symbol='i'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                j: SymbolItem{symbol='j', type=Data, value=SymbolData{type=null, value=null, symbol='j'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """);
    }

    @Test
    public void TestFlowExample4() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] | [head;tail]
                    tail | stdout
                    [head] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
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
                stdout: SymbolData{type=Int, value=5, symbol='head'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{dataList=[], nextFlow=StdOutFlow{}, symbol='tail'}}
                }
                """);
    }

    @Test
    public void TestFlowExample5() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("1, 1.213, true, \"dada\"".getBytes()));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    stdin | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);
        System.setIn(System.in);

        assertEquals(output.toString(), """
                stdout: TerminalData{type=Int, value=1}
                stdout: TerminalData{type=Double, value=1.213}
                stdout: TerminalData{type=Bool, value=true}
                stdout: TerminalData{type=String, value="dada"}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=StdOutFlow{}, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """);
    }

    @Test
    public void TestBlockExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2, 3, 4] | [a, b, c, d] | stdout
                    {
                        [5, 6, 7, 8] | [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
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
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=5, symbol='a'}}
                b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=6, symbol='b'}}
                c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=7, symbol='c'}}
                d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=8, symbol='d'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                }}
                }
                """);
    }

    @Test
    public void TestBlockExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    {
                        [1, 2, 3, 4] | [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
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
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
                b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
                c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=null, value=null, symbol='d'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{dataList=[], nextFlow=null, symbol='null'}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=4, symbol='d'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='in'}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{dataList=[], nextFlow=null, symbol='out'}}
                }}
                }
                """);
    }

    public static void main(String[] args) throws ParseException {
        Result result = JUnitCore.runClasses(UnitTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.getTrace());
        }
        if (result.wasSuccessful()) {
            System.err.println("Tests Pass!");
        } else {
            System.err.println("Some Tests Fail!");
        }
    }
}
