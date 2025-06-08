package demo2;

public class Money implements Cloneable {
    public double money = 15.6;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
