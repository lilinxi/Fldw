package core;

import java.util.LinkedList;

public class ListFlow extends Flow {
    private String symbol;
    private LinkedList<Datable> dataList;
    private Flowable nextFlow;

    public ListFlow() {
        this.symbol = null;
        this.dataList = new LinkedList<>();
        this.nextFlow = null;
    }

    public ListFlow(String symbol) {
        this();
        this.symbol = symbol;
    }

    @Override
    public String GetSymbol() {
        return this.symbol;
    }

    @Override
    public boolean Push(Datable data) {
        return dataList.add(data);
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
    public boolean Push(int index, Datable data) {
        return this.Get(index).Push(data);
    }

    @Override
    public Datable Pop() {
        if (this.inLen() > 0) {
            return dataList.pop();
        }
        return null;
    }

    @Override
    public int inLen() {
        return this.dataList.size();
    }

    @Override
    public Datable Get(int index) {
        if (index >= 0 && index < this.dataList.size()) {
            return this.dataList.get(index);
        } else {
            throw new RuntimeException("out of arrange");
        }
    }

    @Override
    public void SetNext(Flowable flow) {
        this.nextFlow = flow;
    }

    @Override
    public Flowable Next() {
        return this.nextFlow;
    }

    @Override
    public boolean HasNext() {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing() {
        if (this.nextFlow == null) {
            return true;
        } else {
            for (int i = 0; i < this.outLen(); i++) {
                boolean success = this.nextFlow.Push(this.Get(i));
                if (!success) return false;
            }
            return this.nextFlow.Flowing();
        }
    }

    @Override
    public boolean Matching() {
        if (this.nextFlow == null) {
            return true;
        } else {
            int minSize = Math.min(this.inLen(), this.nextFlow.inLen());
            for (int i = 0; i < minSize; i++) {
                boolean success = this.nextFlow.Push(i, this.Get(i));
                if (!success) return false;
            }
            return this.nextFlow.Flowing();
        }
    }

    @Override
    public String toString() {
        return "ListFlow{" +
                "symbol='" + symbol + '\'' +
                ", dataList=" + dataList +
                ", nextFlow=" + nextFlow +
                '}';
    }
}
