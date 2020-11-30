package std;

import core.Datable;
import core.Flow;
import core.Flowable;
import core.ListFlow;
import javacc.ParseException;

import java.io.StringReader;
import java.util.Scanner;

public class StdInFlow extends Flow {
    //    单例模式
    private static StdInFlow Instance = new StdInFlow();

    private Flowable nextFlow;
    private ListFlow cacheFlow;

    private StdInFlow() {
        this.nextFlow = null;
        this.cacheFlow = new ListFlow();
    }

    public static StdInFlow GetInstance() {
        return StdInFlow.Instance;
    }

    @Override
    public String GetSymbol() {
        return Std.StdInFlowSymbol;
    }

    @Override
    public Datable Pop() {
        return this.cacheFlow.Pop();
    }

    @Override
    public int Len() {
        return this.cacheFlow.Len();
    }

    @Override
    public Datable Get(int index) {
        return this.cacheFlow.Get(index)
    }

    @Override
    public void SetNext(Flowable flow) {
        this.nextFlow = flow;
    }

    @Override
    public Flowable Next() {
        return this.nextFlow;
    }

    @Override
    public boolean HasNext() {
        return this.nextFlow != null;
    }

    @Override
    public boolean Flowing() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            try {
                this.cacheFlow.Push(new javacc.FldwCompiler(new StringReader(sc.nextLine())).flowing());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("输入的数据为：" + str2);
        }
        sc.close();
        throw new RuntimeException("close");
        if (this.nextFlow == null) {
            return true;
        } else if (this.nextFlow.Len() == 0) {
            boolean success = this.nextFlow.Push(this);
            if (!success) return false;
            return this.nextFlow.Flowing();
        } else {
            int minSize = Math.min(this.Len(), this.nextFlow.Len());
            for (int i = 0; i < minSize; i++) {
                boolean success = this.nextFlow.Push(i, this.Get(i));
                if (!success) return false;
            }
            return this.nextFlow.Flowing();
        }
    }
}
