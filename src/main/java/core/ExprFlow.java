package core;

import java.util.LinkedList;

public class ExprFlow extends Flow{
    private LinkedList<ExprData> exprList;
    private Flowable nextFlow;
    private String symbol;

    public ExprFlow(){
        this.exprList = new LinkedList<>();
        this.nextFlow = null;
        this.symbol = null;
    }

    public ExprFlow(String symbol) {
        this();
        this.symbol = symbol;
    }

    @Override
    public String GetSymbol() {
        return this.symbol;
    }

    public boolean Push(ExprData expr) {
        return exprList.add(expr);
    }

    public boolean Push(Flowable flow) {
        ExprData expr;
        while ((expr = (ExprData) flow.Pop()) != null) {
            boolean success = this.Push(expr);
            if (!success) return false;
        }
        return true;
    }

//    public boolean Push(int index, Datable data) {
//        return this.Get(index).Push(data);
//    }


    @Override
    public ExprData PopExpr() {
        if (this.Len() > 0) {
            return exprList.pop();
        }
        return null;
    }

    public int Len() {
        return this.exprList.size();
    }

    public ExprData Get(int index) {
        if (index >= 0 && index < this.exprList.size()) {
            return this.exprList.get(index);
        } else {
            throw new RuntimeException("out of arrange");
        }
    }

    public void SetNext(Flowable flow) {
        this.nextFlow = flow;
    }

    public Flowable Next() {
        return this.nextFlow;
    }

    public boolean HasNext() {
        return this.nextFlow != null;
    }

    public boolean Flowing() {
        if (this.nextFlow == null) {
            return true;
        } else if (this.nextFlow.Len() == 0) {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flowing();
        } else {
            int minSize = Math.min(this.Len(), this.nextFlow.Len());
            for (int i = 0; i < minSize; i++) {
                boolean success = this.nextFlow.Push(i, this.Get(i));
                if (!success) return false;
            }
            return this.nextFlow.Flowing();
        }
    }

    public String toString() {
        return "ExprFlow{" +
                "exprList=" + exprList +
                ", nextFlow=" + nextFlow +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
