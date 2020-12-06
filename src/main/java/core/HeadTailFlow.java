package core;

public class HeadTailFlow extends Flow {
    private SymbolData headData;
    private Flowable tailFlow;
    private boolean setHead;

    public HeadTailFlow(SymbolData headData, Flowable tailFlow) {
        this.headData = headData;
        this.tailFlow = tailFlow;
        this.setHead = true;
    }

    @Override
    public String GetSymbol() {
        throw new RuntimeException("wrong call"); // 不包含 Symbol，无法被符号表查找
    }

    @Override
    public boolean Push(Datable data) {
        if (this.setHead) {
            this.headData.Push(data);
            this.setHead = false;
            return true;
        } else {
            return this.tailFlow.Push(data);
        }
    }

    @Override
    public boolean Push(Flowable flow) {
        Datable data;
        while ((data = flow.Pop()) != null) {
            boolean success = this.Push(data);
            if (!success) return false;
        }
        return true;
    }

    @Override
    public Datable Pop() {
        throw new RuntimeException("wrong call");
    }

    @Override
    public int inLen() {
        return 0;
    }

    @Override
    public void SetNextFlowing(Flowable flow) {
        throw new RuntimeException("can not set next");
    }

    @Override
    public Flowable NextFlowing() {
        throw new RuntimeException("wrong call");
    }

    @Override
    public boolean HasNextFlowing() {
        return false;
    }

    @Override
    public boolean Flowing() {
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
