package GOF.RelationSchema.NeedExtends.TemplateMethodTest;

/**
 * @Auther: KAM1996
 * @Date: 9:31 2018-11-08
 * @Description: 核心抽象类，模板类
 * @Version: 1.0
 */

/*
一个抽象类中，有一个主方法，再定义1-n个方法，可以使抽象的也可以是实际的方法，
定义一个类，继承该抽象类，重写抽象方法，通过调用抽象类，实现对子类的调用
 */
public abstract class AbstractCalculator {

    //主方法，实现对本类的其他方法的调用
    public final int calculate(String exp, String opt) {
        int[] array = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    //被子类重写的方法，子类给出实际的运算实现
    abstract public int calculate(int num1, int num2);

    //切割，服务于主方法，抽象出需要切割并且在哪里切割的规则
    public int[] split(String exp, String opt) {
        String[] array = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
