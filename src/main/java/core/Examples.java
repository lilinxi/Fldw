package core;

public class Examples {
    public static String QuickSortExample = "function sort() {\n" +
            "\tif ( 1<2 ) { }\n" +
            "\telse { \n" +
            "\t\tin | [head|tail]\n" +
            "\t\ttail | x\n" +
            "\t\tif ( x < head ) {\n" +
            "\t\t\tx | a\n" +
            "\t\t} else {\n" +
            "\t\t\tx | b\n" +
            "\t\t}\n" +
            "\t\ta|sort|a\n" +
            "\t\tb|sort|b\n" +
            " \t\t[a, head, b] | out\n" +
            "\t}\n" +
            " }\n" +
            "\n" +
            "[5, 6, 3, 2, 7, 8] | sort | stdout";
}
