package core;

import java.util.ArrayList;

public class BlockFlow extends Flow {
    private int identity; // System.identityHashCode(this) 返回对象的内存地址，不管该对象的类是否重写了 hashCode() 方法。
    private Flowable inFlow;
    private Flowable outFlow;
    private ArrayList<Flowable> flowList;

    public BlockFlow() {
        this.identity = System.identityHashCode(this);
        this.inFlow = SymbolTable.CurrentSymbolTable().GetSymbol(SymbolTable.InSymbol).assertGetFlowable();
        this.outFlow = SymbolTable.CurrentSymbolTable().GetSymbol(SymbolTable.OutSymbol).assertGetFlowable();
        this.flowList = new ArrayList<>();
    }

    public boolean addFlow(Flowable flow) {
        return this.flowList.add(flow);
    }

    @Override
    public boolean Push(Datable data) {
        return this.inFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) {
        return this.inFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) {
        return this.inFlow.Push(index, data);
    }

    @Override
    public Datable Pop() {
        return this.outFlow.Pop();
    }

    @Override
    public int inLen() {
        return this.inFlow.inLen();
    }

    @Override
    public int outLen() {
        return this.outFlow.outLen();
    }

    @Override
    public Datable Get(int index) {
        return this.outFlow.Get(index);
    }

    @Override
    public void SetNext(Flowable flow) {
        this.outFlow.SetNext(flow);
    }

    @Override
    public Flowable Next() {
        return this.outFlow.Next();
    }

    @Override
    public boolean HasNext() {
        return this.outFlow.HasNext();
    }

    @Override
    public boolean Flowing() {
        for (Flowable flow : this.flowList) {
            boolean success = flow.Flowing();
            if (!success) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlockFlow{" +
                ", inFlow=" + inFlow +
                ", outFlow=" + outFlow +
                ", flowList=" + flowList +
                '}';
    }
}
