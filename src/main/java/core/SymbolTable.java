package core;

import javacc.ParseException;

import java.util.*;

/**
 * 符号表
 */
public class SymbolTable {
    //    所有符号可以代表的类型：数据，流，函数，符号表
    public static enum SymbolType {
        Data,                   // 符号-数据
        Flow,                   // 符号-流
        Function,               // 符号-函数
        BlockSymbolTable        // 符号-Block 符号表
    }

    private static int TmpSymbolIndex = 0;

    //    生成一个临时的符号，用于表示临时变量的符号
    //    目前仅用于表示 Block 符号表的符号
    private static String getTmpSymbol() { // 临时变量有序，不采用 UUID，便于测试
//        UUID uuid = UUID.randomUUID();
//        return uuid.toString();
        return "_tmp" + TmpSymbolIndex++;
    }

    //    符号表项
    public static class SymbolItem {
        private String symbol;      // 符号
        private SymbolType type;    // 符号种类
        private Object value;       // 符号值

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

        public SymbolData assertGetSymbolData() throws ExplainException {
            if (this.getType() != SymbolType.Data) {
                throw new ExplainException("assert type mismatch");
            }
            return (SymbolData) this.getValue();
        }

        public DelayFuncFlow assertGetDelayFuncFlow() throws ExplainException {
            if (this.getType() != SymbolType.Function) {
                throw new ExplainException("assert type mismatch");
            }
            return new DelayFuncFlow(this.getSymbol(), this.getValue().toString());
        }

        public Flowable assertGetFlowable() throws ExplainException {
            if (this.getType() != SymbolType.Flow) {
                throw new ExplainException("assert type mismatch");
            }
            return (Flowable) this.getValue();
        }

