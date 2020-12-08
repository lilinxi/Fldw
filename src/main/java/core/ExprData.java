package core;

/**
 * 表达式数据
 * <p>
 * 支持逻辑运算和算数运算
 * 解析语法树只建立表达式树，在获取值时进行类型判断并且懒求值
 */
public class ExprData extends Data {
    public enum ExprOp {
        LeftOp,             // 小于
        RightOp,            // 大于
        LeftEqualOp,        // 小于等于
        RightEqualOp,       // 大于等于

        LogicAndOp,         // 逻辑与
        LogicOrOp,          // 逻辑或

        LogicEqualOp,       // 逻辑等于
        LogicNotEqualOp,    // 逻辑不等于

        AddOp,              // 加
        SubOp,              // 减
        MulOp,              // 乘
        DivOp,              // 除
        ModOp               // 取模
    }

    // 检查两元的类型和运算符是否匹配，并返回表达式的类型
    public static DataType CheckExprTypeMatch(DataType leftType, DataType rightType, ExprOp op) throws ExplainException {
        switch (op) {
//                两个类型必为 Int 或 Double，有一方为 Double 则强转为 Double，否则报错
            case AddOp, SubOp, MulOp, DivOp, ModOp -> {
//                两个类型都为 Int
                if (leftType == DataType.Int && rightType == DataType.Int) {
                    return DataType.Int;
                }
//                两个类型不是都为 Int，那么就肯定有一个 Double，否则报错
                if (leftType != DataType.Double && rightType != DataType.Double) {
                    throw new ExplainException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else { // 类型强转为 Double
                    return DataType.Double;
                }
            }
//                两个类型必为 Int 或 Double，强转为 Double 进行比较，类型返回 Bool，否则报错
            case LeftOp, RightOp, LeftEqualOp, RightEqualOp -> {
                if ((leftType != DataType.Int && leftType != DataType.Double) ||
                        (rightType != DataType.Int && rightType != DataType.Double)) {
                    throw new ExplainException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                } else {
                    return DataType.Bool;
                }
            }
//            逻辑运算的两个类型必须都为真假值
            case LogicAndOp, LogicOrOp -> {
                if (leftType == DataType.Bool && rightType == DataType.Bool) {
                    return DataType.Bool;
                } else {
                    throw new ExplainException("expr type mismatch with " + leftType + " " + op + " " + rightType);
                }
            }
//            等于和不等于对两个类型不做限制
            case LogicEqualOp, LogicNotEqualOp -> {
                return DataType.Bool;
            }
            default -> {
                throw new ExplainException("unexpected op " + op);
            }
        }
    }

    private Datable leftData;       // 运算左值
    private Datable rightData;      // 运算右值
    private ExprOp op;              // 运算符
    private DataType type;          // 表达式的类型

    public ExprData() {
    }

    /**
     * 深拷贝
     *
     * @param exprData
     * @throws ExplainException
     */
    public ExprData(ExprData exprData) throws ExplainException {
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
    public DataType GetType() throws ExplainException {
        if (this.type == null) {
            this.type = ExprData.CheckExprTypeMatch(this.leftData.GetType(), this.rightData.GetType(), this.op);
        }
        return this.type;
    }

    @Override
    public Object GetValue() throws ExplainException {
        if (this.type == null) { // 任何不提前获取类型的获取值操作都是耍流氓
            throw new ExplainException("GetValue before GetType is not allowed");
        }
        switch (this.op) {
            // <, >, <=, >=
            case LeftOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) < Double.parseDouble(this.rightData.GetValue().toString());
            }
            case RightOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) > Double.parseDouble(this.rightData.GetValue().toString());
            }
            case LeftEqualOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) <= Double.parseDouble(this.rightData.GetValue().toString());
            }
            case RightEqualOp -> {
                return Double.parseDouble(this.leftData.GetValue().toString()) >= Double.parseDouble(this.rightData.GetValue().toString());
            }
            // ||, &&, ==, !=
            case LogicAndOp -> {
                return Boolean.parseBoolean(this.leftData.GetValue().toString()) && Boolean.parseBoolean(this.rightData.GetValue().toString());
            }
            case LogicOrOp -> {
                return Boolean.parseBoolean(this.leftData.GetValue().toString()) || Boolean.parseBoolean(this.rightData.GetValue().toString());
            }
            case LogicEqualOp -> {
                return this.leftData.equals(this.rightData);
            }
            case LogicNotEqualOp -> {
                return !this.leftData.equals(this.rightData);
            }
            // +, -, *, /, %
            case AddOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) + Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) + Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    throw new ExplainException("unexpected call with type " + this.GetValue() + " and op " + this.op);
                }
            }
            case SubOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) - Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) - Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    throw new ExplainException("unexpected call with type " + this.GetValue() + " and op " + this.op);
                }
            }
            case MulOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) * Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) * Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    throw new ExplainException("unexpected call with type " + this.GetValue() + " and op " + this.op);
                }
            }
            case DivOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) / Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) / Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    throw new ExplainException("unexpected call with type " + this.GetValue() + " and op " + this.op);
                }
            }
            case ModOp -> {
                if (this.GetType() == DataType.Int) {
                    return Integer.parseInt(this.leftData.GetValue().toString()) % Integer.parseInt(this.rightData.GetValue().toString());
                } else if (this.GetType() == DataType.Double) {
                    return Double.parseDouble(this.leftData.GetValue().toString()) % Double.parseDouble(this.rightData.GetValue().toString());
                } else {
                    throw new ExplainException("unexpected call with type " + this.GetValue() + " and op " + this.op);
                }
            }
            default -> {
                throw new ExplainException("unexpected op " + op);
            }
        }
    }

    @Override
    public String GetSymbol() throws ExplainException {
        StringBuilder builder = new StringBuilder();
        builder.append(this.leftData.GetSymbol());
        builder.append(this.op);
        builder.append(this.rightData.GetSymbol());
        return builder.toString();
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
    public Datable Clone() throws ExplainException {
        return new ExprData(this);
    }
}
