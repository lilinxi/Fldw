package core;

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
