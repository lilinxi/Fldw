package core;

/**
 * 终结符，创建时指定了类型和值，不可以修改
 */
public class TerminalData extends Data {
    private DataType type;
    private final Object value;

    public TerminalData(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public DataType GetType() {
        return this.type;
    }

    @Override
    public Object GetValue() {
        return this.value;
    }

    @Override
    public String GetSymbol() {
        throw new RuntimeException("wrong call");
    }

    @Override
    public boolean SetType(DataType type) {
        throw new RuntimeException("wrong call");
    }

    @Override
    public boolean SetValue(Object value) {
        throw new RuntimeException("wrong call");
    }

    @Override
    public String toString() {
        return "TerminalData{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
