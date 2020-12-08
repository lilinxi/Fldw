package core;

import java.util.LinkedList;

/**
 * 列表流，包含一个数据的列表
 */
public class ListFlow extends Flow {
    private String symbol;                  // 列表符号
    private LinkedList<Datable> dataList;   // 数据列表
    private Flowable nextFlowing;           // 下一流

    public ListFlow() {
        this.symbol = null;
        this.dataList = new LinkedList<>();
        this.nextFlowing = null;
    }

    public ListFlow(String symbol) {
        this();
        this.symbol = symbol;
    }

    @Override
    public String GetSymbol() throws ExplainException {
        StringBuilder builder = new StringBuilder(this.symbol);
        for (Datable data : this.dataList) {
            builder.append("_");
            builder.append(data.GetSymbol());
        }
        return builder.toString();
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return dataList.add(data);

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
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.Get(index).Push(data);
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException {
        int minSize = Math.min(this.inLen(), flow.inLen());
        for (int i = 0; i < minSize; i++) {
            boolean success = flow.Push(i, this.Get(i));
            if (!success) return false;
        }
        return true;
    }

    @Override
    public Datable Pop() throws ExplainException {
        if (this.inLen() > 0) {
            return dataList.pop();
        }
        return null;
    }

    @Override
    public int inLen() throws ExplainException {
        return this.dataList.size();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        if (index >= 0 && index < this.dataList.size()) {
            if (this.copyValue) {
                SymbolData tmp = new SymbolData();
                tmp.Push(this.dataList.get(index));
                return tmp;
            } else {
                return this.dataList.get(index);
            }
        } else {
            throw new ExplainException("out of arrange");
        }
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        this.nextFlowing = flow;
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.nextFlowing;
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.nextFlowing != null;
    }

    @Override
    public boolean Flowing() throws ExplainException {
        if (this.nextFlowing == null) {
            return true;
        }
        switch (this.flowOp) {
            case Pushing -> {
                for (int i = 0; i < this.outLen(); i++) {
                    boolean success = this.nextFlowing.Push(this.Get(i));
                    if (!success) return false;
                }
                return this.nextFlowing.Flowing();
            }
            case Matching -> {
                int minSize = Math.min(this.inLen(), this.nextFlowing.inLen());
                for (int i = 0; i < minSize; i++) {
                    boolean success = this.nextFlowing.Push(i, this.Get(i));
                    if (!success) return false;
                }
                return this.nextFlowing.Flowing();
            }
            default -> {
                throw new ExplainException("unexpected flowOp: " + this.flowOp);
            }
        }
    }

    @Override
    public String toString() {
        return "ListFlow{" +
                "symbol='" + symbol + '\'' +
                ", dataList=" + dataList +
                ", nextFlow=" + nextFlowing +
                '}';
    }
}
