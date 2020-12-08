package core;

/**
 * while 数据流
 */
public class WhileFlow extends Flow {
    private Datable conditionData;  // 判断条件，因为是动态类型语言，所以每次运行时都要重新判定类型
    private Flowable trueFlow;      // 真值流
    private Flowable nextFlow;      // 下一流

    public WhileFlow(Datable conditionData, Flowable trueFlow) {
        this.conditionData = conditionData;
        this.trueFlow = trueFlow;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        StringBuilder builder = new StringBuilder();
        builder.append("while");
        builder.append("(");
        builder.append(this.conditionData.GetSymbol());
        builder.append(")");
        builder.append(this.trueFlow.GetSymbol());
        return builder.toString();
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(data);
        } else {
            return false;
        }
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(flow);
        } else {
            return false;
        }
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Push(index, data);
        } else {
            return false;
        }
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Match(flow);
        } else {
            return false;
        }
    }

    @Override
    public Datable Pop() throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Pop();
        } else {
            return null;
        }
    }

    @Override
    public int inLen() throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.inLen();
        } else {
            return 0;
        }
    }

    @Override
    public int outLen() throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.outLen();
        } else {
            return 0;
        }
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        if (conditionData.GetType() != Datable.DataType.Bool) {
            throw new ExplainException("type mismatch " + conditionData.GetType());
        }
        if (conditionData.GetValue().equals(true)) {
            return this.trueFlow.Get(index);
        } else {
            return null;
        }
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException { // 同时绑定 nextFlow
        this.trueFlow.SetNextFlowing(flow);
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
        while (conditionData.GetValue().equals(true)) {
            boolean success = this.trueFlow.Flowing();
            if (!success) throw new ExplainException("Flowing Error: " + this.trueFlow);
            if (conditionData.GetType() != Datable.DataType.Bool) {
                throw new ExplainException("type mismatch " + conditionData.GetType());
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
