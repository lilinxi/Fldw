package core;

import java.util.ArrayList;

/**
 * 语句块流：一个语句块作为一整个流操作来进行处理，包含默认的输入流`in`，输出流`out`和中间处理流列表`flowList`
 * <p>
 * 输入操作导入`in`，输出操作由`out`导出，中间操作遍历`flowList`
 */
public class BlockFlow extends Flow {
    private Flowable inFlow;                // 输入流
    private Flowable outFlow;               // 输出流
    private ArrayList<Flowable> flowList;   // 中间处理流

    public BlockFlow() throws ExplainException {
        this.inFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.InSymbol).assertGetFlowable();
        this.outFlow = SymbolTable.CurrentSymbolTable().RecurseGetSymbol(SymbolTable.OutSymbol).assertGetFlowable();
        this.flowList = new ArrayList<>();
    }

    /**
     * 添加一个中间处理流
     *
     * @param flow
     * @return
     * @throws ExplainException
     */
    public boolean addFlow(Flowable flow) throws ExplainException {
        return this.flowList.add(flow);
    }

    @Override
    public String GetSymbol() throws ExplainException {
        StringBuilder builder = new StringBuilder("block");
        for (Flowable flow : this.flowList) {
            builder.append("->");
            builder.append(flow.GetSymbol());
        }
        return builder.toString();
    }

    @Override
    public void SetFlowOp(FlowOp flowOp) throws ExplainException {
        super.SetFlowOp(flowOp);
        this.outFlow.SetFlowOp(flowOp);
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
    public boolean Match(Flowable flow) throws ExplainException {
        return this.outFlow.Match(flow);
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
        // 在中间处理操作中，操作结果不直接输入到下一流，而是使用延迟流，延迟等待所有中间处理操作完成后，再输入到下一流
        DelayFlow delayOutFlow = new DelayFlow(this.outFlow);
        delayOutFlow.SetNextFlowing(flow);
        this.outFlow = delayOutFlow; // 重写 outflow
        this.addFlow(delayOutFlow);
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
        this.outFlow.SetFlowOp(this.flowOp);
        for (Flowable flow : this.flowList) {
            boolean success = flow.Flowing();
            if (!success) throw new ExplainException("Flowing Error: " + flow);
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
