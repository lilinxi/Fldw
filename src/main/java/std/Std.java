package std;

import core.SymbolTable;

public class Std {
    public static String StdInFlowSymbol = "stdin";
    public static String StdOutFlowSymbol = "stdout";

    static {
        System.out.println("Std loading...");
        SymbolTable.RootSymbolTable.PutSymbol(StdInFlowSymbol, SymbolTable.SymbolType.Flow, null);
        SymbolTable.RootSymbolTable.PutSymbol(StdOutFlowSymbol, SymbolTable.SymbolType.Flow, null);
        System.out.println("Std loaded");
    }
}
