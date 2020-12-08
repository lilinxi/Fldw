package core;

/**
 * 延迟流：
 * <p>
 * 当一个符号-流被第二次获取的时候，应该复制其他数据，但是保留某些数据为未赋值的状态，延迟到实际运行时再被赋值
 * 否则一个语句块的执行过程中，前面的语句输入到了后面语句的流，会被立即解释执行而导致不可预期的结果
 * <p>
 * 1. nextFlow 需要保留未赋值
 * 2. flowOp 需要保留未赋值
 */
public class DelayFlow extends Flow {
    private Flowable curFlow;   // 当前复制流
    private Flowable nextFlow;  // 下一流

    public DelayFlow(Flowable flow) {
        this.curFlow = flow;
        this.nextFlow = null;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return "delay_" + this.curFlow.GetSymbol();
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return this.curFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        return this.curFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.curFlow.Push(index, data);
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        return this.curFlow.Match(flow);
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.curFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.curFlow.inLen();
    }

    @Override
    public int outLen() throws ExplainException {
        return this.curFlow.outLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.curFlow.Get(index);
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
        this.curFlow.SetFlowOp(this.flowOp); // 延迟设置流操作符
        if (this.nextFlow != null) {
            this.curFlow.SetNextFlowing(this.nextFlow); // 延迟设置下一流
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
