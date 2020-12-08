package core;

/**
 * if-else 控制流
 */
public class IfElseFlow extends Flow {
    private Datable conditionData;  // 判断条件，因为是动态类型语言，所以每次运行时都要重新判定类型
    private Flowable trueFlow;      // 真值流
    private Flowable falseFlow;     // 假值流
    private Flowable nextFlow;      // 下一流

    public IfElseFlow(Datable conditionData, Flowable trueFlow, Flowable falseFlow) {
        this.conditionData = conditionData;
        this.trueFlow = trueFlow;
        this.falseFlow = falseFlow;
        this.nextFlow = null;
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(data);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Push(data);
        }
        return true;
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(flow);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Push(flow);
        }
        return true;
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(index, data);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Push(index, data);
        }
        return true;
    }

    @Override
    public Datable Pop() throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Pop();
        } else if (this.falseFlow != null) {
            return this.falseFlow.Pop();
        }
        return null;
    }

    @Override
    public int inLen() throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.inLen();
        } else if (this.falseFlow != null) {
            return this.falseFlow.inLen();
        }
        return 0;
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Get(index);
        } else if (this.falseFlow != null) {
            return this.falseFlow.Get(index);
        }
        return null;
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException { // 同时绑定 nextFlow
        this.trueFlow.SetNextFlowing(flow);
        if (this.falseFlow != null) {
            this.falseFlow.SetNextFlowing(flow);
        }
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
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Flowing();
        } else if (this.falseFlow != null) {
            return this.falseFlow.Flowing();
        }
        return this.nextFlow.Flowing(); // 判断为假且没有假值流，直接接下一流
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
