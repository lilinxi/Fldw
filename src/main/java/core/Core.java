package core;

public class Core {
    private static boolean Eager = true;

    public static boolean isEager() {
        return Core.Eager;
    }

    public static void setEager(boolean eager) {
        Core.Eager = eager;
    }

    public final static String ModuleLoadFunc = "Load";

}
