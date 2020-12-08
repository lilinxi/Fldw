package core;

// Flowable 默认实现类
public class Flow extends Symbol implements Flowable {
    protected FlowOp flowOp;

    public Flow() {
        this.flowOp = FlowOp.Pushing;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return null;
    }

    @Override
    public void SetFlowOp(FlowOp flowOp) throws ExplainException {
        this.flowOp = flowOp;
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public Datable Pop() throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public int inLen() throws ExplainException {
        return 0;
    }

    @Override
    public int outLen() throws ExplainException { // 默认输出长度等于输入长度
        return this.inLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return false;
    }

    @Override
    public boolean Flowing() throws ExplainException {
        return true;
    }
}
