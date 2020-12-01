package core;

public class Core {
    private static boolean EagerFlowing = true;

    public static boolean isEagerFlowing() {
        return Core.EagerFlowing;
    }

    public static void setEagerFlowing(boolean eagerFlowing) {
//        System.out.println("set eager flow: " + eagerFlowing);
        Core.EagerFlowing = eagerFlowing;
    }
}
