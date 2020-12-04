package core;

public class ForFlow extends Flow {
    private Flowable iterFlow;
    private SymbolData iterSymbolData;
    private BlockFlow forBlockFlow;

    public ForFlow(Flowable iterFlow, SymbolData iterSymbolData, BlockFlow forBlockFlow) {
        this.iterFlow = iterFlow;
        this.iterSymbolData = iterSymbolData;
        this.forBlockFlow = forBlockFlow;
    }

    @Override
    public boolean Push(Datable data) {
        return this.forBlockFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) {
        return this.forBlockFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) {
        return this.forBlockFlow.Push(index, data);
    }

    @Override
    public Datable Pop() {
        return this.forBlockFlow.Pop();
    }

    @Override
    public int inLen() {
        return this.forBlockFlow.inLen();
    }

    @Override
    public int outLen() {
        return this.forBlockFlow.outLen();
    }

    @Override
    public Datable Get(int index) {
        return this.forBlockFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) {
        this.forBlockFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() {
        return this.forBlockFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() {
        return this.forBlockFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() {
        while (this.iterFlow.outLen() > 0) {
//            System.err.println(this.iterFlow);
//            System.err.println(this.forBlockFlow);
            this.iterSymbolData.Push(this.iterFlow.Pop());
            boolean success = this.forBlockFlow.Flowing();
            if (!success) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ForFlow{" +
                "iterFlow=" + iterFlow +
                ", iterSymbolData=" + iterSymbolData +
                ", forBlockFlow=" + forBlockFlow +
                '}';
    }
}
