package core;

import java.util.ArrayList;

public class BlockFlow extends Flow {
    private Flowable inFlow;
    private Flowable outFlow;
    private ArrayList<Flowable> flowList;

    public BlockFlow() {
        this.inFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.InSymbol).assertGetFlowable();
        this.outFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.OutSymbol).assertGetFlowable();
        this.flowList = new ArrayList<>();
    }

    public boolean addFlow(Flowable flow) throws ExplainException {
        return this.flowList.add(flow);
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
        return this.inFlow.Push(data);
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        return this.inFlow.Push(flow);
    }

    @Override
    public boolean Push(int index, Datable data) throws ExplainException {
        return this.inFlow.Push(index, data);
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.outFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.inFlow.inLen();
    }

    @Override
    public int outLen() throws ExplainException {
        return this.outFlow.outLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.outFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        DelayFlow delayOutFlow = new DelayFlow(this.outFlow);
        delayOutFlow.SetNextFlowing(flow);
        this.addFlow(delayOutFlow);
//        this.outFlow = delayOutFlow;
//        this.outFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.outFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.outFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() throws ExplainException {
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
