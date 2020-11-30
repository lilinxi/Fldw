package std;

import core.Datable;
import core.Flow;
import core.Flowable;

public class StdInFlow extends Flow {
    //    单例模式
    private static StdInFlow Instance = new StdInFlow();

    private StdInFlow() {
    }

    public static StdInFlow GetInstance() {
        return StdInFlow.Instance;
    }

    @Override
    public String GetSymbol() {
        return Std.StdInFlowSymbol;
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
}
