package Class;

public class WashMachine {
    public String brand;  // 品牌
    public String type;   // 型号
    public double weight; // 重量
    public double lenght; // 长
    public double weidth; // 宽
    public double height; // 高
    public String color;  // 颜色

    public void WashClothes(){
        System.out.println("洗衣功能");
    }

    public void dryClothes(){
        System.out.println("脱水功能");
    }

    public void SetTime(){
        System.out.println("定时功能");
    }
}
