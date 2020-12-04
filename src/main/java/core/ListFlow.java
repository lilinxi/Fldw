package core;

import java.util.LinkedList;

public class ListFlow extends Flow {
    private String symbol;
    private LinkedList<Datable> dataList;
    private Flowable nextFlowing;
    private Flowable nextMatching;
    private boolean matching;

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
    public void SetNextFlowing(Flowable flow) {
        this.nextFlowing = flow;
    }

    @Override
    public Flowable NextFlowing() {
        return this.nextFlowing;
    }

    @Override
    public boolean HasNextFlowing() {
        return this.nextFlowing != null;
    }

    @Override
    public boolean Flowing() {
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
