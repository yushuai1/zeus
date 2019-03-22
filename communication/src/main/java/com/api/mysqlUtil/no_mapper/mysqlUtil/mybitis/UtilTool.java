package com.api.mysqlUtil.no_mapper.mysqlUtil.mybitis;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class UtilTool {


    public static InputStream getXml(String v2, String v3) throws Exception {
        String s1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
                "<configuration>\n" +
                "    <typeAliases>\n" +
                "        <package name=\"";
        String s2 = "\"/>\n" +
                "    </typeAliases>\n" +
                "    <mappers>\n" +
                "        <package name=\"";
        String s3 = "\"/>\n" +
                "    </mappers>\n" +
                "</configuration>";
        String result = String.format("%s%s%s%s%s",s1,v2,s2,v3,s3);
        return new ByteArrayInputStream(result.getBytes("UTF-8"));
    }
}
