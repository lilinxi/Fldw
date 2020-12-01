package std;

import core.SymbolTable;

public class Std {
    public static String StdInFlowSymbol = "stdin";
    public static String StdOutFlowSymbol = "stdout";

    static {
//        System.out.println("Std loading...");
        SymbolTable.CurrentSymbolTable().PutSymbol(StdInFlowSymbol, SymbolTable.SymbolType.Flow, StdInFlow.GetInstance());
        SymbolTable.CurrentSymbolTable().PutSymbol(StdOutFlowSymbol, SymbolTable.SymbolType.Flow, StdOutFlow.GetInstance());
//        System.out.println("Std loaded");
    }
}
