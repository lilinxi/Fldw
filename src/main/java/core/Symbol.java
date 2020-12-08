package core;

/**
 * 数据和流的基类，包含指针地址 identity
 */
public class Symbol {
    private int identity;

    public Symbol() {
        this.identity = System.identityHashCode(this);
    }

    public int getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "identity=" + identity +
                '}';
    }
}
