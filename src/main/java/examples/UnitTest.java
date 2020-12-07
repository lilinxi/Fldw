package examples;

import core.Datable;
import core.ExprData;
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
//            System.err.println("\t\t" + failure.getTrace());
        }
        if (result.wasSuccessful()) {
            System.err.println("Tests Pass!");
        } else {
            System.err.println("Some Tests Fail!");
        }
    }

    @Test
    public void TestExprDataExample1() throws ParseException {
        Datable data = new FldwCompiler(
                new StringReader(
                        "1"
                ))
                .term();

//        System.out.println(data);

        assertEquals("TerminalData{type=Int, value=1}",
                data.toString());
    }

    @Test
    public void TestExprDataExample2() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "abc"
                ))
                .term();

//        System.out.println(data);

        assertEquals("SymbolData{type=null, value=null, symbol='abc'}",
                data.toString());
    }

    @Test
    public void TestExprDataExample3() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "2 * 3"
                ))
                .expr4_data();

//        System.out.println(data);

        assertEquals("ExprData{leftData=TerminalData{type=Int, value=2}, rightData=TerminalData{type=Int, value=3}, op=MulOp, type=null}",
                data.toString());
    }

    @Test
    public void TestExprDataExample4() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "2 * 3 * 4"
                ))
                .expr4_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

        assertEquals("ExprData{leftData=ExprData{leftData=TerminalData{type=Int, value=2}, rightData=TerminalData{type=Int, value=3}, op=MulOp, type=null}, rightData=TerminalData{type=Int, value=4}, op=MulOp, type=null}",
                data.toString());
        assertEquals("Int", data.GetType().toString());
        assertEquals("24", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample5() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "2 * 3 * 4 * 5 * 6"
                ))
                .expr4_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

//        assertEquals("ExprData{leftData=SymbolData{type=Int, value=120, symbol='null'}, rightData=TerminalData{type=Int, value=6}, op=MulOp, type=Int}",
//                data.toString());
        assertEquals("Int", data.GetType().toString());
        assertEquals("720", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample6() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1"
                ))
                .expr4_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

        assertEquals("TerminalData{type=Int, value=1}",
                data.toString());
        assertEquals("Int", data.GetType().toString());
        assertEquals("1", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample7() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1+2"
                ))
                .expr3_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

//        assertEquals("ExprData{leftData=TerminalData{type=Int, value=1}, rightData=TerminalData{type=Int, value=2}, op=AddOp, type=Int}",
//                data.toString());
        assertEquals("Int", data.GetType().toString());
        assertEquals("3", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample8() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1+2+3"
                ))
                .expr3_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

//        assertEquals("ExprData{leftData=SymbolData{type=Int, value=3, symbol='null'}, rightData=TerminalData{type=Int, value=3}, op=AddOp, type=Int}",
//                data.toString());
        assertEquals("Int", data.GetType().toString());
        assertEquals("6", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample9() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1+2*3.1"
                ))
                .expr3_data();

//        System.out.println(data);
//        System.out.println(data.GetType());
//        System.out.println(data.GetValue());

//        assertEquals("ExprData{leftData=TerminalData{type=Int, value=1}, rightData=ExprData{leftData=TerminalData{type=Int, value=2}, rightData=TerminalData{type=Double, value=3.1}, op=MulOp, type=Double}, op=AddOp, type=Double}",
//                data.toString());
        assertEquals("Double", data.GetType().toString());
        assertEquals("7.2", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample101() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1.0 + 2.3 * 3.3"
                ))
                .expr3_data();

//        assertEquals("ExprData{leftData=TerminalData{type=Double, value=1.0}, rightData=ExprData{leftData=TerminalData{type=Double, value=2.3}, rightData=TerminalData{type=Double, value=3.3}, op=MulOp, type=Double}, op=AddOp, type=Double}",
//                data.toString());
        assertEquals("Double", data.GetType().toString());
        assertEquals("8.59", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample102() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1<2 && 2<3 || 4>5 || false"
                ))
                .expr_data();

//        assertEquals("ExprData{leftData=SymbolData{type=Bool, value=true, symbol='null'}, rightData=TerminalData{type=Bool, value=false}, op=LogicOrOp, type=Bool}",
//                data.toString());
        assertEquals("Bool", data.GetType().toString());
        assertEquals("true", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample103() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "(1.0 + 2.3) * 3.3"
                ))
                .expr_data();

