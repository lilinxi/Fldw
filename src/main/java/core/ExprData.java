package core;

// 只实现简单的二元运算
public class ExprData extends Data {
    public static enum ExprOp {
        AddOp,
        SubOp,
        MulOp,
        DivOp,
        LeftOp,
        RightOp,
        LogicEqualOp,
    }

    // 检查两元的类型和运算符是否匹配，并返回表达式的类型
    public static DataType CheckExprTypeMatch(DataType leftType, DataType rightType, ExprOp op) {
        switch (op) {
//                两个类型必为 Int 或 Double，有一方为 Double 则强转为 Double，否则报错
            case AddOp, SubOp, MulOp, DivOp -> {
//                两个类型都为 Int
                if (leftType == DataType.Int && rightType == DataType.Int) {
                    return DataType.Int;
                }
//                两个类型不是都为 Int，那么就肯定有一个 Double，否则报错
                if (leftType != DataType.Double || rightType != DataType.Double) {
                    throw new RuntimeException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else { // 类型强转为 Double
                    return DataType.Double;
                }
            }
//                两个类型必为 Int 或 Double，强转为 Double 进行比较，类型返回 Bool，否则报错
            case LeftOp, RightOp -> {
                if ((leftType != DataType.Int && leftType != DataType.Double) ||
                        (rightType != DataType.Int && rightType != DataType.Double)) {
                    throw new RuntimeException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else {
                    return DataType.Bool;
                }
            }
//            需要类型强匹配，否则报错
            case LogicEqualOp -> {
                if (leftType == rightType) {
                    return leftType;
                } else {
                    throw new RuntimeException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                }
            }
            default -> {
                throw new RuntimeException("unexpected op");
            }
        }
    }

    private Datable leftData;
    private Datable rightData;
    private ExprOp op;
    private DataType type;
    private Datable cal;

    public ExprData() {
    }

    public void setLeftData(Datable leftData) {
        this.leftData = leftData;
    }

    public void setRightData(Datable rightData) {
        this.rightData = rightData;
    }

    public void setOp(ExprOp op) {
        this.op = op;
    }

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public DataType GetType() {
        if (this.type == null) {
            this.type = ExprData.CheckExprTypeMatch(this.leftData.GetType(), this.rightData.GetType(), this.op);
        }
        return this.type;
    }

    @Override
    public Object GetValue() {
        if (this.type == null) { // 任何不提前获取类型的获取值操作都是耍流氓
            throw new RuntimeException("unexpected call");
        }
        switch (this.op) {
            case LeftOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) < Double.parseDouble(this.rightData.GetValue().toString());
            }
            case RightOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) > Double.parseDouble(this.rightData.GetValue().toString());
            }
            default -> {
                throw new RuntimeException("TODO");
            }
        }
    }

    @Override
    public String GetSymbol() {
        throw new RuntimeException("unexpected call");
    }

    @Override
    public boolean SetType(DataType type) {
        throw new RuntimeException("unexpected call");
    }

    @Override
    public boolean SetValue(Object value) {
        throw new RuntimeException("unexpected call");
    }

    @Override
    public String toString() {
        return "ExprData{" +
                "leftData=" + leftData +
                ", rightData=" + rightData +
                ", op=" + op +
                ", type=" + type +
                '}';
    }
}
