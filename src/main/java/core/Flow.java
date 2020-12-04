package core;

// Flowable 默认实现类
public class Flow extends TokenList implements Flowable {
//    @Override
//    public int GetIdentity() {
//        return System.identityHashCode(this);
//    }

    @Override
    public String GetSymbol() {
        return null;
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
    public int inLen() {
        return 0;
    }

    @Override
    public int outLen() { // 默认输出长度等于输入长度
        return this.inLen();
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
        return false;
    }

    @Override
    public boolean Flowing() {
        return true;
    }

    @Override
    public boolean Matching() {
        return true;
    }
}
