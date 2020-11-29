package core;

// Flowable 默认实现类
public class Flow implements Flowable {
    @Override
    public String GetSymbol() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean Push(Datable data) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean Push(Flowable flow) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean Push(int index, Datable data) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public Datable Pop() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public int Len() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public Datable Get(int index) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public void SetNext(Flowable flow) {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public Flowable Next() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean HasNext() {
        throw new RuntimeException("no impl, wrong call");
    }

    @Override
    public boolean Flowing() {
        throw new RuntimeException("no impl, wrong call");
    }
}
