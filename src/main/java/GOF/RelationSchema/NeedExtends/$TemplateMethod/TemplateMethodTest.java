package GOF.RelationSchema.NeedExtends.$TemplateMethod;

/**
 * @Auther: KAM1996
 * @Date: 9:41 2018-11-08
 * @Description: 模板模式测试类
 * @Version: 1.0
 */

/**
 * 首先将exp和"\\+"做参数，调用AbstractCalculator类里的calculate(String,String)方法，
 * 在calculate(String,String)里调用同类的split()，之后再调用calculate(int ,int)方法，
 * 从这个方法进入到子类中，执行完return num1 + num2后，将值返回到AbstractCalculator类，赋给result，打印出来。
 */
public class TemplateMethodTest {

    public static void main(String[] args) {
       //子类加
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp,"\\+");
        System.out.println(result);
        //子类减
        System.out.println(new Minus().calculate("10-3", "-"));
        System.out.println(new Minus().calculate(2, 3));

        //子类乘
        System.out.println(new Muiltply().calculate("4*9", "\\*"));
        //子类除
        System.out.println(new Divide().calculate("16/8", "\\/"));

    }
}
