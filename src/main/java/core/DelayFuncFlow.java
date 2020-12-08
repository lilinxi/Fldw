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

    public FuncFlow getFuncFlow() throws ExplainException{
        if (this.funcFlow == null) {
            try {
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
    public boolean Push(Datable data)throws ExplainException {
        return this.getFuncFlow().Push(data);
    }

    @Override
    public void SetNextFlowing(Flowable flow)throws ExplainException {
        this.nextFlow = flow;
    }

    @Override
    public Flowable NextFlowing()throws ExplainException {
        return this.nextFlow;
    }

    @Override
    public boolean HasNextFlowing()throws ExplainException {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing()throws ExplainException {
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
