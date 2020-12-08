package core;

import java.util.LinkedList;

public class ListFlow extends Flow {
    private String symbol;
    private LinkedList<Datable> dataList;
    private Flowable nextFlowing;
    @Deprecated
    private Flowable nextMatching;
    @Deprecated
    private boolean matching = false;
    @Deprecated
    private boolean copyValue = false;
    @Deprecated
    private boolean gotoNext = true;

    public ListFlow() {
        this.symbol = null;
        this.dataList = new LinkedList<>();
        this.nextFlowing = null;
    }

    public ListFlow(String symbol) {
        this();
        this.symbol = symbol;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }

    public void setCopyValue(boolean copyValue) {
        this.copyValue = copyValue;
    }

    @Override
    public String GetSymbol()throws ExplainException {
        return this.symbol;
    }

    @Override
    public boolean Push(Datable data)throws ExplainException {
//        SymbolData tmp = new SymbolData();
//        tmp.Push(data);
//        System.err.println("---");
//        System.err.println(data);
//        System.err.println(tmp);
        dataList.add(data);
//        System.err.println(this);
//        System.err.println("---");
        return true;

    }

    @Override
    public boolean Push(Flowable flow)throws ExplainException {
        Datable data;
        while ((data = flow.Pop()) != null) {
            boolean success = this.Push(data);
            if (!success) return false;
        }
        return true;
    }

    @Override
    public boolean Push(int index, Datable data)throws ExplainException {
        return this.Get(index).Push(data);
    }

    @Override
    public boolean Match(Flowable flow) throws ExplainException{
        int minSize = Math.min(this.inLen(), flow.inLen());
        for (int i = 0; i < minSize; i++) {
            boolean success = flow.Push(i, this.Get(i));
            if (!success) return false;
        }
        return true;
    }

    @Override
    public Datable Pop()throws ExplainException {
        if (this.inLen() > 0) {
            return dataList.pop();
        }
        return null;
    }

    @Override
    public int inLen()throws ExplainException {
        return this.dataList.size();
    }

    @Override
    public Datable Get(int index)throws ExplainException {
        if (index >= 0 && index < this.dataList.size()) {
            if (this.copyValue) {
                SymbolData tmp = new SymbolData();
                tmp.Push(this.dataList.get(index));
                return tmp;
            } else {
                return this.dataList.get(index);
            }
        } else {
            throw new RuntimeException("out of arrange");
        }
    }

    @Override
    public void SetNextFlowing(Flowable flow)throws ExplainException {
        this.nextFlowing = flow;
    }

    @Override
    public Flowable NextFlowing()throws ExplainException {
        return this.nextFlowing;
    }

    @Override
    public boolean HasNextFlowing()throws ExplainException {
        return this.nextFlowing != null;
    }

    @Override
    public boolean Flowing()throws ExplainException {
        if (this.nextFlowing == null) {
            return true;
        }
        switch (this.flowOp) {
            case Pushing -> {
                for (int i = 0; i < this.outLen(); i++) {
                    boolean success = this.nextFlowing.Push(this.Get(i));
                    if (!success) return false;
                }
                if (this.gotoNext) {
                    return this.nextFlowing.Flowing();
                } else {
                    return true;
                }
            }
            case Matching -> {
                int minSize = Math.min(this.inLen(), this.nextFlowing.inLen());
                for (int i = 0; i < minSize; i++) {
                    boolean success = this.nextFlowing.Push(i, this.Get(i));
                    if (!success) return false;
                }
                if (this.gotoNext) {
                    return this.nextFlowing.Flowing();
                } else {
                    return true;
                }
            }
            default -> {
                throw new RuntimeException();
            }
        }
    }

//    @Override
//    public void SetNextMatching(Flowable flow) {
//        this.nextMatching = flow;
//    }
//
//    @Override
//    public Flowable NextMatching() {
//        return this.nextMatching;
//    }
//
//    @Override
//    public boolean HasNextMatching() {
//        return this.nextMatching != null;
//    }

//    @Override
//    public boolean Matching() {
//        if (this.nextMatching == null) {
//            return true;
//        } else {
//            int minSize = Math.min(this.inLen(), this.nextMatching.inLen());
//            for (int i = 0; i < minSize; i++) {
//                boolean success = this.nextMatching.Push(i, this.Get(i));
//                if (!success) return false;
//            }
//            return this.nextMatching.Matching();
//        }
//    }

    @Override
    public String toString() {
        return "ListFlow{" +
                "symbol='" + symbol + '\'' +
                ", dataList=" + dataList +
                ", nextFlow=" + nextFlowing +
                '}';
    }
}
