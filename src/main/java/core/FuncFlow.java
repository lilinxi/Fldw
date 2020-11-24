package core;

public class FuncFlow implements Flowable {
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
    public boolean Flow() {
        return false;
    }

    @Override
    public String toString() {
        return "FuncFlow{}";
    }
}
