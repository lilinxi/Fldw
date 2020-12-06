package core;

public class FuncFlow extends Flow {
//    public final static String FuncSymbolTableSuffix = "_funcSymbolTable";

    private String symbol;
    private String funcValue; // delay func make
    private ListFlow paramFlow;
    private BlockFlow blockFlow;

    private SymbolData tmpData;

//    public FuncFlow(String symbol, String funcValue) {
//        this.symbol = symbol;
//        this.funcValue = funcValue;
//    }

    public FuncFlow(ListFlow paramFlow, BlockFlow blockFlow) {
//        System.err.println("1");
        this.paramFlow = paramFlow;
        this.blockFlow = blockFlow;
    }

//    public FuncFlow(String symbol, ListFlow paramFlow, BlockFlow blockFlow) {
//        System.err.println("2");
//        this.symbol = symbol;
//        this.paramFlow = paramFlow;
//        this.blockFlow = blockFlow;
//    }
//
//    public FuncFlow(FuncFlow funcFlow, ListFlow paramFlow) {
//        System.err.println("3");
//        this.symbol = funcFlow.symbol;
//        this.paramFlow = funcFlow.paramFlow;
//        this.blockFlow = funcFlow.blockFlow;
//        paramFlow.SetNextFlowing(this.paramFlow);
//        paramFlow.SetFlowOp(FlowOp.Matching);
//        paramFlow.Flowing();
//    }

    public void setParamFlow(ListFlow paramFlow) {
        if (!paramFlow.Match(this.paramFlow)) {
            throw new RuntimeException();
        }
//        System.out.println("===");
    }

    @Override
    public int GetIdentity() {
        return -1;
    }

    @Override
    public String GetSymbol() {
        return this.symbol;
    }

    @Override
    public boolean Push(Datable data) {
        return this.blockFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) {
        return this.blockFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) {
        return this.blockFlow.Push(index, data);
    }

    @Override
    public Datable Pop() {
        return this.blockFlow.Pop();
    }

    @Override
    public int inLen() {
        return this.blockFlow.inLen();
    }

    @Override
    public int outLen() {
        return this.blockFlow.outLen();
    }

    @Override
    public Datable Get(int index) {
        return this.blockFlow.Get(index);
    }


    @Override
    public void SetNextFlowing(Flowable flow) {
        this.blockFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() {
        return this.blockFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() {
        return this.blockFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() {
//        new FldwCompiler(new StringReader(this.getValue().toString())).make_func_flow(this.getSymbol());
//        System.out.println("begin func=====");
//        System.err.println("this: "+this);
//        System.err.println("flowing: "+this.blockFlow);
        return this.blockFlow.Flowing();
    }

    @Override
    public String toString() {
        return "FuncFlow{" +
                "symbol='" + symbol + '\'' +
                ", paramFlow=" + paramFlow +
                ", blockFlow=" + blockFlow +
                '}';
    }
}
