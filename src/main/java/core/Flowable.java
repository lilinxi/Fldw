package core;

// 支持的流所提供的接口
public interface Flowable {
    String GetSymbol();// 获取流的符号

    boolean Push(Datable data); // 流入一个元素，返回是否成功

    boolean Push(Flowable flow); // 流入一个流，返回是否成功

    Datable Pop(); // 流出一个元素，若无元素则流出 null

    int Len(); // 当前流长度

    void SetNext(Flowable flow); // 设置下一个流

    Flowable Next(); // 下一个流

    boolean HasNext(); // 是否有下一个流

    boolean Flowing(); // 开始元素的流动
}
