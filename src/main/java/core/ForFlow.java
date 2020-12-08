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
    public boolean Push(Datable data) throws ExplainException{
        return this.forBlockFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow)throws ExplainException {
        return this.forBlockFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data)throws ExplainException {
        return this.forBlockFlow.Push(index, data);
    }

    @Override
    public Datable Pop()throws ExplainException {
        return this.forBlockFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException{
        return this.forBlockFlow.inLen();
    }

    @Override
    public int outLen() throws ExplainException{
        return this.forBlockFlow.outLen();
    }

    @Override
    public Datable Get(int index)throws ExplainException {
        return this.forBlockFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException{
        this.forBlockFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() throws ExplainException{
        return this.forBlockFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException{
        return this.forBlockFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() throws ExplainException{
        for (int i = 0; i < this.iterFlow.outLen(); i++) {
            this.iterSymbolData.Push(this.iterFlow.Get(i));
//            System.err.println("1");
//            System.err.println(this.iterFlow.Get(i));
//            System.err.println(this.iterSymbolData);
//            System.err.println(this.forBlockFlow);
            boolean success = this.forBlockFlow.Flowing();
//            System.err.println("2");
            if (!success) return false;
//            boolean success = this.nextFlowing.Push(this.Get(i));
//            if (!success) return false;
        }
//        while (this.iterFlow.outLen() > 0) {
////            System.err.println(this.iterFlow);
////            System.err.println(this.forBlockFlow);
//            System.err.println("1");
//            System.err.println(this.forBlockFlow);
//            this.iterSymbolData.Push(this.iterFlow.Pop());
//            boolean success = this.forBlockFlow.Flowing();
//            System.err.println("2");
//            if (!success) return false;
//        }
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
