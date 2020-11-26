package std;

import core.SymbolTable;

public class Std {
    public static String StdInFlowSymbol = "stdin";
    public static String StdOutFlowSymbol = "stdout";

    static {
        SymbolTable.RootSymbolTable.PutSymbol(StdInFlowSymbol, SymbolTable.SymbolType.Flow, null);
        SymbolTable.RootSymbolTable.PutSymbol(StdOutFlowSymbol, SymbolTable.SymbolType.Flow, null);
    }
}
