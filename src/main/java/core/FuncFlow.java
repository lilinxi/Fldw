package core;

/**
 * 函数流
 */
public class FuncFlow extends Flow {
    private Flowable paramFlow;     // 参数流
    private BlockFlow blockFlow;    // 函数语句块流

    public FuncFlow(ListFlow paramFlow, BlockFlow blockFlow) {
        this.paramFlow = paramFlow;
        this.blockFlow = blockFlow;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return this.blockFlow.GetSymbol();
    }

    @Override
    public void SetFlowOp(FlowOp flowOp) throws ExplainException {
        super.SetFlowOp(flowOp);
        this.blockFlow.SetFlowOp(flowOp);
    }

    @Override
    public void SetParamFlow(Flowable paramFlow) throws ExplainException {
        if (!paramFlow.Match(this.paramFlow)) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return this.blockFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        return this.blockFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.blockFlow.Push(index, data);
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.blockFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.blockFlow.inLen();
    }

    @Override
    public int outLen() throws ExplainException {
        return this.blockFlow.outLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.blockFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        this.blockFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.blockFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.blockFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() throws ExplainException {
        return this.blockFlow.Flowing();
    }

    @Override
    public String toString() {
        return "FuncFlow{" +
                "paramFlow=" + paramFlow +
                ", blockFlow=" + blockFlow +
                '}';
    }
}
