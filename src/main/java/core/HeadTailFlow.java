package core;


public class HeadTailFlow implements Flowable {
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
    public int Len() {
        return 0;
    }

    @Override
    public void SetNext(Flowable flow) {

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
        return "HeadTailFlow{}";
    }
}
