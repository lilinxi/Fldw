package core;

public class Examples {
    /*****************************************************************/
    /***************************流实例*********************************/
    /*****************************************************************/
    public static String FlowingExample1 = """
            [5, 6, 3, 2, 7, 8] 
            """;
    public static String FlowingExample2 = """
            [5, 6, 3, 2, 7, 8] | sym
            """;
    public static String FlowingExample3 = """
            [5, 6, 3, 2, 7, 8] | sym | sym1
            """;
    public static String FlowingExample4 = """
            [5, 6, 3, 2, 7, 8] | sym | [head;tail]
            """;
    public static String FlowingExample5 = """
            [5, 6, 3, 2, 7, 8] | stdout
            """;
    public static String FlowingExample6 = """
            import std.Std
            [5, 6, 3, 2, 7, 8] | stdout
            """;
    public static String FlowingExample7 = """
            [5, 6, 3, 2, 7, 8] | [a, b, c, d] | [e, f, g, h, i, j]
            """;
    public static String FlowingExample8 = """
            import std.Std
            [5, 6, 3, 2, 7, 8] | [a, b, c, d] | [e, f, g, h, i, j] | stdout
            """;
    public static String FlowingExample9 = """
            import std.Std
            [1, 2, 3, 4] | stdout
            {
                [5, 6, 3, 2, 7, 8] | stdout
            }
            [5, 6, 7, 8] | stdout
            """;
    public static String FlowingExample10 = """
            import std.Std
            stdin | stdout // 1, 1.213, true, "dada"
            """;
    /*****************************************************************/
    /***************************表达式实例*****************************/
    /*****************************************************************/
    public static String ExprDataExample1 = """
            [1+2]         
            """;
    public static String ExprDataExample2 = """
            [1+2, 1+2-3, 2*3, 3/2]         
            """;
    public static String ExprDataExample3 = """
            [(1+2)*3, (3+2.5)/1.5]         
            """;
    public static String ExprDataExample4 = """
            [(1+2)*3, (3+2.5)/1.5] | [a, b]  
            """;
    public static String ExprDataExample5 = """
            [1+2,3*4]         
            """;
    /*****************************************************************/
    /***************************控制语句实例****************************/
    /*****************************************************************/
    public static String StmtExample1 = """
            import std.Std
            if (1>2) {
                [1] | stdout
            } else {
                [2] | stdout
            }
            """;
    public static String StmtExample2 = """
            import std.Std
            [1, 2] | [a, b]
            while(a>b) {
                [a, b] | [b, a]
            }
            [a, b] | stdout
            """;
    public static String StmtExample3 = """
            import std.Std
            [1, 2, 3, 4, 5] -> x
            [x] | stdout
            """;
    public static String StmtExample4 = """
            import std.Std
            [1, 2, 3, 4, 5] -> x
            if (x>3) {
                [x] | stdout
            } else {
                [3] | stdout
            }
            """;
    /*****************************************************************/
    /***************************函数实例*******************************/
    /*****************************************************************/
    public static String FuncExample = """
            import std.Std
            function func() {
                in | [a, b, c]
                [a, b, c] | out
            }
            [1, 2, 3, 4] | func() | stdout
            [1, 2] | func() | stdout
            """;
    public static String QuickSortExample = """
            import std.Std
            function sort() {
             	if ( 1<2 ) { }
             	else { 
             		in | [head;tail]
             		tail | x
             		if ( x < head ) {
             			x | a
             		} else {
             			x | b
             		}
             		a|sort()|out
             		head|out
             		b|sort()|out
             	}
             }
             [5, 6, 3, 2, 7, 8] | sort() | stdout
             """;

}
