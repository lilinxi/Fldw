package core;

public class SymbolDataFlow extends Flow {
    private SymbolData symbolData; // 临时变量
    private Flowable next;



    @Override
    public String GetSymbol() {
        return null;
    }

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public boolean Push(Flowable flow) {
        return false;
    }

    @Override
    public Datable Pop() {
        return null;
    }

    @Override
    public int inLen() {
        return 0;
    }

    @Override
    public void SetNext(Flowable flow) {
        this.next = flow;
    }

    @Override
    public Flowable Next() {
        return null;
    }

    @Override
    public boolean HasNext() {
        return false;
    }

    @Override
    public boolean Flowing() {
        return false;
    }

    @Override
    public String toString() {
        return "SymbolFlow{" +
                "symbolData=" + symbolData +
                ", next=" + next +
                '}';
    }
}