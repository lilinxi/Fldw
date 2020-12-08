package core;

/**
 * 终结符，创建时指定了类型和值，不可以修改
 */
public class TerminalData extends Data {
    private final DataType type;    // 类型
    private final Object value;     // 值

    public TerminalData(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public DataType GetType() throws ExplainException {
        return this.type;
    }

    @Override
    public Object GetValue() throws ExplainException {
        return this.value;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return this.type.toString() + "_" + this.value.toString();
    }

    @Override
    public String toString() {
        return "TerminalData{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
