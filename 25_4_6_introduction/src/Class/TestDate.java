package Class;

public class TestDate {
    public int year;
    public int month;
    public int day;

    // 这里赋值的显式强调要赋值的那个是this的而不是形参
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // 也可以这样直接指明变量是this的，不指明也行，编译器默认添加
    public void printDate() {
        System.out.println(this.year + "-" + this.month + "-" + this.day);
    }
}