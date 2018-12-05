package GOF.TypeStatusSchema.TypeVSType.$State;

/**
 * @Auther: KAM1996
 * @Date: 13:51 2018-11-08
 * @Description: 状态模式测试类
 * @Version: 1.0
 */
/**
 * 状态模式核心思想:
 * 当对象的状态改变时，同时改变其行为，很好理解！就拿QQ来说，有几种状态，在线、隐身、忙碌等，
 * 每个状态对应不同的操作，而且你的好友也能看到你的状态，所以，状态模式就两点：
 *  1、可以通过改变状态来获得不同的行为。
 *  2、你的好友能同时看到你的变化。
 *
 * 根据这个特性，状态模式在日常开发中用的挺多的，尤其是做网站的时候，我们有时希望根据对象的某一属性，
 * 区别开他们的一些功能，比如说简单的权限控制等。由状态切换类的method，做不同状态的区别对待
 */
public class StateTest {

    public static void main(String[] args) {
        State state = new State();
        Context context = new Context(state);

        //设置第一种状态
        state.setValue("state1");
        context.method();

        //设置第二种状态
        state.setValue("state2");
        context.method();
    }
}
