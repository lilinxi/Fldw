package core;

import java.util.ArrayList;

public class BlockFlow extends Flow {
    private int identity; // System.identityHashCode(this) 返回对象的内存地址，不管该对象的类是否重写了 hashCode() 方法。
    private Flowable inFlow;
    private Flowable outFlow;
    private ArrayList<Flowable> flowList;

    public BlockFlow() {
        this.identity = System.identityHashCode(this);
        this.inFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.InSymbol).assertGetFlowable();
        this.outFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.OutSymbol).assertGetFlowable();
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
    public void SetNextFlowing(Flowable flow) {
        this.outFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() {
        return this.outFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() {
        return this.outFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() {
//        System.out.println(this.flowList);
        for (Flowable flow : this.flowList) {
//            System.out.println("flow: " + flow);
            boolean success = flow.Flowing();
//            System.out.println("end flow: " + flow.GetSymbol());
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
