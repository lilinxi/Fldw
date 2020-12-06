package core;

public class IfElseFlow extends Flow {
    private ExprData conditionData; // 因为是动态类型语言，所以每次运行时都要重新判定类型
    private Flowable trueFlow;
    private Flowable falseFlow;
    private Flowable nextFlow;

    public IfElseFlow(ExprData conditionData, Flowable trueFlow, Flowable falseFlow) {
        this.conditionData = conditionData;
        this.trueFlow = trueFlow;
        this.falseFlow = falseFlow;
        this.nextFlow = null;
    }

    @Override
    public boolean Push(Datable data) {
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
    public boolean Push(Flowable flow) {
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
    public boolean Push(int index, Datable data) {
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
    public Datable Pop() {
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
    public int inLen() {
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
    public Datable Get(int index) {
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
    public void SetNextFlowing(Flowable flow) { // 同时绑定 nextFlow
        this.trueFlow.SetNextFlowing(flow);
        this.falseFlow.SetNextFlowing(flow);
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
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new RuntimeException("type mismatch");
        }
        boolean ret = true;
        if (conditionData.GetValue().equals(true)) {
            ret = this.trueFlow.Flowing();
        } else if (this.falseFlow != null) {
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
