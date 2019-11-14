package com.zxx.demorepository.test;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;

/**
 * @author: tuosen
 * @date: 15:20 2019-10-24
 * @description: 父类
 */
public class Father {
  public void doSomeThing() {
    System.out.println("Father do Some Thing...");
    this.doSomeThing();
  }

  public static void main(String[] args) {
    HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor
        hasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
    Father father = new Son();
    father.doSomeThing();
  }
}

class Son extends Father {

  @Override
  public void doSomeThing() {
    System.out.println("Son do Some Thing...");
    super.doSomeThing();
  }
}
