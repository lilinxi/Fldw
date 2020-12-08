package core;

public class WhileFlow extends Flow {
    private Datable conditionData; // 因为是动态类型语言，所以每次运行时都要重新判定类型
    private Flowable trueFlow;
    private Flowable nextFlow;

    public WhileFlow(Datable conditionData, Flowable trueFlow) {
        this.conditionData = conditionData;
        this.trueFlow = trueFlow;
    }

    @Override
    public boolean Push(Datable data) throws ExplainException{
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(data);
        } else {
            return false;
        }
    }

    @Override
    public boolean Push(Flowable flow)throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(flow);
        } else {
            return false;
        }
    }

    @Override
    public boolean Push(int index, Datable data)throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(index, data);
        } else {
            return false;
        }
    }

    @Override
    public Datable Pop()throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Pop();
        } else {
            return null;
        }
    }

    @Override
    public int inLen()throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.inLen();
        } else {
            return 0;
        }
    }

    @Override
    public Datable Get(int index)throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Get(index);
        } else {
            return null;
        }
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException{ // 同时绑定 nextFlow
        this.trueFlow.SetNextFlowing(flow);
        this.nextFlow = flow;
    }

    @Override
    public Flowable NextFlowing()throws ExplainException {
        return this.nextFlow;
    }

    @Override
    public boolean HasNextFlowing()throws ExplainException {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing()throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        while (conditionData.GetValue().equals(true)) {
            boolean success = this.trueFlow.Flowing();
            if (!success) return false;
            if (conditionData.GetType() != Datable.DataType.Bool) {
                throw new RuntimeException("type mismatch");
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "WhileFlow{" +
                "conditionData=" + conditionData +
                ", trueFlow=" + trueFlow +
                ", nextFlow=" + nextFlow +
                '}';
    }
}
