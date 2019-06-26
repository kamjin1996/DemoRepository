package com.zxx.demorepository.runorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther: tuosen
 * @date: 11:52 2019-06-26
 * @description: 结果类
 */
@Component
public class Result {
    @Autowired
    private List<Rank> ranks;

    public void print() {
        this.ranks.forEach(Rank::print);
    }

}
