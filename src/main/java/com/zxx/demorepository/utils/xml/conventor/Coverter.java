package com.zxx.demorepository.utils.xml.conventor;

import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

/**
 * @auther: tuosen
 * @date: 16:14 2019-09-02
 * @description:
 */
public abstract class Coverter implements com.thoughtworks.xstream.converters.Converter{

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }
}
