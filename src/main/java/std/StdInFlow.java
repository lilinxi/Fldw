package std;

import core.*;
import javacc.ParseException;

import java.io.StringReader;
import java.util.Scanner;

/**
 * Stdin 流，标准输入流
 * <p>
 * 标准输入的合法值为一个列表流，例如：
 * <p>
 * [1, 2, 3]
 * 1, 2, 3
 * 1 2 3
 * <p>
 * 其中 [ 和 , 都可以省略
 */
public class StdInFlow extends Flow {
    private ListFlow cacheFlow; // 输入缓冲流

    public StdInFlow() {
        this.cacheFlow = new ListFlow();
    }

    @Override
    public String GetSymbol() throws ExplainException {
        return Std.StdInFlowSymbol;
    }

    @Override
    public Datable Pop() throws ExplainException {
        return this.cacheFlow.Pop();
    }

    @Override
    public int inLen() throws ExplainException {
        return this.cacheFlow.inLen();
    }

    @Override
    public Datable Get(int index) throws ExplainException {
        return this.cacheFlow.Get(index);
    }

    @Override
    public void SetNextFlowing(Flowable flow) throws ExplainException {
        this.cacheFlow.SetNextFlowing(flow);
    }

    @Override
    public Flowable NextFlowing() throws ExplainException {
        return this.cacheFlow.NextFlowing();
    }

    @Override
    public boolean HasNextFlowing() throws ExplainException {
        return this.cacheFlow.HasNextFlowing();
    }

    @Override
    public boolean Flowing() throws ExplainException {
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
