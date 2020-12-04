package core;

public class BoolExprData extends Data {
    public boolean GetBoolValue() {
        return Boolean.getBoolean(this.GetValue().toString());
    }

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public DataType GetType() {
        return null;
    }

    @Override
    public Object GetValue() {
        return null;
    }

    @Override
    public String GetSymbol() {
        return null;
    }

    @Override
    public boolean SetType(DataType type) {
        return false;
    }

    @Override
    public boolean SetValue(Object value) {
        return false;
    }
}
