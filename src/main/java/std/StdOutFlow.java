package std;

import core.Datable;
import core.Flow;
import core.Flowable;
import core.ListFlow;

public class StdOutFlow extends Flow {
    //    private ListFlow cacheFlow;
    //    单例模式
    private static StdOutFlow Instance = new StdOutFlow();

    private StdOutFlow() {
//        this.cacheFlow = new ListFlow();
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
    public Datable Pop() {
//        if (this.Len() > 0) {
////            TODO：Debug 模式，输出全部信息
//            System.out.println(this.cacheFlow.Pop());
////            System.out.println(this.cacheFlow.Pop().GetValue());
//        }
        return null;
    }

    @Override
    public int Len() {
        return 0;
    }

    @Override
    public void SetNext(Flowable flow) {
        throw new RuntimeException("wrong call");
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
//        while (this.Len() > 0) {
//            this.Pop();
//        }
        return true;
//        throw new RuntimeException("wrong call");
    }
}
