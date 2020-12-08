package core;

// 支持的数据所提供的接口
public interface Datable {
    enum DataType { // 数据支持的类型
        Int, Double, Bool, String
    }

//    int GetIdentity() throws ExplainException; // 获取内存地址

    boolean Push(Datable data) throws ExplainException; // 数据的类型和值流向数据，先赋值类型后赋值值，返回是否设置成功

    DataType GetType() throws ExplainException; // 获取类型

    Object GetValue() throws ExplainException; // 获取值

    String GetSymbol() throws ExplainException; // 获取符号

    boolean SetType(DataType type) throws ExplainException; // 设置类型，返回是否设置成功

    boolean SetValue(Object value) throws ExplainException; // 设置值，返回是否设置成功，需要先设置类型再设置值

    boolean equals(Datable data) throws ExplainException;// 判断类型和值是否都相等

    Datable Clone() throws ExplainException;
}
