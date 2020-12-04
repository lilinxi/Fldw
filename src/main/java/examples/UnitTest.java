package examples;

import core.SymbolTable;
import javacc.FldwCompiler;
import javacc.ParseException;

import java.io.*;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class UnitTest {
    public final static boolean UnitTest = true;

    public static void main(String[] args) throws ParseException {
        Result result = JUnitCore.runClasses(UnitTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println("Fail: " + failure.getTestHeader());
        }
        if (result.wasSuccessful()) {
            System.err.println("Tests Pass!");
        } else {
            System.err.println("Some Tests Fail!");
        }
    }

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

        assertEquals("""
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """, output.toString());
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

        assertEquals("""
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                sym: SymbolItem{symbol='sym', type=Flow, value=ListFlow{, symbol='sym', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=ListFlow{, symbol='sym1', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}}
                sym1: SymbolItem{symbol='sym1', type=Flow, value=ListFlow{, symbol='sym1', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}
                }
                """, output.toString());
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

        assertEquals("""
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
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                j: SymbolItem{symbol='j', type=Data, value=SymbolData{type=null, value=null, symbol='j'}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """, output.toString());
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

        assertEquals("""
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                stdout: SymbolData{type=Int, value=5, symbol='head'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{, symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}
                }
                """, output.toString());
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

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                stdout: TerminalData{type=Double, value=1.213}
                stdout: TerminalData{type=Bool, value=true}
                stdout: TerminalData{type=String, value="dada"}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Double, value=1.213}, TerminalData{type=Bool, value=true}, TerminalData{type=String, value="dada"}], nextFlow=StdOutFlow{}}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """, output.toString());
    }

    @Test
    public void TestFlowExample6() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("1 1.213 true \"dada\"".getBytes()));

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

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                stdout: TerminalData{type=Double, value=1.213}
                stdout: TerminalData{type=Bool, value=true}
                stdout: TerminalData{type=String, value="dada"}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Double, value=1.213}, TerminalData{type=Bool, value=true}, TerminalData{type=String, value="dada"}], nextFlow=StdOutFlow{}}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                }
                """, output.toString());
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

        assertEquals("""
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
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }
                """, output.toString());
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

        assertEquals("""
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
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=4, symbol='d'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestBlockExample3() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2, 3, 4] | sym | {
                        in | [a, b, c] | out
                    } | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                sym: SymbolItem{symbol='sym', type=Flow, value=ListFlow{, symbol='sym', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=BlockFlow{, inFlow=ListFlow{, symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}, outFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}, flowList=[ListFlow{, symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}]}}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestIfElseExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    if ( 1>2 ) {
                        [1] | stdout
                    } else {
                        [2] | stdout
                    }
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=2}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestIfElseExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    if ( 1<2 ) {
                        [1] | stdout
                    }
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestIfElseExample3() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2, 3, 4] | if ( 1<2 ) {
                        in | [a, b] | out
                    } else {
                       in | out
                    } | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=StdOutFlow{}}}}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=StdOutFlow{}}}
                }}
                tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=ListFlow{, symbol='out', dataList=[], nextFlow=StdOutFlow{}}}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=StdOutFlow{}}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestWhileExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2] | [a, b]
                    while ( a<b ) {
                        [a, b] | stdout
                        [b, a] | [a, b]
                    }
                    [a, b] | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=2, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=2, symbol='a'}}
                b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestWhileExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2] | [a, b] | while ( a<b ) {
                        in | [b, a] | [c, d, e, f] | out
                    } | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='c'}
                stdout: SymbolData{type=Int, value=1, symbol='d'}
                stdout: SymbolData{type=null, value=null, symbol='e'}
                stdout: SymbolData{type=null, value=null, symbol='f'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=1, symbol='b'}}
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=1, symbol='c'}}
                |-- d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=1, symbol='d'}}
                |-- e: SymbolItem{symbol='e', type=Data, value=SymbolData{type=null, value=null, symbol='e'}}
                |-- f: SymbolItem{symbol='f', type=Data, value=SymbolData{type=null, value=null, symbol='f'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=1, symbol='b'}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='b'}, SymbolData{type=Int, value=1, symbol='a'}], nextFlow=ListFlow{, symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=StdOutFlow{}}}}}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=StdOutFlow{}}}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestForExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    for ( [1, 2, 3] | x ) {
                        [x] | stdout
                    }
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='x'}
                stdout: SymbolData{type=Int, value=2, symbol='x'}
                stdout: SymbolData{type=Int, value=3, symbol='x'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                x: SymbolItem{symbol='x', type=Data, value=SymbolData{type=Int, value=3, symbol='x'}}
                }
                """, output.toString());
    }

    @Test
    public void TestForExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [4, 5, 6] | for ( [1, 2, 3] | x ) {
//                        in | out
                        [x] | out
                    } | stdout
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='x'}
                stdout: SymbolData{type=Int, value=2, symbol='x'}
                stdout: SymbolData{type=Int, value=3, symbol='x'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[TerminalData{type=Int, value=4}, TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[SymbolData{type=Int, value=3, symbol='x'}], nextFlow=StdOutFlow{}}}
                }}
                x: SymbolItem{symbol='x', type=Data, value=SymbolData{type=Int, value=3, symbol='x'}}
                }
                """, output.toString());
    }

    @Test
    public void TestFuncExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function func() {
                        [1, 2] | stdout
                    }
                    func()
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                stdout: TerminalData{type=Int, value=2}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                func: SymbolItem{symbol='func', type=Function, value=( ) { [ 1 , 2 ] | stdout } }
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestFuncExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function func([a, b, c]) {
                        [a, b, c] | stdout
                    }
                    func([1, 2])
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestFuncExample3() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function func([a, b, c]) {
                        [a, b, c] | stdout
                    }
                    func([1, 2, 3, 4])
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                }
                """, output.toString());
    }

    @Test
    public void TestFuncExample4() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function func([a, b, c]) {
                        [a, b, c] | stdout
                    }
                    func([1, 2])
                    func([1, 2, 3, 4])
                    func()
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                stdout: SymbolData{type=null, value=null, symbol='a'}
                stdout: SymbolData{type=null, value=null, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp2', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp3', parentSymbolTable=tmp2, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                tmp4: SymbolItem{symbol='tmp4', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp4', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp5: SymbolItem{symbol='tmp5', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp5', parentSymbolTable=tmp4, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                }
                """, output.toString());
    }

//    @Test
    public void TestQuickSortExample() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function sort() {
                        in | [head;tail]
             	        if ( head != null ) { 
             	            for (tail | x) {
             	                if ( x < head ) {
             			            [x] | a
             		            } else {
             			            [x] | b
             		            }
             	            }
             	            a | sort() | stdout
             		        head | stdout
             		        b | sort() | stdout
             	        }
                    }
                    [5, 6, 3, 2, 7, 8] | sort()
                    """);
            System.out.println(SymbolTable.CurrentSymbolTable());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                stdout: SymbolData{type=null, value=null, symbol='a'}
                stdout: SymbolData{type=null, value=null, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                SymbolTable{
                symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
                in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{, symbol='null', dataList=[], nextFlow=null}}}
                stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp2', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp3', parentSymbolTable=tmp2, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                tmp4: SymbolItem{symbol='tmp4', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp4', parentSymbolTable=root, SymbolItemTreeMap=
                |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
                |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
                |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                |-- tmp5: SymbolItem{symbol='tmp5', type=BlockSymbolTable, value=SymbolTable{
                symbol='tmp5', parentSymbolTable=tmp4, SymbolItemTreeMap=
                |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{, symbol='in', dataList=[], nextFlow=null}}
                |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{, symbol='out', dataList=[], nextFlow=null}}
                }}
                }}
                }
                """, output.toString());
    }
}
