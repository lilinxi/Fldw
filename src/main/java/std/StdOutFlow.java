package std;

import core.Datable;
import core.ExplainException;
import core.Flow;
import core.Flowable;
import examples.UnitTest;

/**
 * Stdout 流，标准输出流
 */
public class StdOutFlow extends Flow {
    public StdOutFlow() {
    }

    @Override
    public String GetSymbol() {
        return Std.StdOutFlowSymbol;
    }

    @Override
    public boolean Push(Datable data) throws ExplainException {
//        Debug 模式，输出全部信息
        if (UnitTest.UnitTest) {
            System.out.println("stdout: " + data);
        } else {
            System.out.println(data.GetValue());
        }
        return true;
    }

    @Override
    public boolean Push(Flowable flow) throws ExplainException {
        Datable data;
        while ((data = flow.Pop()) != null) {
            boolean success = this.Push(data);
            if (!success) return false;
        }
        return true;
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return false;
    }

    @Override
    public String toString() {
        return "StdOutFlow{}";
    }
}
