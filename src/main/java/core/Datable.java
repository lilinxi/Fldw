package core;

// 支持的数据所提供的接口
public interface Datable {
    public static enum DataType { // 数据支持的类型
        Int, Double, Bool, String
    }

    DataType GetType();// 获取类型

    Object GetValue();// 获取值

    String GetSymbol();// 获取符号

    boolean SetType(DataType type);// 设置类型，返回是否设置成功

    boolean SetValue(Object value);// 设置值，返回是否设置成功，需要先设置类型再设置值
}
