package core;

public class Examples {
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
            		a|sort()|a
            		b|sort()|b
             		[a, head, b] | out
            	}
            }
            [5, 6, 3, 2, 7, 8] | sort() | stdout
            """;

}
