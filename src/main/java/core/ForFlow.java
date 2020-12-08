package core;

/**
 * For 控制语句流，对流中的每个数据进行一次迭代操作
 */
public class ForFlow extends Flow {
    private Flowable iterFlow;              // 迭代的流
    private SymbolData iterSymbolData;      // 迭代的数据的符号
    private BlockFlow forBlockFlow;         // 迭代的语句块

    public ForFlow(Flowable iterFlow, SymbolData iterSymbolData, BlockFlow forBlockFlow) {
        this.iterFlow = iterFlow;
        this.iterSymbolData = iterSymbolData;
        this.forBlockFlow = forBlockFlow;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        StringBuilder builder = new StringBuilder();
        builder.append("for");
        builder.append("(");
        builder.append(this.iterFlow.GetSymbol());
        builder.append("->");
        builder.append(this.iterSymbolData.GetSymbol());
        builder.append(")");
        builder.append(this.forBlockFlow.GetSymbol());
        return builder.toString();
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return this.forBlockFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        return this.forBlockFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.forBlockFlow.Push(index, data);
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        return this.forBlockFlow.Match(flow);
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.forBlockFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.forBlockFlow.inLen();
    }

    @Override
    public int outLen() throws ExplainException {
        return this.forBlockFlow.outLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.forBlockFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        this.forBlockFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.forBlockFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.forBlockFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() throws ExplainException {
        for (int i = 0; i < this.iterFlow.outLen(); i++) {
            this.iterSymbolData.Push(this.iterFlow.Get(i));
            boolean success = this.forBlockFlow.Flowing();
            if (!success) throw new ExplainException("Flowing Error: " + this.forBlockFlow);
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
