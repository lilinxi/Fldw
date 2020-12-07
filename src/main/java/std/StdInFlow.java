package std;

import core.Datable;
import core.Flow;
import core.Flowable;
import core.ListFlow;
import javacc.ParseException;

import java.io.StringReader;
import java.util.Scanner;

public class StdInFlow extends Flow {
    private ListFlow cacheFlow;

    public StdInFlow() {
        this.cacheFlow = new ListFlow();
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
    public int inLen() {
        return this.cacheFlow.inLen();
    }

    @Override
    public Datable Get(int index) {
        return this.cacheFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) {
        this.cacheFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() {
        return this.cacheFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() {
        return this.cacheFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String stdin = sc.nextLine();
            if (!stdin.startsWith("[")) {
                stdin = "[" + stdin + "]";
            }
            try {
                this.cacheFlow.Push(new javacc.FldwCompiler(new StringReader(stdin)).flowing());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        sc.close();
        return this.cacheFlow.Flowing();
    }

    @Override
    public String toString() {
        return "StdInFlow{" +
                "cacheFlow=" + cacheFlow +
                '}';
    }
}