        public SymbolTable assertGetSymbolTable() throws ExplainException {
            if (this.getType() != SymbolType.BlockSymbolTable) {
                throw new ExplainException("assert type mismatch");
            }
            return (SymbolTable) this.getValue();
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

    public final static String RootSymbol = "root"; // 根符号表
    public final static String InSymbol = "in";     // in 流符号
    public final static String OutSymbol = "out";   // out 流的符号

    private String symbol;                                  // 符号表的符号
    private SymbolTable parentSymbolTable;                  // 父符号表
    private TreeMap<String, SymbolItem> SymbolItemTreeMap;  // TreeMap 有序，方便对符号表进行测试

    //    私有化构造函数，符号表只能通过两种方式构建：（1）根符号表；（2）Blocks 内符号表
    private SymbolTable(String symbol, SymbolTable parentSymbolTable) throws ExplainException {
        this.symbol = symbol;
        this.parentSymbolTable = parentSymbolTable;
        this.SymbolItemTreeMap = new TreeMap<>();

        //        每个符号表都内嵌了 in 和 out 两个符号流，表示都可以作为一个流调用，但是只有函数 Block 包含显示的名称，可以直接被用户调用
        //        可以进行多个程序的串联
        this.PutSymbol(SymbolTable.InSymbol, SymbolType.Flow);
        this.PutSymbol(SymbolTable.OutSymbol, SymbolType.Flow);
    }

    public void setParentSymbolTable(SymbolTable parentSymbolTable) {
        this.parentSymbolTable = parentSymbolTable;
    }

    //    新增符号时，获取符号的默认值
    private Object NewSymbolValue(String symbol, SymbolType type) throws ExplainException {
        switch (type) {
            case Data -> {
                return new SymbolData(symbol);
            }
            case Flow -> {
                return new ListFlow(symbol);
            }
            case Function -> { // 因为函数变量不能赋值，所以创建默认值没有意义
                throw new ExplainException("wrong type");
            }
            case BlockSymbolTable -> {
                return new SymbolTable(symbol, this);
            }
            default -> {
                throw new ExplainException("wrong call");
            }
        }
    }

    //    递归查找符号表，默认不对流进行延迟拷贝
    public SymbolItem RecurseGetSymbol(String symbol) throws ExplainException {
        return this.RecurseGetSymbol(symbol, false); // 默认为 false
    }

    //    递归查找符号表
    public SymbolItem RecurseGetSymbol(String symbol, boolean delayCopy) throws ExplainException {
        SymbolItem value = this.GetSymbol(symbol, delayCopy);
        if (value != null) {
            return value;
        } else if (this.parentSymbolTable != null) {
            return this.parentSymbolTable.RecurseGetSymbol(symbol, delayCopy);
        } else {
            return null; // 未定义符号不报错，只有使用未定义符号的时候会报错
        }
    }

    //    不递归，只查找当前符号表
    public SymbolItem GetSymbol(String symbol, boolean delayCopy) throws ExplainException {
        SymbolItem symbolItem = this.SymbolItemTreeMap.get(symbol);
        if (delayCopy && symbolItem != null && symbolItem.getType() == SymbolType.Flow) {
            return new SymbolItem(symbolItem.getSymbol(), symbolItem.getType(), new DelayFlow(symbolItem.assertGetFlowable()));
        }
        return symbolItem;
    }

    //    新增符号，新增默认的空符号，返回新增的符号项
    public SymbolItem PutSymbol(String symbol, SymbolType type) throws ExplainException {
        Object value = this.NewSymbolValue(symbol, type);
        return this.PutSymbol(symbol, type, value);
    }

    //    新增符号，返回新增的符号项
    public SymbolItem PutSymbol(String symbol, SymbolType type, Object value) throws ExplainException {
        if (this.SymbolItemTreeMap.containsKey(symbol)) {
            throw new ExplainException("duplicate symbol"); // 符号重复
        } else {
//            java TreeMap put 的返回值并不是插入的元素，而是 null 或者被重复 key 覆盖的元素
            SymbolItem item = new SymbolItem(symbol, type, value);
            this.SymbolItemTreeMap.put(symbol, item);
            return item;
        }
    }

    //    遇见一个符号，不确定是已有的还是新增的，若有则返回，没有则新增
    public SymbolItem PutOrRecurseGetSymbol(String symbol, SymbolType type) throws ExplainException {
        SymbolItem symbolItem = this.RecurseGetSymbol(symbol, true); // 默认为 true
        if (symbolItem != null) {
            if (symbolItem.getType() == type) {
                return symbolItem;
            } else {
                throw new ExplainException("duplicate symbol:" + symbol + ", " + symbolItem.getType() + ", " + type); // 符号重复
            }
        } else {
            return this.PutSymbol(symbol, type);
        }
    }

    //    遇见一个符号，不确定是已有的还是新增的，若有则返回，没有则新增
    public SymbolItem PutOrGetSymbol(String symbol, SymbolType type) throws ExplainException {
        SymbolItem symbolItem = this.GetSymbol(symbol, true); // 默认为 true

        if (symbolItem != null) {
            if (symbolItem.getType() == type) {
                return symbolItem;
            } else {
                throw new ExplainException("duplicate symbol:" + symbol + ", " + symbolItem.getType() + ", " + type); // 符号重复
            }
        } else {
            return this.PutSymbol(symbol, type);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SymbolTable{\n" +
                "symbol='" + symbol + '\'' +
                ", parentSymbolTable=" + (parentSymbolTable == null ? "" : parentSymbolTable.symbol) + // 打印整个 parentSymbolTable 会导致递归栈溢出
                ", SymbolItemTreeMap=\n");
        for (Map.Entry<String, SymbolItem> entry : this.SymbolItemTreeMap.entrySet()) {
            if (this.parentSymbolTable != null) {
                stringBuilder.append("|-- ");
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\n");
        }
        stringBuilder.append('}');
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    //    根符号表
    private static SymbolTable RootSymbolTable;

    //    创建根符号表
    static {
        try {
            RootSymbolTable = new SymbolTable(SymbolTable.RootSymbol, null);
        } catch (ExplainException e) {
            throw new RuntimeException("Root Symbol Table Error!");
        }
    }

    //    符号表栈
    private static final Stack<SymbolTable> SymbolTableStack = new Stack<>();

    //    清空符号表
    public static void Clear() throws ExplainException {
        SymbolTable.RootSymbolTable.SymbolItemTreeMap.clear();
        SymbolTable.SymbolTableStack.clear();
        //        符号表栈从根符号表开始
        SymbolTable.SymbolTableStack.push(SymbolTable.RootSymbolTable);
        SymbolTable.RootSymbolTable.PutSymbol(SymbolTable.InSymbol, SymbolType.Flow);
        SymbolTable.RootSymbolTable.PutSymbol(SymbolTable.OutSymbol, SymbolType.Flow);
        SymbolTable.TmpSymbolIndex = 0;
    }

    static {
        try {
            Clear();
        } catch (ExplainException e) {
            e.printStackTrace();
        }
    }

    //    获取当前符号表，即栈顶符号表
    public static SymbolTable CurrentSymbolTable() {
        return SymbolTable.SymbolTableStack.peek();
    }

    //    创建新符号表，符号表栈压入一位
    public static SymbolTable EnterBlock(String name) throws ExplainException {
        //        Block 表示为匿名的函数
        if (name == null) {
            name = SymbolTable.getTmpSymbol();
        } else {
            name += SymbolTable.getTmpSymbol();
        }
        SymbolTable.SymbolTableStack.push(
                SymbolTable.CurrentSymbolTable().PutOrRecurseGetSymbol(name, SymbolType.BlockSymbolTable)
                        .assertGetSymbolTable());
        return SymbolTable.CurrentSymbolTable();
    }

    //    符号表栈弹出一位
    public static SymbolTable ExitBlock() {
        return SymbolTable.SymbolTableStack.pop();
    }
}