package core;

import java.util.HashMap;

public class SymbolTable {
    //    所有符号可以代表的类型：数据，流，函数，符号表
    public static enum SymbolType {
        Data, Flow, Function, SymbolTable
    }

    //    符号表项
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

    private String symbol;
    private SymbolTable parentSymbolTable;
    private HashMap<String, SymbolItem> SymbolItemHashMap;

    public SymbolTable(String symbol, SymbolTable parentSymbolTable) {
        this.symbol = symbol;
        this.parentSymbolTable = parentSymbolTable;
        this.SymbolItemHashMap = new HashMap<>();
    }

    //    递归查找符号表
    public SymbolItem GetSymbol(String symbol) {
        SymbolItem value = this.SymbolItemHashMap.get(symbol);
        if (value != null) {
            return value;
        } else if (this.parentSymbolTable != null) {
            return this.parentSymbolTable.GetSymbol(symbol);
        } else {
            return null;
        }
    }

    public SymbolItem PutSymbol(String symbol, SymbolType type, Object value) {
        return this.SymbolItemHashMap.put(symbol, new SymbolItem(symbol, type, value));
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "symbol='" + symbol + '\'' +
                ", parentSymbolTable=" + parentSymbolTable +
                ", SymbolItemHashMap=" + SymbolItemHashMap +
                '}';
    }

    //    根符号表
    public static SymbolTable RootSymbolTable = new SymbolTable("root", null);
}
