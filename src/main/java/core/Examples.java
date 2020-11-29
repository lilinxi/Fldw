package core;

public class Examples {
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
    public static String QuickSortExample = """
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