//        assertEquals("ExprData{leftData=ExprData{leftData=TerminalData{type=Double, value=1.0}, rightData=TerminalData{type=Double, value=2.3}, op=AddOp, type=Double}, rightData=TerminalData{type=Double, value=3.3}, op=MulOp, type=Double}",
//                data.toString());
        assertEquals("Double", data.GetType().toString());
        assertEquals("10.889999999999999", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample200() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "1*2 < 2*3"
                ))
                .expr2_data();

//        assertEquals("ExprData{leftData=ExprData{leftData=TerminalData{type=Int, value=1}, rightData=TerminalData{type=Int, value=2}, op=MulOp, type=Int}, rightData=ExprData{leftData=TerminalData{type=Int, value=2}, rightData=TerminalData{type=Int, value=3}, op=MulOp, type=Int}, op=LeftOp, type=Bool}",
//                data.toString());
        assertEquals("Bool", data.GetType().toString());
        assertEquals("true", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample201() throws ParseException {
        SymbolTable.Clear();

        Datable data = new FldwCompiler(
                new StringReader(
                        "4>=5 || false"
                ))
                .expr_data();

//        assertEquals("ExprData{leftData=ExprData{leftData=TerminalData{type=Int, value=4}, rightData=TerminalData{type=Int, value=5}, op=RightEqualOp, type=Bool}, rightData=TerminalData{type=Bool, value=false}, op=LogicOrOp, type=Bool}",
//                data.toString());
        assertEquals("Bool", data.GetType().toString());
        assertEquals("false", data.GetValue().toString());
    }

    @Test
    public void TestExprDataExample301() throws ParseException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5+8,1+2] | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                        stdout: ExprData{leftData=TerminalData{type=Int, value=5}, rightData=TerminalData{type=Int, value=8}, op=AddOp, type=null}
                        stdout: ExprData{leftData=TerminalData{type=Int, value=1}, rightData=TerminalData{type=Int, value=2}, op=AddOp, type=null}
                        """
                , output.toString());
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
                        """
                , output.toString());
        assertEquals("""
                        SymbolTable{
                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                        }
                        """,
                SymbolTable.CurrentSymbolTable().toString());
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        sym: SymbolItem{symbol='sym', type=Flow, value=ListFlow{symbol='sym', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=ListFlow{symbol='sym1', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}}
//                        sym1: SymbolItem{symbol='sym1', type=Flow, value=ListFlow{symbol='sym1', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=null, value=null, symbol='e'}
                stdout: SymbolData{type=null, value=null, symbol='f'}
                stdout: SymbolData{type=null, value=null, symbol='g'}
                stdout: SymbolData{type=null, value=null, symbol='h'}
                stdout: SymbolData{type=null, value=null, symbol='i'}
                stdout: SymbolData{type=null, value=null, symbol='j'}
                stdout: SymbolData{type=null, value=null, symbol='a'}
                stdout: SymbolData{type=null, value=null, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                stdout: SymbolData{type=null, value=null, symbol='d'}
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                """, output.toString());
        assertEquals("""
                        SymbolTable{
                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
                        c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
                        d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=null, value=null, symbol='d'}}
                        e: SymbolItem{symbol='e', type=Data, value=SymbolData{type=null, value=null, symbol='e'}}
                        f: SymbolItem{symbol='f', type=Data, value=SymbolData{type=null, value=null, symbol='f'}}
                        g: SymbolItem{symbol='g', type=Data, value=SymbolData{type=null, value=null, symbol='g'}}
                        h: SymbolItem{symbol='h', type=Data, value=SymbolData{type=null, value=null, symbol='h'}}
                        i: SymbolItem{symbol='i', type=Data, value=SymbolData{type=null, value=null, symbol='i'}}
                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
                        j: SymbolItem{symbol='j', type=Data, value=SymbolData{type=null, value=null, symbol='j'}}
                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
                        }
                        """,
                SymbolTable.CurrentSymbolTable().toString());
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Double, value=1.213}, TerminalData{type=Bool, value=true}, TerminalData{type=String, value="dada"}], nextFlow=StdOutFlow{}}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Double, value=1.213}, TerminalData{type=Bool, value=true}, TerminalData{type=String, value="dada"}], nextFlow=StdOutFlow{}}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestFlowExample7() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [5, 6, 3, 2, 7, 8] -> [a, b, c, d] -> [e, f, g, h, i, j] | stdout
                    """);
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=5, symbol='a'}}
//                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=6, symbol='b'}}
//                        c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
//                        d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=2, symbol='d'}}
//                        e: SymbolItem{symbol='e', type=Data, value=SymbolData{type=Int, value=5, symbol='e'}}
//                        f: SymbolItem{symbol='f', type=Data, value=SymbolData{type=Int, value=6, symbol='f'}}
//                        g: SymbolItem{symbol='g', type=Data, value=SymbolData{type=Int, value=3, symbol='g'}}
//                        h: SymbolItem{symbol='h', type=Data, value=SymbolData{type=Int, value=2, symbol='h'}}
//                        i: SymbolItem{symbol='i', type=Data, value=SymbolData{type=null, value=null, symbol='i'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        j: SymbolItem{symbol='j', type=Data, value=SymbolData{type=null, value=null, symbol='j'}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestBlockExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2, 3, 4] -> [a, b, c, d] | stdout
                    {
                        [5, 6, 7, 8] -> [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=5, symbol='a'}}
