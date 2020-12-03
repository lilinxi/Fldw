package core;

public class ForFlow extends Flow {
    private ListFlow listFlow;
    private SymbolData symbolData;
    private BlockFlow blockFlow;

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
        while (this.listFlow.outLen() > 0) {
            this.symbolData.Push(this.listFlow.Pop());
            boolean success = this.blockFlow.Flowing();
            if (!success) return false;
        }
        return true;
    }
}
