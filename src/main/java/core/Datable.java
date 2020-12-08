package core;

/**
 * 支持的数据所提供的接口
 */
public interface Datable {
    enum DataType { // 数据支持的类型
        Int,        // 整数
        Double,     // 浮点数
        Bool,       // 真假值
        String      // 字符串值
    }

    boolean Push(Datable data) throws ExplainException;             // 数据的类型和值流向数据，先赋值类型后赋值值，返回是否设置成功

    DataType GetType() throws ExplainException;                     // 获取类型

    Object GetValue() throws ExplainException;                      // 获取值

    String GetSymbol() throws ExplainException;                     // 获取符号

    boolean SetType(DataType type) throws ExplainException;         // 设置类型，返回是否设置成功

    boolean SetValue(Object value) throws ExplainException;         // 设置值，返回是否设置成功，需要先设置类型再设置值

    boolean equals(Datable data) throws ExplainException;           // 判断类型和值是否都相等

    Datable Clone() throws ExplainException;                        // 拷贝数据，深拷贝
}
