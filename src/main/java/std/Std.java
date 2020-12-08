package std;

import core.ExplainException;
import core.SymbolTable;

/**
 * Std 包，包含 stdin 和 stdout
 */
public class Std {
    public static String StdInFlowSymbol = "stdin";
    public static String StdOutFlowSymbol = "stdout";

    //    默认调用 Load 方法将标准包的流导入到当前符号表中
    public static void Load() throws ExplainException {
        SymbolTable.CurrentSymbolTable().PutSymbol(StdInFlowSymbol, SymbolTable.SymbolType.Flow, new StdInFlow());
        SymbolTable.CurrentSymbolTable().PutSymbol(StdOutFlowSymbol, SymbolTable.SymbolType.Flow, new StdOutFlow());
    }
}
