package std;

import core.SymbolTable;

public class Std {
    public static String StdInFlowSymbol = "stdin";
    public static String StdOutFlowSymbol = "stdout";

    public static void Load() {
//        System.out.println("Std loading...");
        SymbolTable.CurrentSymbolTable().PutSymbol(StdInFlowSymbol, SymbolTable.SymbolType.Flow, new StdInFlow());
        SymbolTable.CurrentSymbolTable().PutSymbol(StdOutFlowSymbol, SymbolTable.SymbolType.Flow, new StdOutFlow());
//        System.out.println("Std loaded");
    }
}
