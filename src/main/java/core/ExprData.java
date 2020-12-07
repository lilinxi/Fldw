package core;

import java.util.Objects;

// 只实现简单的二元运算
public class ExprData extends Data {
    public static enum ExprOp {
        LeftOp,
        RightOp,
        LeftEqualOp,
        RightEqualOp,

        LogicAndOp,
        LogicOrOp,

        LogicEqualOp,
        LogicNotEqualOp,

        AddOp,
        SubOp,
        MulOp,
        DivOp,
        ModOp,
    }

    // 检查两元的类型和运算符是否匹配，并返回表达式的类型
    public static DataType CheckExprTypeMatch(DataType leftType, DataType rightType, ExprOp op) {
        switch (op) {
//                两个类型必为 Int 或 Double，有一方为 Double 则强转为 Double，否则报错
            case AddOp, SubOp, MulOp, DivOp, ModOp -> {
//                两个类型都为 Int
                if (leftType == DataType.Int && rightType == DataType.Int) {
                    return DataType.Int;
                }
//                两个类型不是都为 Int，那么就肯定有一个 Double，否则报错
                if (leftType != DataType.Double && rightType != DataType.Double) {
                    throw new RuntimeException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else { // 类型强转为 Double
                    return DataType.Double;
                }
            }
//                两个类型必为 Int 或 Double，强转为 Double 进行比较，类型返回 Bool，否则报错
            case LeftOp, RightOp, LeftEqualOp, RightEqualOp -> {
                if ((leftType != DataType.Int && leftType != DataType.Double) ||
                        (rightType != DataType.Int && rightType != DataType.Double)) {
                    throw new RuntimeException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else {
                    return DataType.Bool;
                }
            }
            case LogicAndOp, LogicOrOp -> {
                if (leftType == DataType.Bool && rightType == DataType.Bool) {
                    return DataType.Bool;
                } else {
                    throw new RuntimeException("mismatch");
                }
            }
            case LogicEqualOp, LogicNotEqualOp -> {
                return DataType.Bool;
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

    public ExprData() {
    }

    public ExprData(ExprData exprData) {
        this.leftData = exprData.leftData.Clone();
        this.rightData = exprData.rightData.Clone();
        this.op = exprData.op;
        this.type = exprData.type;
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
        throw new RuntimeException("unexpected call");
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
//        SymbolTable sym = SymbolTable.CurrentSymbolTable();
        switch (this.op) {
            //<,>,<=,>=
            case LeftOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) < Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            case RightOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) > Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            case LeftEqualOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) <= Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            case RightEqualOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) >= Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            //
            case LogicOrOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Boolean.parseBoolean(this.leftData.GetValue().toString()) || Boolean.parseBoolean(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            case LogicAndOp -> {
                if (this.GetType() == DataType.Bool) {
                    return Boolean.parseBoolean(this.leftData.GetValue().toString()) && Boolean.parseBoolean(this.rightData.GetValue().toString());
                } else {
                    return "Wrong!";
                }
            }
            case LogicEqualOp -> {
                return this.leftData.equals(this.rightData);
            }
            case LogicNotEqualOp -> {
                return !this.leftData.equals(this.rightData);
            }
            case AddOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) + Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) + Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return false;
                }

            }
            case SubOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) - Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) - Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return false;
                }
            }
            case MulOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) * Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) * Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return false;
                }
            }
            case DivOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) / Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) / Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return false;
                }
            }
            case ModOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) % Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) % Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    return false;
                }
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

    @Override
    public Datable Clone() {
        return new ExprData(this);
    }
}
