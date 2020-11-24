package core;

import java.util.LinkedList;

public class ListFlow implements Flowable {
    private LinkedList<Datable> dataList = new LinkedList<>();
    private Flowable nextFlow = null;

    public ListFlow() {
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
    public boolean Flow() {
        if (this.nextFlow == null) {
            return true;
        } else if (this.nextFlow.Len() == 0) {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flow();
        } else {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flow();
        }
    }

    @Override
    public String toString() {
        return "ListFlow{" +
                "dataList=" + dataList +
                ", nextFlow=" + nextFlow +
                '}';
    }
}
