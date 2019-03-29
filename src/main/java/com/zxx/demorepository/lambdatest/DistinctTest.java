package com.zxx.demorepository.lambdatest;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther: kam
 * @date: 11:13 2019-02-16
 * @description: stearm的去重测试
 * 参考链接：https://blog.csdn.net/puppylpg/article/details/78556730
 */
public class DistinctTest {

    //@EqualsAndHashCode 这个注解可以解决distinct抛异常的问题，此处不偷懒，手动重写一下eqlus和hashcode方法
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class VideoInfo {
        @Getter
        String id;
        int width;
        int height;

        //IDEA快捷键生成的重写，同样管用
        //@Override
        //public boolean equals(Object o) {
        //    if (this == o) {
        //        return true;
        //    }
        //    if (o == null || getClass() != o.getClass()) {
        //        return false;
        //    }
        //    VideoInfo videoInfo = (VideoInfo) o;
        //    return width == videoInfo.width &&
        //            height == videoInfo.height &&
        //            Objects.equals(id, videoInfo.id);
        //}
        //
        //@Override
        //public int hashCode() {
        //    return Objects.hash(id, width, height);
        //}

        //手动重写eq和hc方法
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VideoInfo)) {
                return false;
            }
            VideoInfo vi = (VideoInfo) obj;

            //观察equals的调用次数,下面hashcode方法总是返回相同值时，才会调用eq方法，如果不同，则就无需eq
            //结果是只有hashCode相同的时候才会调用equal()进一步判断两个对象究竟是否相同；如果hashCode不相同，两个对象显然不相同。
            System.out.println("<===> Invoke equals() ==> "
                    + this.toString() + " vs. " + vi.toString());

            return this.id.equals(vi.id) &&
                    this.height == vi.height &&
                    this.width == vi.width;
        }

        @Override
        public int hashCode() {
            int n = 31;
            n = n * 31 + this.id.hashCode();
            n = n * 31 + this.width;
            n = n * 31 + this.height;
            return n;
            // return 1; //调用了三次eq
        }
    }

    public static void main(String[] args) {
        List<VideoInfo> videoInfoList = Arrays.asList(
                new VideoInfo("123", 1, 2),
                new VideoInfo("456", 4, 5),
                new VideoInfo("123", 1, 2));
        //duplication1
        Map<String, VideoInfo> duplication1 = videoInfoList.stream()
                .collect(Collectors.toMap(VideoInfo::getId, v -> v, (oldValue, newValue) -> newValue));
        System.out.println("No Duplicated1: ");
        duplication1.forEach((k, v) -> System.out.println("<" + k + "," + v + ">"));

        System.out.println("------------------------------------------------------------------------------");

        //duplication2
        Map<String, VideoInfo> duplication2 = videoInfoList.stream()
                .distinct().collect(Collectors.toMap(VideoInfo::getId, v -> v));
        System.out.println("No Duplicated2: ");
        duplication2.forEach((k, v) -> System.out.println("<" + k + "," + v + ">"));

        //未重写eq和hc方法，duplication2果然抛出异常，原因是distinct()方法依赖equles(),而此类（VideoInfo）的equles()和hashcode()未重写，
        // 一直用的object类的equles与hashcode方法的缘故
        //去重（即只要VideoInfo三个参数相同，即认为是需出重的目标），尝试重写eqlues和hashcode，看能否解决这个问题

        //结论:
        //list转map推荐使用toMap()，并且无论是否会出现重复的问题，都要指定重复后的取舍规则，不费功夫但受益无穷；
        //对一个自定义的class使用distinct()，切记覆写equals()方法；
        //覆写equals()，一定要覆写hashCode()；
        //虽然设计出一个hashCode()可以简单地让其return 1，这样并不会违反Java规定，但是这样做会导致很多恶果。比如将这样的对象存入hashMap的时候，所有的对象的hashCode都相同，最终所有对象都存储在hashMap的同一个桶中，直接将hashMap恶化成了一个链表。从而O(1)的复杂度被整成了O(n)的，性能自然大大下降。
        //好书是程序猿进步的阶梯。——高尔基。比如《Effecctive Java》。
    }


}
