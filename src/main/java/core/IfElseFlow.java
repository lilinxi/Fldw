package core;

public class IfElseFlow extends Flow {
    private Datable conditionData; // 因为是动态类型语言，所以每次运行时都要重新判定类型
    private Flowable trueFlow;
    private Flowable falseFlow;
    private Flowable nextFlow;
    private ListFlow cacheFlow;

    public IfElseFlow(Datable conditionData, Flowable trueFlow, Flowable falseFlow) {
        this.conditionData = conditionData;
        this.trueFlow = trueFlow;
        this.falseFlow = falseFlow;
        this.nextFlow = null;
        this.cacheFlow = new ListFlow();
    }

    @Override
    public boolean Push(Datable data)throws ExplainException {
//        return this.cacheFlow.Push(data);
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(data);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Push(data);
        }
        return true;
    }

    @Override
    public boolean Push(Flowable flow)throws ExplainException {
//        return this.cacheFlow.Push(flow);
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(flow);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Push(flow);
        }
        return true;
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException{
//        return this.cacheFlow.Push(index,data);
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(index, data);
        } else if (this.falseFlow != null)  {
            return this.falseFlow.Push(index, data);
        }
        return true;
    }

    @Override
    public Datable Pop() throws ExplainException{
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Pop();
        } else if (this.falseFlow != null) {
            return this.falseFlow.Pop();
        }
        return null;
    }

    @Override
    public int inLen()throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.inLen();
        } else if (this.falseFlow != null) {
            return this.falseFlow.outLen();
        }
        return 0;
    }

    @Override
    public Datable Get(int index) throws ExplainException{
//        return this.cacheFlow.Get(index);
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Get(index);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Get(index);
        }
        return null;
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException{ // 同时绑定 nextFlow
        this.trueFlow.SetNextFlowing(flow);
        if (this.falseFlow != null) {
            this.falseFlow.SetNextFlowing(flow);
        }
        this.nextFlow = flow;
    }

    @Override
    public Flowable NextFlowing()throws ExplainException {
        return this.nextFlow;
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException{
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing() throws ExplainException{
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        boolean ret = true;
        if (conditionData.GetValue().equals(true)) {
//            this.trueFlow.SetNextFlowing(this.nextFlow);
//            this.trueFlow.Push(this.cacheFlow);
            ret = this.trueFlow.Flowing();
        } else if (this.falseFlow != null) {
//            this.falseFlow.SetNextFlowing(this.nextFlow);
//            this.falseFlow.Push(this.cacheFlow);
            ret = this.falseFlow.Flowing();
        }
        return ret;
    }

    @Override
    public String toString() {
        return "IfElseFlow{" +
                "conditionData=" + conditionData +
                ", trueFlow=" + trueFlow +
                ", falseFlow=" + falseFlow +
                ", nextFlow=" + nextFlow +
                '}';
    }
}
