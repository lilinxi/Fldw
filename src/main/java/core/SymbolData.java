package core;

/**
 * 符号值，创建是只指定符号，类型定义后不可修改，可以修改值为多个符合类型的值
 */
public class SymbolData extends Data {
    private DataType type;  // 类型
    private Object value;   // 值
    private String symbol;  // 符号

    public SymbolData() {
        this.type = null;
        this.value = null;
        this.symbol = null;
    }

    public SymbolData(String symbol) {
        super();
        this.symbol = symbol;
    }

    @Override
    public boolean Push(Datable symbolData) throws ExplainException {
        boolean ret = true;
        this.SetType(symbolData.GetType());
        this.SetValue(symbolData.GetValue());
        return ret;
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
        return this.symbol;
    }

    @Override
    public boolean SetType(DataType type) throws ExplainException {
        if (this.type == null) {
            this.type = type;
            return true;
        } else return this.type == type;
    }

    @Override
    public boolean SetValue(Object value) throws ExplainException {
        if (this.type == null) {
            throw new ExplainException("get set value without type");
        } else {
            switch (this.type) {
                case Int -> {
                    try {
                        this.value = Integer.parseInt(value.toString());
                    } catch (NumberFormatException e) {
                        throw new ExplainException("type mismatch with " + DataType.Int + " and " + value);
                    }
                }
                case Double -> { // 隐含了 int 到 double 的转换
                    try {
                        this.value = Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        throw new ExplainException("type mismatch with " + DataType.Double + " and " + value);
                    }
                }
                case Bool -> { // 隐含了类型转换，不是 True 或 true 即为 false
                    this.value = Boolean.parseBoolean(value.toString());
                }
                case String -> { // 是否隐含了 int, double, bool 到 string 的类型转换
                    this.value = value.toString();
                }
                default -> {
                    throw new ExplainException("unexpected type: " + this.type);
                }
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return "SymbolData{" +
                "type=" + type +
                ", value=" + value +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
