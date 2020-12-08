package core;

// Datable 默认实现类
public class Data extends Symbol implements Datable {
//    @Override
//    public int GetIdentity() {
//        return System.identityHashCode(this);
//    }

    @Override
    public boolean Push(Datable data) throws ExplainException{
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public DataType GetType()throws ExplainException {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public Object GetValue() throws ExplainException{
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public String GetSymbol()throws ExplainException {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean SetType(DataType type) throws ExplainException{
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean SetValue(Object value)throws ExplainException {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean equals(Datable data) throws ExplainException{
        if (this.GetType() == null && data.GetType() == null) {
            return true;
        } else if (this.GetType() == data.GetType() && this.GetValue().toString().equals(data.GetValue().toString())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Datable Clone()throws ExplainException {
        return this;
    }
}
