package core;

/**
 * Fldw 核心配置
 */
public class Core {
    /**
     * 默认为立即解释执行
     * <p>
     * 如果为语句块，则需要解析完整个语句块后再解释执行，每次进入语句块调用 AddEager，离开语句块调用 SubEager
     * 对于每个可执行的 Flowing 语句，根据 IsEager 的判定结果决定是否立即解释执行
     */
    private static int EagerCount = 0;

    public static boolean IsEager() {
        return Core.EagerCount == 0;
    }

    public static void AddEager() {
        Core.EagerCount++;
    }

    public static void SubEager() {
        Core.EagerCount--;
    }

    /**
     * 外部扩展包默认的导入接口
     */
    public final static String ModuleLoadFunc = "Load";
}
