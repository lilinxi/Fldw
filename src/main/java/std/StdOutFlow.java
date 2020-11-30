package std;

import core.Datable;
import core.Flow;
import core.Flowable;

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
        System.out.println("stdout: " + data);
//            System.out.println(this.cacheFlow.Pop().GetValue());
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
