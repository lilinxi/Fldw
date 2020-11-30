package core;

// Datable 默认实现类
public class Data implements Datable {
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
}
