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
    public void SetNext(Flowable flow) {
        this.cacheFlow.SetNext(flow);
    }

    @Override
    public Flowable Next() {
        return this.cacheFlow.Next();
    }

    @Override
    public boolean HasNext() {
        return this.cacheFlow.HasNext();
    }

    @Override
    public boolean Flowing() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String stdin = sc.nextLine();
            if (!stdin.startsWith("[")) {
                stdin = "[" + stdin + "]";
            }
//            System.out.println(stdin);
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
