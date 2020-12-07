package std;

import core.Datable;
import core.Flow;
import core.Flowable;
import examples.UnitTest;

public class StdOutFlow extends Flow {
    public StdOutFlow() {
    }

    @Override
    public String GetSymbol() {
        return Std.StdOutFlowSymbol;
    }

    @Override
    public boolean Push(Datable data) {
//        Debug 模式，输出全部信息
        if (UnitTest.UnitTest) {
            System.out.println("stdout: " + data);
        } else {
            System.out.println(data.GetValue());
        }
        return true;
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
    public boolean HasNextFlowing() {
        return false;
    }

    @Override
    public String toString() {
        return "StdOutFlow{}";
    }
}
