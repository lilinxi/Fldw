package core;

import java.util.LinkedList;

public class ListFlow implements Flowable {
    private LinkedList<Datable> dataList;
    private Flowable nextFlow;
    private String symbol;

    public ListFlow() {
        this.dataList = new LinkedList<>();
        this.nextFlow = null;
        this.symbol = null;
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
    public Datable Pop() {
        if (this.Len() > 0) {
            return dataList.pop();
        }
        return null;
    }

    @Override
    public int Len() {
        return this.dataList.size();
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
        } else if (this.nextFlow.Len() == 0) {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flowing();
        } else {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flowing();
        }
    }

    @Override
    public String toString() {
        return "ListFlow{" +
                "dataList=" + dataList +
                ", nextFlow=" + nextFlow +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
