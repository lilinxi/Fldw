package core;

public class FuncFlow extends Flow {
    private String symbol;
    private ListFlow paramFlow;
    private BlockFlow blockFlow;

    public FuncFlow(String symbol, ListFlow paramFlow, BlockFlow blockFlow) {
        this.symbol = symbol;
        this.paramFlow = paramFlow;
        this.blockFlow = blockFlow;
    }

    @Override
    public String GetSymbol() {
        return null;
    }

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public boolean Push(Flowable flow) {
        return false;
    }

    @Override
    public Datable Pop() {
        return null;
    }

    @Override
    public int inLen() {
        return 0;
    }

    @Override
    public void SetNext(Flowable flow) {

    }

    @Override
    public Flowable Next() {
        return null;
    }

    @Override
    public boolean HasNext() {
        return false;
    }

    @Override
    public boolean Flowing() {
        return false;
    }

    @Override
    public String toString() {
        return "FuncFlow{}";
    }
}
