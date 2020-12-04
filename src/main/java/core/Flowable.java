package core;

// 支持的流所提供的接口
public interface Flowable {
    public static enum FlowOp {
        Pushing,
        Matching
    }
//    int GetIdentity();// 获取内存地址

    String GetSymbol();// 获取流的符号

    boolean Push(Datable data); // 流入一个元素，返回是否成功

    boolean Push(Flowable flow); // 流入一个流，返回是否成功

    boolean Push(int index, Datable data); // 将一个元素的类型和值流入到一个元素的符号中，返回是否成功

    @Deprecated
    Datable Pop(); // 流出一个元素，若无元素则流出 null

    int inLen(); // 当前流输出长度，如果不为 0，则流入操作将转化为匹配操作

    int outLen(); // 当前流输入长度

    Datable Get(int index); // 获取某个位置上的元素

    void SetFlowOp(FlowOp flowOp);

    void SetNextFlowing(Flowable flow); // 设置下一个流

    Flowable NextFlowing(); // 下一个流

    boolean HasNextFlowing(); // 是否有下一个流

    boolean Flowing(); // 解释执行：开始元素的流动

    void SetNextMatching(Flowable flow); // 设置下一个流

    Flowable NextMatching(); // 下一个流

    boolean HasNextMatching(); // 是否有下一个流

    boolean Matching(); // 解释执行：开始元素的匹配
}
