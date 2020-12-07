package core;

// Datable 默认实现类
public class Data extends TokenList implements Datable {
//    @Override
//    public int GetIdentity() {
//        return System.identityHashCode(this);
//    }

    @Override
    public boolean Push(Datable data) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public DataType GetType() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public Object GetValue() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public String GetSymbol() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean SetType(DataType type) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean SetValue(Object value) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean equals(Datable data) {
        if (this.GetType() == null && data.GetType() == null) {
            return true;
        } else if (this.GetType() == data.GetType() && this.GetValue() == data.GetValue()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Datable Clone() {
        return this;
    }
}
