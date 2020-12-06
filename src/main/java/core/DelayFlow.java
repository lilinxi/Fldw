package core;

/**
 * 当一个符号-流被第二次获取的时候，应该复制其数据，但是保留某些数据为未赋值的状态，延迟到实际运行时再被赋值
 * 1. 下一流字段需要保留未赋值，
 * 2. matching or pushing 需要保留（TODO）
 */
public class DelayFlow extends Flow {
    private Flowable curFlow;
    private Flowable nextFlow;
//    private FlowOp flowOp; // TODO Flow 中包含

    public DelayFlow(Flowable flow) {
        this.curFlow = flow;
        this.nextFlow = null;
    }

    @Override
    public String GetSymbol() {
        return this.curFlow.GetSymbol() + "_copy";
    }

    @Override
    public boolean Push(Datable data) {
        return this.curFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) {
        return this.curFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) {
        return this.curFlow.Push(index, data);
    }

    @Override
    public boolean Match(Flowable flow) {
        return this.curFlow.Match(flow);
    }

    @Override
    public Datable Pop() {
        return this.curFlow.Pop();
    }

    @Override
    public int inLen() {
        return this.curFlow.inLen();
    }

    @Override
    public int outLen() {
        return this.curFlow.outLen();
    }

    @Override
    public Datable Get(int index) {
        return this.curFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) {
        this.nextFlow = flow;
    }

    @Override
    public Flowable NextFlowing() {
        return this.nextFlow;
    }

    @Override
    public boolean HasNextFlowing() {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing() {
        this.curFlow.SetFlowOp(this.flowOp);
        if (this.nextFlow != null) {
            this.curFlow.SetNextFlowing(this.nextFlow);
        }
        return this.curFlow.Flowing();
    }

    @Override
    public String toString() {
        return "DelayFlow{" +
                "curFlow=" + curFlow +
                ", nextFlow=" + nextFlow +
                '}';
    }
}
