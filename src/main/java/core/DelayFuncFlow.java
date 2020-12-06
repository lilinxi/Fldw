package core;

import javacc.FldwCompiler;
import javacc.ParseException;

import java.io.StringReader;

public class DelayFuncFlow extends Flow {
//    public final static String FuncSymbolTableSuffix = "_funcSymbolTable";

    private String symbol;
    private String funcValue; // delay func make
    private ListFlow paramFlow;
    private FuncFlow funcFlow;
    private Flowable nextFlow;

    public DelayFuncFlow(String symbol, String funcValue) {
        this.symbol = symbol;
        this.funcValue = funcValue;
        this.paramFlow = null;
        this.funcFlow = null;
    }

    public void setParamFlow(ListFlow paramFlow) {
        this.paramFlow = paramFlow;
    }

    public FuncFlow getFuncFlow() {
        if (this.funcFlow == null) {
            try{
                this.funcFlow = new FldwCompiler(new StringReader(this.funcValue)).make_func_flow(this.symbol);
                if (this.paramFlow != null) {
                    funcFlow.setParamFlow(paramFlow);
                }
                if (this.nextFlow != null) {
                    funcFlow.SetNextFlowing(this.nextFlow);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.funcFlow;
    }

    @Override
    public boolean Push(Datable data) {
        return this.getFuncFlow().Push(data);
    }

    @Override
    public void SetNextFlowing(Flowable flow) {
        this.nextFlow = flow;
    }

    @Override
    public boolean Flowing() {
        return this.getFuncFlow().Flowing();
    }

    @Override
    public String toString() {
        return "DelayFuncFlow{" +
                "symbol='" + symbol + '\'' +
                ", funcValue='" + funcValue + '\'' +
                ", paramFlow=" + paramFlow +
                '}';
    }
}
