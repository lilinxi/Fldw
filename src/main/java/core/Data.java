package core;

/**
 * Datable 默认实现类
 */
public class Data extends Symbol implements Datable {
    @Override
    public boolean Push(Datable data) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public DataType GetType() throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public Object GetValue() throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public String GetSymbol() throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean SetType(DataType type) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean SetValue(Object value) throws ExplainException {
        throw new ExplainException("Illegal call");
    }

    @Override
    public boolean equals(Datable data) throws ExplainException {
        // 类型为空则都为 null，判定为相等；否则必须类型和值都相等则判定为相等
        if (this.GetType() == null && data.GetType() == null) {
            return true;
        } else return this.GetType() == data.GetType() && this.GetValue().toString().equals(data.GetValue().toString());
    }

    @Override
    public Datable Clone() throws ExplainException {
        return this;
    }
}
