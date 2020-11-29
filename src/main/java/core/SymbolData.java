package core;

/**
 * 符号值，创建是只指定符号，类型定义后不可修改，可以修改值为多个符合类型的值
 */
public class SymbolData implements Datable {
    private DataType type;
    private Object value;
    private String symbol;

    public SymbolData(String symbol) {
        this.symbol = symbol;
    }


    @Override
    public boolean Push(Datable symbolData) {
        this.SetType(symbolData.GetType());
        this.SetValue(symbolData.GetValue());
        return true;
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
        return this.symbol;
    }

    @Override
    public boolean SetType(DataType type) {
        if (this.type == null) {
            this.type = type;
            return true;
        } else if (this.type == type) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean SetValue(Object value) {
        if (this.type == null) {
            return false;
        } else {
            switch (this.type) {
                case Int -> {
                    try {
                        this.value = Integer.parseInt(value.toString());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("type mismatch with " + DataType.Int + " and " + value);
                    }
                }
                case Double -> { // 隐含了 int 到 double 的转换
                    try {
                        this.value = Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("type mismatch with " + DataType.Double + " and " + value);
                    }
                }
                case Bool -> { // 隐含了类型转换，不是 True 或 true 即为 false
                    this.value = Boolean.parseBoolean(value.toString());
                }
                case String -> { // TODO: 是否隐含了 int, double, bool 到 string 的类型转换
                    this.value = value.toString();
                }
                default -> {
                    throw new RuntimeException("unreachable");
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
