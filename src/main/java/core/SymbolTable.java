package core;

import java.util.HashMap;

public class SymbolTable {
    public static enum SymbolType {
        Data, Flow, Function
    }

    public static class SymbolItem {
        private String symbol;
        private SymbolType type;
        private Object value;

        public SymbolItem(String symbol, SymbolType type, Object value) {
            this.symbol = symbol;
            this.type = type;
            this.value = value;
        }

        public String getSymbol() {
            return symbol;
        }

        public SymbolType getType() {
            return type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "SymbolItem{" +
                    "symbol='" + symbol + '\'' +
                    ", type=" + type +
                    ", value=" + value +
                    '}';
        }
    }

    private static HashMap<String, SymbolItem> SymbolItemHashMap = new HashMap<String, SymbolItem>();

    public static SymbolItem GetSymbol(String symbol) {
        return SymbolTable.SymbolItemHashMap.get(symbol);
    }

    public static void PutSymbol(String symbol, SymbolType type, Object value) {
        SymbolTable.SymbolItemHashMap.put(symbol, new SymbolItem(symbol, type, value));
    }

}
