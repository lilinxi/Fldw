package core;

/**
 * 首位流
 * <p>
 * 将流分割为首位的数据符号和末尾的其余数据流
 */
public class HeadTailFlow extends Flow {
    private SymbolData headData;        // 首符号
    private Flowable tailFlow;          // 尾数据流
    private boolean setHead;            // 是否当前设置位为首位

    public HeadTailFlow(SymbolData headData, Flowable tailFlow) {
        this.headData = headData;
        this.tailFlow = tailFlow;
        this.setHead = true;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return this.headData.GetSymbol() + ";" + this.tailFlow.GetSymbol();
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        if (this.setHead) {
            this.headData.Push(data);
            this.setHead = false;
            return true;
        } else {
            return this.tailFlow.Push(data);
        }
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        Datable data;
        while ((data = flow.Pop()) != null) {
            boolean success = this.Push(data);
            if (!success) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HeadTailFlow{" +
                "headData=" + headData +
                ", tailDataList=" + tailFlow +
                ", setHead=" + setHead +
                '}';
    }
}
