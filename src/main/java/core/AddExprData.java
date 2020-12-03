package core;

public class AddExprData extends Data {
    private Datable leftChild, rightChild;

    @Override
    public boolean Push(Datable data) {
        return false;
    }

    @Override
    public DataType GetType() {
        if (leftChild.GetType() != rightChild.GetType()) {
            throw new RuntimeException();
        } else {
            return leftChild.GetType();
        }
    }

    @Override
    public Object GetValue() {
        if (leftChild.GetType() != rightChild.GetType()) {
            throw new RuntimeException();
        } else {
            switch (leftChild.GetType()) {
                case Int -> {
                    return Integer.parseInt(leftChild.GetValue().toString()) + Integer.parseInt(rightChild.GetValue().toString());
                }
                case String -> {
                    throw new RuntimeException();
                }
            }
        }
        return null;
    }

    @Override
    public String GetSymbol() {
        throw new RuntimeException();
    }

    @Override
    public boolean SetType(DataType type) {
        throw new RuntimeException();
    }

    @Override
    public boolean SetValue(Object value) {
        throw new RuntimeException();
    }
}
