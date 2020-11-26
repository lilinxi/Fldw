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
                  [5, 6, 3, 2, 7, 8] | [a, b, c, d] | [head;tail]
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