//                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=6, symbol='b'}}
//                        c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=7, symbol='c'}}
//                        d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=8, symbol='d'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                        [1, 2, 3, 4] -> [a, b, c, d] | stdout
                    }
                    [a, b, c, d] | stdout
                    """);
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
//                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
//                        c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
//                        d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=null, value=null, symbol='d'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
//                        |-- d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=4, symbol='d'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                        in -> [a, b, c] | out
                    } | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        sym: SymbolItem{symbol='sym', type=Flow, value=ListFlow{symbol='sym', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=BlockFlow{, inFlow=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}, outFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}, flowList=[ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}]}}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}, SymbolData{type=Int, value=3, symbol='c'}], nextFlow=StdOutFlow{}}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=2}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                        in -> [a, b] | out
                    } else {
                       in | out
                    } | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=1}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=4}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=StdOutFlow{}}}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=2, symbol='b'}], nextFlow=StdOutFlow{}}}
//                        }
//                        }
//                        tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=ListFlow{symbol='out', dataList=[], nextFlow=StdOutFlow{}}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=StdOutFlow{}}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestWhileExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2] -> [a, b]
                    while ( a<b ) {
                        [a, b] | stdout
                        [b, a] -> [a, b]
                    }
                    [a, b] | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=2, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=2, symbol='a'}}
//                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestWhileExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [1, 2] -> [a, b] | while ( a<b ) {
                        in -> [b, a] -> [c, d, e, f] | out
                    } | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='c'}
                stdout: SymbolData{type=Int, value=1, symbol='d'}
                stdout: SymbolData{type=null, value=null, symbol='e'}
                stdout: SymbolData{type=null, value=null, symbol='f'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=1, symbol='b'}}
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=1, symbol='c'}}
//                        |-- d: SymbolItem{symbol='d', type=Data, value=SymbolData{type=Int, value=1, symbol='d'}}
//                        |-- e: SymbolItem{symbol='e', type=Data, value=SymbolData{type=null, value=null, symbol='e'}}
//                        |-- f: SymbolItem{symbol='f', type=Data, value=SymbolData{type=null, value=null, symbol='f'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[SymbolData{type=Int, value=1, symbol='a'}, SymbolData{type=Int, value=1, symbol='b'}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='b'}, SymbolData{type=Int, value=1, symbol='a'}], nextFlow=ListFlow{symbol='null', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=StdOutFlow{}}}}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[SymbolData{type=Int, value=1, symbol='c'}, SymbolData{type=Int, value=1, symbol='d'}, SymbolData{type=null, value=null, symbol='e'}, SymbolData{type=null, value=null, symbol='f'}], nextFlow=StdOutFlow{}}}
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestForExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    for ( [1, 2, 3] -> x ) {
                        [x] | stdout
                    }
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='x'}
                stdout: SymbolData{type=Int, value=2, symbol='x'}
                stdout: SymbolData{type=Int, value=3, symbol='x'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        x: SymbolItem{symbol='x', type=Data, value=SymbolData{type=Int, value=3, symbol='x'}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestForExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    [4, 5, 6] | for ( [1, 2, 3] -> x ) {
                        in | stdout
                        [x] | stdout
                    }
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=4}
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: SymbolData{type=Int, value=1, symbol='x'}
                stdout: TerminalData{type=Int, value=4}
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: SymbolData{type=Int, value=2, symbol='x'}
                stdout: TerminalData{type=Int, value=4}
                stdout: TerminalData{type=Int, value=5}
                stdout: TerminalData{type=Int, value=6}
                stdout: SymbolData{type=Int, value=3, symbol='x'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=4}, TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}], nextFlow=StdOutFlow{}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        x: SymbolItem{symbol='x', type=Data, value=SymbolData{type=Int, value=3, symbol='x'}}
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: TerminalData{type=Int, value=1}
                stdout: TerminalData{type=Int, value=2}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        func: SymbolItem{symbol='func', type=Function, value=( ) { [ 1 , 2 ] | stdout } }
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=null, value=null, symbol='c'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=1, symbol='a'}
                stdout: SymbolData{type=Int, value=2, symbol='b'}
                stdout: SymbolData{type=Int, value=3, symbol='c'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
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
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        func: SymbolItem{symbol='func', type=Function, value=( [ a , b , c ] ) { [ a , b , c ] | stdout } }
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp2', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=Int, value=1, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=Int, value=2, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=Int, value=3, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp3', parentSymbolTable=tmp2, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        tmp4: SymbolItem{symbol='tmp4', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp4', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- a: SymbolItem{symbol='a', type=Data, value=SymbolData{type=null, value=null, symbol='a'}}
//                        |-- b: SymbolItem{symbol='b', type=Data, value=SymbolData{type=null, value=null, symbol='b'}}
//                        |-- c: SymbolItem{symbol='c', type=Data, value=SymbolData{type=null, value=null, symbol='c'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp5: SymbolItem{symbol='tmp5', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp5', parentSymbolTable=tmp4, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestFuncExample5() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function seq( [begin, end, step] ) {
                        [begin] -> [a]
                        [] | ret
                        while ( a < end ) {
                            #[a] | ret
                            [a+step] -> [a]
                        }
                        ret | out
                    }
                    seq( [0, 10, 1] ) | seq_10
                    for (  seq_10 -> x ) {
                        if ( x % 2 == 0 ) {
                            [x] | stdout
                        } else {
                            ["bee"] | stdout
                        }
                    }            
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=0, symbol='x'}
                stdout: TerminalData{type=String, value="bee"}
                stdout: SymbolData{type=Int, value=2, symbol='x'}
                stdout: TerminalData{type=String, value="bee"}
                stdout: SymbolData{type=Int, value=4, symbol='x'}
                stdout: TerminalData{type=String, value="bee"}
                stdout: SymbolData{type=Int, value=6, symbol='x'}
                stdout: TerminalData{type=String, value="bee"}
                stdout: SymbolData{type=Int, value=8, symbol='x'}
                stdout: TerminalData{type=String, value="bee"}
                """, output.toString());
    }

    @Test
    public void TestQuickSortExample1() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function sort() {
                        in | [head;tail]
                        [head] | stdout
                        tail | stdout
                        if ( head != null ) { 
                            ["head != null is true"] | stdout
                        } else {
                            ["head != null is false"] | stdout
                        }
                        if ( head == null ) { 
                            ["head == null is true"] | stdout
                        } else {
                            ["head == null is false"] | stdout
                        }
                    }
                    [5, 6, 3, 2, 7, 8] | sort()
                    [5] | sort()
                    [] | sort()
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=5, symbol='head'}
                stdout: TerminalData{type=Int, value=6}
                stdout: TerminalData{type=Int, value=3}
                stdout: TerminalData{type=Int, value=2}
                stdout: TerminalData{type=Int, value=7}
                stdout: TerminalData{type=Int, value=8}
                stdout: TerminalData{type=String, value="head != null is true"}
                stdout: TerminalData{type=String, value="head == null is false"}
                stdout: SymbolData{type=Int, value=5, symbol='head'}
                stdout: TerminalData{type=String, value="head != null is true"}
                stdout: TerminalData{type=String, value="head == null is false"}
                stdout: SymbolData{type=null, value=null, symbol='head'}
                stdout: TerminalData{type=String, value="head != null is false"}
                stdout: TerminalData{type=String, value="head == null is true"}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        sort: SymbolItem{symbol='sort', type=Function, value=( ) { in | [ head ; tail ] [ head ] | stdout tail | stdout if ( head != null ) { [ "head != null is true" ] | stdout } else { [ "head != null is false" ] | stdout } if ( head == null ) { [ "head == null is true" ] | stdout } else { [ "head == null is false" ] | stdout } } }
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=HeadTailFlow{headData=SymbolData{type=Int, value=5, symbol='head'}, tailDataList=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}, setHead=false}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=StdOutFlow{}}}
//                        |-- tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp2', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp3', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp4: SymbolItem{symbol='tmp4', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp4', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp5: SymbolItem{symbol='tmp5', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp5', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        tmp12: SymbolItem{symbol='tmp12', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp12', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp13: SymbolItem{symbol='tmp13', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp13', parentSymbolTable=tmp12, SymbolItemTreeMap=
//                        |-- head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=null, value=null, symbol='head'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=HeadTailFlow{headData=SymbolData{type=null, value=null, symbol='head'}, tailDataList=ListFlow{symbol='tail', dataList=[], nextFlow=StdOutFlow{}}, setHead=true}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[], nextFlow=StdOutFlow{}}}
//                        |-- tmp14: SymbolItem{symbol='tmp14', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp14', parentSymbolTable=tmp13, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp15: SymbolItem{symbol='tmp15', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp15', parentSymbolTable=tmp13, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp16: SymbolItem{symbol='tmp16', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp16', parentSymbolTable=tmp13, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp17: SymbolItem{symbol='tmp17', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp17', parentSymbolTable=tmp13, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        tmp6: SymbolItem{symbol='tmp6', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp6', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp7: SymbolItem{symbol='tmp7', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp7', parentSymbolTable=tmp6, SymbolItemTreeMap=
//                        |-- head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=5}], nextFlow=HeadTailFlow{headData=SymbolData{type=Int, value=5, symbol='head'}, tailDataList=ListFlow{symbol='tail', dataList=[], nextFlow=StdOutFlow{}}, setHead=false}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[], nextFlow=StdOutFlow{}}}
//                        |-- tmp10: SymbolItem{symbol='tmp10', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp10', parentSymbolTable=tmp7, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp11: SymbolItem{symbol='tmp11', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp11', parentSymbolTable=tmp7, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp8: SymbolItem{symbol='tmp8', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp8', parentSymbolTable=tmp7, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp9: SymbolItem{symbol='tmp9', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp9', parentSymbolTable=tmp7, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestQuickSortExample2() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function sort() {
                        in | [head;tail]
                        if ( head != null ) { 
                            for ( tail -> tmp) {
                                [tmp] | stdout
                            }
                        }
                    }
                    [5, 6, 3, 2, 7, 8] | sort()
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=6, symbol='tmp'}
                stdout: SymbolData{type=Int, value=3, symbol='tmp'}
                stdout: SymbolData{type=Int, value=2, symbol='tmp'}
                stdout: SymbolData{type=Int, value=7, symbol='tmp'}
                stdout: SymbolData{type=Int, value=8, symbol='tmp'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        sort: SymbolItem{symbol='sort', type=Function, value=( ) { in | [ head ; tail ] if ( head != null ) { for ( tail -> tmp ) { [ tmp ] | stdout } } } }
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=HeadTailFlow{headData=SymbolData{type=Int, value=5, symbol='head'}, tailDataList=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=null}, setHead=false}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=null}}
//                        |-- tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp2', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp: SymbolItem{symbol='tmp', type=Data, value=SymbolData{type=Int, value=8, symbol='tmp'}}
//                        |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp3', parentSymbolTable=tmp2, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestQuickSortExample3() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function sort() {
                        in | [head;tail]
                        if ( head != null ) { 
                            [] | leftHead
                            [] | rightHead
                            for ( tail -> tmp) {
                                if ( tmp < head ) {
                                    #[tmp] | leftHead
                                } 
                                else {
                                    #[tmp] | rightHead
                                }
                            }
                            leftHead | stdout
                            ["==="] | stdout
                            rightHead | stdout
                        }
                    }
                    [5, 6, 3, 2, 7, 8] | sort()
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=3, symbol='null'}
                stdout: SymbolData{type=Int, value=2, symbol='null'}
                stdout: TerminalData{type=String, value="==="}
                stdout: SymbolData{type=Int, value=6, symbol='null'}
                stdout: SymbolData{type=Int, value=7, symbol='null'}
                stdout: SymbolData{type=Int, value=8, symbol='null'}
                """, output.toString());
//        assertEquals("""
//                        SymbolTable{
//                        symbol='root', parentSymbolTable=, SymbolItemTreeMap=
//                        in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        sort: SymbolItem{symbol='sort', type=Function, value=( ) { in | [ head ; tail ] if ( head != null ) { [ ] | leftHead [ ] | rightHead for ( tail -> tmp ) { if ( tmp < head ) { # [ tmp ] | leftHead [ "if" ] | stdout } else { # [ tmp ] | rightHead [ "else" ] | stdout } } } } }
//                        stdin: SymbolItem{symbol='stdin', type=Flow, value=StdInFlow{cacheFlow=ListFlow{symbol='null', dataList=[], nextFlow=null}}}
//                        stdout: SymbolItem{symbol='stdout', type=Flow, value=StdOutFlow{}}
//                        tmp0: SymbolItem{symbol='tmp0', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp0', parentSymbolTable=root, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp1: SymbolItem{symbol='tmp1', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp1', parentSymbolTable=tmp0, SymbolItemTreeMap=
//                        |-- head: SymbolItem{symbol='head', type=Data, value=SymbolData{type=Int, value=5, symbol='head'}}
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[TerminalData{type=Int, value=5}, TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=HeadTailFlow{headData=SymbolData{type=Int, value=5, symbol='head'}, tailDataList=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=null}, setHead=false}}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tail: SymbolItem{symbol='tail', type=Flow, value=ListFlow{symbol='tail', dataList=[TerminalData{type=Int, value=6}, TerminalData{type=Int, value=3}, TerminalData{type=Int, value=2}, TerminalData{type=Int, value=7}, TerminalData{type=Int, value=8}], nextFlow=null}}
//                        |-- tmp2: SymbolItem{symbol='tmp2', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp2', parentSymbolTable=tmp1, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- leftHead: SymbolItem{symbol='leftHead', type=Flow, value=ListFlow{symbol='leftHead', dataList=[SymbolData{type=Int, value=3, symbol='null'}, SymbolData{type=Int, value=2, symbol='null'}], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- rightHead: SymbolItem{symbol='rightHead', type=Flow, value=ListFlow{symbol='rightHead', dataList=[SymbolData{type=Int, value=6, symbol='null'}, SymbolData{type=Int, value=7, symbol='null'}, SymbolData{type=Int, value=8, symbol='null'}], nextFlow=null}}
//                        |-- tmp: SymbolItem{symbol='tmp', type=Data, value=SymbolData{type=Int, value=8, symbol='tmp'}}
//                        |-- tmp3: SymbolItem{symbol='tmp3', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp3', parentSymbolTable=tmp2, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        |-- tmp4: SymbolItem{symbol='tmp4', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp4', parentSymbolTable=tmp3, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        |-- tmp5: SymbolItem{symbol='tmp5', type=BlockSymbolTable, value=SymbolTable{
//                        symbol='tmp5', parentSymbolTable=tmp3, SymbolItemTreeMap=
//                        |-- in: SymbolItem{symbol='in', type=Flow, value=ListFlow{symbol='in', dataList=[], nextFlow=null}}
//                        |-- out: SymbolItem{symbol='out', type=Flow, value=ListFlow{symbol='out', dataList=[], nextFlow=null}}
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        }
//                        """,
//                SymbolTable.CurrentSymbolTable().toString());
    }

    @Test
    public void TestQuickSortExample4() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SymbolTable.Clear();
            FldwCompiler.parse("""
                    import std.Std
                    function sort() {
                        in | [!head;!tail]
                        if ( head != null ) { 
                            [] | !leftHead
                            [] | !rightHead
                            for ( tail -> !tmp) {
                                if ( tmp < head ) {
                                    #[tmp] | leftHead
                                } 
                                else {
                                    #[tmp] | rightHead
                                }
                            }
                            leftHead | sort() | out
                            [head] | out
                            rightHead | sort() | out
                        } | out
                    }
                    [5, 6, 3, 2, 7, 8] | sort() | stdout
                    """);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.setOut(System.out);

        assertEquals("""
                stdout: SymbolData{type=Int, value=2, symbol='head'}
                stdout: SymbolData{type=Int, value=3, symbol='head'}
                stdout: SymbolData{type=Int, value=5, symbol='head'}
                stdout: SymbolData{type=Int, value=6, symbol='head'}
                stdout: SymbolData{type=Int, value=7, symbol='head'}
                stdout: SymbolData{type=Int, value=8, symbol='head'}
                """, output.toString());
    }
}
