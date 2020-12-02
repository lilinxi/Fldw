package core;

public class FuncFlow extends Flow {
    public final static String FuncSymbolTableSuffix = "_funcSymbolTable";

    private String symbol;
    private ListFlow paramFlow;
    private BlockFlow blockFlow;

    public FuncFlow(String symbol, ListFlow paramFlow, BlockFlow blockFlow) {
        this.symbol = symbol;
        this.paramFlow = paramFlow;
        this.blockFlow = blockFlow;
    }

    public FuncFlow(FuncFlow funcFlow, ListFlow paramFlow) {
        this.symbol = funcFlow.symbol;
        this.paramFlow = funcFlow.paramFlow;
        this.blockFlow = funcFlow.blockFlow;
        paramFlow.SetNext(this.paramFlow);
        paramFlow.Flowing();
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
    public void SetNext(Flowable flow) {
        this.blockFlow.SetNext(flow);
    }

    @Override
    public Flowable Next() {
        return this.blockFlow.Next();
    }

    @Override
    public boolean HasNext() {
        return this.blockFlow.HasNext();
    }

    @Override
    public boolean Flowing() {
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
