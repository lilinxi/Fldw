package std;

import core.Datable;
import core.Flow;
import core.Flowable;
import core.UnitTest;

public class StdOutFlow extends Flow {
    //    单例模式
    private static StdOutFlow Instance = new StdOutFlow();

    private StdOutFlow() {
    }

    public static StdOutFlow GetInstance() {
        return StdOutFlow.Instance;
    }

    @Override
    public String GetSymbol() {
        return Std.StdOutFlowSymbol;
    }

    @Override
    public boolean Push(Datable data) {
//        TODO：Debug 模式，输出全部信息
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
    public boolean HasNext() {
        return false;
    }

    @Override
    public String toString() {
        return "StdOutFlow{}";
    }
}
