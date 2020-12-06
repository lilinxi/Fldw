package core;

public class Core {
    private static int EagerCount = 0; // 默认为立即执行

    public static boolean IsEager() {
        return Core.EagerCount == 0;
    }

    public static void AddEager() {
        Core.EagerCount++;
    }

    public static void SubEager() {
        Core.EagerCount--;
    }

    public final static String ModuleLoadFunc = "Load";

}
