package GOF.RelationSchema.NeedExtends.$Strategy;

/**
 * @Auther: KAM1996
 * @Date: 18:23 2018-11-07
 * @Description: 辅助类
 * @Version: 1.0
 */
public abstract class AbstractCalculator {

    //辅助入参出参
    public int[] split(String exp,String opt){
        String[] array = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
