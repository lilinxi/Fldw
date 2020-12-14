package core;

import javacc.FldwCompiler;
import javacc.ParseException;

import java.io.StringReader;

/**
 * 延迟函数流：支持函数的递归调用，仅在函数实际解释执行时再解析函数的语法树
 */
public class DelayFuncFlow extends Flow {
    private String symbol;          // 函数的符号
    private String funcValue;       // 函数体
    private Flowable paramFlow;     // 函数参数流
    private FuncFlow funcFlow;      // 函数流
    private Flowable nextFlow;      // 函数流的下一流

    public DelayFuncFlow(String symbol, String funcValue) {
        this.symbol = symbol;
        this.funcValue = funcValue;
        this.paramFlow = null;
        this.funcFlow = null;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return this.symbol + "(" + this.paramFlow.GetSymbol() + ")" + this.funcFlow.GetSymbol();
    }

    @Override
    public void SetParamFlow(Flowable paramFlow) throws ExplainException {
        this.paramFlow = paramFlow;
    }

    // 延迟解释函数流
    public FuncFlow getFuncFlow() throws ExplainException {
        if (this.funcFlow == null) {
            try {
                this.funcFlow = new FldwCompiler(new StringReader(this.funcValue)).make_func_flow(this.symbol);
                if (this.paramFlow != null) {
                    this.funcFlow.SetParamFlow(paramFlow);
                }
                if (this.nextFlow != null) {
                    this.funcFlow.SetNextFlowing(this.nextFlow);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.funcFlow;
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return this.getFuncFlow().Push(data);
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        return this.getFuncFlow().Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.getFuncFlow().Push(index, data);
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        return this.getFuncFlow().Match(flow);
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.getFuncFlow().Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.getFuncFlow().inLen();
    }

    @Override
    public int outLen() throws ExplainException {
        return this.getFuncFlow().outLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.getFuncFlow().Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        this.nextFlow = flow;
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.nextFlow;
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing() throws ExplainException {
        this.getFuncFlow().SetFlowOp(this.flowOp);

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
