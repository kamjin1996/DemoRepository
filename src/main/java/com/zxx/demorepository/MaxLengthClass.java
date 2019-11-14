package com.zxx.demorepository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author: tuosen
 * @date: 18:14 2019-10-24
 * @description:
 */
public class MaxLengthClass {
  // 名字校验正则
  public static final String NAMEREX = "[\u4e00-\u9fa5a-zA-Z\\·]+";

  public static void main(String[] args) throws IOException {
    // new MaxLengthClass().testProvider();

    System.out.println("校验是否通过：" + Pattern.matches(NAMEREX, "玉山江·吾守尔"));
  }

  public void testProvider() throws IOException {
    ClassPathScanningCandidateComponentProvider provider =
        new ClassPathScanningCandidateComponentProvider(true);
    provider.addIncludeFilter(new AssignableTypeFilter(Object.class));
    Set<BeanDefinition> org = provider.findCandidateComponents("org");
    List<String> all = new ArrayList<>();
    for (BeanDefinition beanDefinition : org) {
      String beanClassName = beanDefinition.getBeanClassName();
      if (!beanClassName.contains("$")) {
        all.add(beanClassName);
      }
    }
    all.sort(((o1, o2) -> o2.length() - o1.length()));
    ListIterator<String> i = all.listIterator();
    StringBuilder sb = new StringBuilder();
    while (i.hasNext()) {
      sb.append(i.next());
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }
}
