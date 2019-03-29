package com.zxx.demorepository.lambdatest;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther: kam
 * @date: 12:00 2019-02-16
 * @description: VideoInfo是我们自己写的类，我们可以往里添加equals()和hashCode()方法。
 * 如果VideoInfo是我们引用的依赖中的一个类，我们无权对其进行修改，那么是不是就没办法使用distinct()按照某些元素是否相同，
 * 对对象进行自定义的过滤了呢？
 * 可以使用Wrapper，即包装类来达到目的:
 */
public class DistinctWrapperTest {
    public static class VideoInfoWrapper {
        private final VideoInfo videoInfo;

        VideoInfoWrapper(VideoInfo videoInfo) {
            this.videoInfo = videoInfo;
        }

        VideoInfo unWrapper() {
            return videoInfo;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VideoInfo)) {
                return false;
            }
            VideoInfo vi = (VideoInfo) obj;
            return videoInfo.id.equals(vi.id) &&
                    videoInfo.height == vi.height &&
                    videoInfo.wight == vi.wight;
        }

        @Override
        public int hashCode() {
            int n = 31;
            n = n * 31 + videoInfo.id.hashCode();
            n = n * 31 + videoInfo.wight;
            n = n * 31 + videoInfo.height;
            return n;
        }

        public static void main(String[] args) {
            List<VideoInfo> videoInfoList = Arrays.asList(new VideoInfo("123", 1, 2),
                    new VideoInfo("456", 4, 5),
                    new VideoInfo("123", 1, 2));

            Map<String, VideoInfo> videoInfoMap = videoInfoList.stream()
                    .map(VideoInfoWrapper::new)
                    .distinct()
                    .map(VideoInfoWrapper::unWrapper)
                    .collect(Collectors.toMap(VideoInfo::getId, v -> v, (oldValue, newValue) -> newValue));

            videoInfoMap.forEach((k, v) -> System.out.println("<" + k + "," + v + ">"));
        }
    }
}

/**
 * 不可修改类
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
class VideoInfo {
    @Getter
    String id;
    int wight;
    int height;
}
