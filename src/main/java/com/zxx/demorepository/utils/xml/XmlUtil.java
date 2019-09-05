package com.zxx.demorepository.utils.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * @auther: tuosen
 * @date: 14:18 2019-09-02
 * @description:
 */
public class XmlUtil {

    public static String toXml(Object obj) {
        return newInstanceIsToXml().toXML(obj);
    }

    public static <T> T toBean(String xmlStr, Class<T> t) {
        return (T) newInstanceIsFromXml().fromXML(xmlStr);
    }

    //private static Class[] enableAnnotationTargetClzs = new Class[]{BizBody.class, BizHeader.class,
    //        CreateImgBizBody.class, CreateImgResponse.class, CreateSealBizBody.class, CreateSealResponse.class,
    //        DeletebizBody.class, DeleteResponse.class, Request.class, SysHeader.class};
    //
    //private static Class[] valueIsNullStillOutputToXmlClzs = new Class[]{BizHeader.class, SysHeader.class};

    private static XStream newInstanceIsToXml() {
        XStream xStream = newInstance();
        xStream.registerConverter(getNullConverter());
        return xStream;
    }

    private static XStream newInstanceIsFromXml() {
        return newInstance();
    }

    private static XStream newInstance() {
        XStream xStream = new XStream(new DomDriver());
        //xStream.processAnnotations(enableAnnotationTargetClzs);
        return xStream;
    }

    private static Converter getNullConverter() {
        //NullConverter nullConverter = new NullConverter();
        //nullConverter.regAttribute(valueIsNullStillOutputToXmlClzs);
        //return nullConverter;
        return null;
    }

}
