package core;

/**
 * 支持的流所提供的接口
 */
public interface Flowable {
    enum FlowOp { // 流操作符
        Pushing,    // 数据管道流动（默认）
        Matching    // 模式匹配流动
    }

    String GetSymbol() throws ExplainException;                         // 获取流的符号

    void SetFlowOp(FlowOp flowOp) throws ExplainException;              // 设置流操作

    boolean Push(Datable data) throws ExplainException;                 // 流入一个元素，返回是否成功

    boolean Push(Flowable flow) throws ExplainException;                // 流入一个流，返回是否成功

    boolean Push(int index, Datable data) throws ExplainException;      // 将一个元素的类型和值流入到一个元素的符号中，返回是否成功

    boolean Match(Flowable flow) throws ExplainException;               // 将当前流的值模式匹配到参数流

    // 设计时考虑 Pop 和 Push 互为逆运算，实际实现时 Push 和 Match 互为对偶运算，故考虑 Pop 可弃用
    @Deprecated
    Datable Pop() throws ExplainException;                              // 流出一个元素，若无元素则流出 null

    int inLen() throws ExplainException;                                // 当前流输入长度

    int outLen() throws ExplainException;                               // 当前流输出长度

    Datable Get(int index) throws ExplainException;                     // 获取某个位置上的元素

    void SetNextFlowing(Flowable flow) throws ExplainException;         // 设置下一个流

    Flowable NextFlowing() throws ExplainException;                     // 返回下一个流

    boolean HasNextFlowing() throws ExplainException;                   // 是否有下一个流

    boolean Flowing() throws ExplainException;                          // 解释执行：开始元素的流动
}
