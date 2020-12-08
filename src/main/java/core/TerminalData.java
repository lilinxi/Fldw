package core;

/**
 * 终结符，创建时指定了类型和值，不可以修改
 */
public class TerminalData extends Data {
    private final DataType type;
    private final Object value;

    public TerminalData(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean Push(Datable data)throws ExplainException {
        return false;
    }

    @Override
    public DataType GetType() throws ExplainException{
        return this.type;
    }

    @Override
    public Object GetValue()throws ExplainException {
        return this.value;
    }

    @Override
    public String GetSymbol()throws ExplainException {
        throw new RuntimeException("wrong call");
    }

    @Override
    public boolean SetType(DataType type)throws ExplainException {
        throw new RuntimeException("wrong call");
    }

    @Override
    public boolean SetValue(Object value)throws ExplainException {
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
