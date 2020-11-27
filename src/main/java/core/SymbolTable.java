package core;

import java.util.HashMap;

public class SymbolTable {
    //    所有符号可以代表的类型：数据，流，函数，符号表
    public static enum SymbolType {
        Data, Flow, Function, InnerSymbolTable
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

    //    新增符号时，获取符号的默认值
    public Object NewSymbolValue(String symbol, SymbolType type) {
        switch (type) {
            case Data -> {
                return new SymbolData(symbol);
            }
            case Flow -> {
                return new ListFlow(symbol);
            }
            case Function -> { // 因为函数变量不能赋值，所以创建默认值没有意义
                throw new RuntimeException("wrong type");
            }
            case InnerSymbolTable -> {
                SymbolTable innerSymbolTable = new SymbolTable(symbol, this);
                innerSymbolTable.PutSymbol("in", SymbolType.Flow);
                innerSymbolTable.PutSymbol("out", SymbolType.Flow);
                return innerSymbolTable;
            }
            default -> {
                throw new RuntimeException("wrong call");
            }
        }
    }

    //    递归查找符号表
    public SymbolItem GetSymbol(String symbol) {
        SymbolItem value = this.SymbolItemHashMap.get(symbol);
        if (value != null) {
            return value;
        } else if (this.parentSymbolTable != null) {
            return this.parentSymbolTable.GetSymbol(symbol);
        } else {
            throw new RuntimeException("undefine symbol"); // 未定义符号
        }
    }

    //    新增符号
    public SymbolItem PutSymbol(String symbol, SymbolType type) {
        Object value = this.NewSymbolValue(symbol, type);
        return this.PutSymbol(symbol, type, value);
    }

    //    新增符号
    public SymbolItem PutSymbol(String symbol, SymbolType type, Object value) {
        if (this.SymbolItemHashMap.containsKey(symbol)) {
            throw new RuntimeException("duplicate symbol"); // 符号重复
        } else {
            return this.SymbolItemHashMap.put(symbol, new SymbolItem(symbol, type, value));
        }
    }

    //    遇见一个符号，不确定是已有的还是新增的，若有则返回，没有则新增
    public SymbolItem PutOrGetSymbol(String symbol, SymbolType type) {
        SymbolItem symbolItem = this.GetSymbol(symbol);
        if (symbolItem != null) {
            if (symbolItem.getType() == type) {
                return symbolItem;
            } else {
                throw new RuntimeException("duplicate symbol"); // 符号重复
            }
        } else {
            return this.PutSymbol(symbol, type, this.NewSymbolValue(symbol, type));
        }
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
