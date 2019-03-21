package com.序列化;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.protobuf.InvalidProtocolBufferException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TestTest {
    private final static Logger log = LoggerFactory.getLogger(TestTest.class);
    static{
        ParserConfig.getGlobalInstance().setAsmEnable(false);
        SerializeConfig.getGlobalInstance().setAsmEnable(false);
    }
    public static void main( String[] args ){


        try {
           String[] das = new String[2];
           System.out.println(das[3]);
        }catch (Exception e){
            e.printStackTrace();
        }

        String mg = "[{\n" +
                "\t\"appid\": 111111111,\n" +
                "\t\"eyeModel\": \"1\",\n" +
                "\t\"featureInfo\": \"6ubVlYeHMXh4aAE1lMfPF29oUBESP//fXP6/l3/96+suIlDNx0AgNKzu+1kXJm3d8qJszJcVeTv+zAEB8v7sRZaU6mIRXd+3tzFIz91Qq6+nFFDI+qODJiydvc+mqs6/EbJiSMyFVTF7/k0BArL+7EUVt7cQAEBVlZ2mIuru/z2Vh6b/2UQmv5dV/OqLkRRmbL2pyNPW5rrZWVNNjsKx00HBwMrLPh+isxCpJCHF9/v5TCQVUYAip5fHaDAUpysIXVXV15c3L29s7Pp5Xc3ielHVyMT++3FB/P/+7FjV16NqWV1VVbemqqoAEZe3t/9/ff3//7elBQBIyIKKqzeVXaSsvPjZy29NzMJraYXXW8NrT0wETv/7S0GXpq44fHVlilrzLW1YE6eu1Vxse4KAPX93wICffmEBEIbO92t5SG0IwSrA4OyE/TW01MTc3MzspaWlrT2l7Xl6zs5tbzdTWgW3/M/PMWpoEZWGrv3dBCK/B3X46oMVFi78uahK17amxtVdePqubTv70siE/X81ETKC29Pa+Dg6tpcVETERemp9fRKS9v/dFRGEpqf/elhI6u63l5e3FVhoSEARlIaHt2hIETDUz4c3amgREZY//91c/r9X//7v25NHRzU9qMneAAAAAKr/VWL1+pRodbEVJAiiUhIzGKHxcQAASkf/QFI=\",\n" +
                "\t\"featureid\": \"dZQQBPCyJSO8p8OakcucjXKjv6q0cttn\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}, {\n" +
                "\t\"appid\": 1,\n" +
                "\t\"eyeModel\": \"1\",\n" +
                "\t\"featureInfo\": \"lbUREV3/7oYQeWhZ366vt5cEBdfd//v6/69/9/////95DUWAjta2q2p7/+zIERXVrHp794bv/xAAankR1f+uIpVQQGp6v7+GpmJf2PzVBw0zMyeElVk6iCylKW08rGroBAB4eXP///8CKmoAEdXvpgKojMDSf38/1c7uvzNRERDd//8qSoyld/efjoNN7KozFp0JOrKqKimRtNUaKkZRpQvj39tFTNDc57X2X9+EoYyAk9s0GI+GwjJZXYWGIra7FTWdnc/u7vrqSEBdVc/O2lNX/01E52pgcH21pSzvLS0iIreVlZUVQGhq+r+v7+/u/f19fTMTSyEASihvKypJS+r6e2tsQFDVxPJzdWXl7sBA6mkZHc2Pigr6mJqaG1tdEIWX4mjdzLvz6p0QSFmVl+fq+AVF87ws38qnNzjq/x2+ZuiMQSvhQQMLCxmIqKyMnore387fUFxtYuLizsXtfWzXl67iNxQASd//PypIjad394+OA8zoKjaVzUQygqJKsraVEDq6urOS3pwVP68NPd19PTMg/90ZOTsRAFn/rpe3+npIMTkszc7G5zpqau/VlZUVFRXd76aASEhR1///v3loWN2fr7eXlgEF1f//+v6///////f/7e+79tUdXVGSAAAAAKr/WFtIb38i6Uf8W8x9p1HzpwywgwAAQEb/QEg=\",\n" +
                "\t\"featureid\": \"KcVBWyK1jNFBq3Y8omRoMniyVblkB6T4\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}, {\n" +
                "\t\"appid\": 111111111,\n" +
                "\t\"eyeModel\": \"1\",\n" +
                "\t\"featureInfo\": \"7v//WQFN3JABJ///QEAFBa7/3QATI66XzVg5poLLe305UEDmq7+18WgIx//7tU3Ot7cAQACv7/sQAADu7oVdWdf/vzUAAN7/v3hqgpWNavr6OZXEyGs3EUBov5f//yoK//+3QEAAru/6KAAiqqbv7e7+3/+uABWVWXvuzs3v//8AAv/u11lIMrfGSX19xaMCQP2NFRFL777/+0ggcIi3N5GV8/zHkAQwCen0THUEiz4Koii5wcDNFR0dERCq7nf3lydX1+eiNb///f39nZemKAAVt/emABBT/6I6OHgQEZW//+5qSBEVlRUAAATu/3t6yoWF77/++tzFTUl4ODh4/6IrDe/+3s/Oh48qSAGVr66qEDX1/3M5XU0RHZeMCJnd/cKoP//IABGq6u9dFBD7ejvXxGg5t+5oEZW3/UhqMpbxjCjcBkYXGzLKquanpLd3Z+Pij6+OhY2NnZUBKKKnt/7bAACfN67/VQAz/+7XSUg3p8JZfX2VoyL43YUVEWuu6u//NzMzurq2l5VZn5fdHRG1FVF46reVXX0zEBC+/xR6+qZq//7uagC3l5Xdamrq7v//XRFBlJARL///aAAABQGv/3pAABWt//8AABOqt9VIOLeGivt9fb+zIvrvxVVRAAAAAKr/WFtIb38i6Uf8W8x9p1HzpwywfAAASUH/QFE=\",\n" +
                "\t\"featureid\": \"9ZgALry6tXnp6TImJOiE4XS2VzdtnoRQ\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}, {\n" +
                "\t\"appid\": 1,\n" +
                "\t\"eyeModel\": \"2\",\n" +
                "\t\"featureInfo\": \"7v//XUFt1JEBLv//QEAVJK7/3QARKqrXXVg1ooL/fX1i1Z1FeTt7+u4il/++FUnd/7cASECv7/8QABDv7sV9WdXfvz0AAP7vNXhqgpUtavu6MZXEaHtTGGho+pe//78K//+3WEhIzu/vAAAiqqbvff/+//+vABAVUXvq7s3v//8AAv/u1V1oN6fCWX19hacCSO0lEFBKL7///9JAxebYUc8JuMgAAjNWaR+yaMVOHdUvXV+M5Z+zgm6C55fVlVltXceOz89Oxe/nJa5VlZWGIgARFdWHABAXv4aqOjgQEJX//+pqQBGVlRUAAATu//t6woTFr7+/tpfFTWhoaGhot+Ng0+urkbuBl4eiKBGVl8aiEBS1vzM5PQ0VFZegABWXN8bA//9oABEI7u5dFBD+ajvTxGR7n+5oERe33UhqIpJYRqE8fQv0AYiNF3VxOnLy6u6ClqWFFTGzt5GQYOKn9/7ZAAC3L6r/VQAz/+7VWTi3pkJ9fX2GowJo7TUQUG8vau7/PzMz/762l9Vd35XdFRGXlVF4+raVXX0zEBC6v5R6+u7u///uaAC3l5XVa2rq7v//VRFBlJARL///UAAADQOv/1lIABWu//8AEDOql91IMbeCgv99fb+zau7vfVBIAAAAAKr/WFtIb38i6Uf8W8x9p1HzpwywfQAASEH/QE8=\",\n" +
                "\t\"featureid\": \"IsAscfa66K3rvnd5z2xoauFwk7e8gjVw\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}, {\n" +
                "\t\"appid\": 1,\n" +
                "\t\"eyeModel\": \"2\",\n" +
                "\t\"featureInfo\": \"lZcRERF97oYQOGhZ3Y6nt5cCAUTd96eu7G5///9/+/3qGB0RAMb2vytqemmEFVf/zHp7+6fv/xEA6nsRlf/vIpUQQEJ6v7+VhWt7+L7XymhhM4fFHXCgikklwzPk0uZlAABQeXn/7/8gKmoAEdX/r4IqrMTSuz8/Ecju77NSVRVN/vOmisxtd/u7INDMTJaShUUQmo6t+MQu1PrD8ZOb54h4j2MbsEiSr8v0mOe2Fl3C55vz0/G5A6aicFGVASCqERXd16eu6mp5UV3XV2/OWhMf/wUgr2poUHX1tSTud2UiIjeXFRUVAEhq6v+/v///+v7u7ioBURHTKqoc0VX/hrIranjdxNT35PLzcXXl78RI6nlZFc2Pjgo6vJiamztZs4z/c0Td3fu66pcUSGmVl+fq+AxV86Ys/86EMTBq/x3GBVyPcNZgTm5EJCfBAE5/VxDryG31VXx9aPri7s+tPWvV36ai9xQASN3/9yaKzGV/+6sg0MQMkpNFTDDILm80ur6VETr6srKS3pwRu78tPf19PTUA/705MTMREBH/r5en+upIcTIobf/Oz5Eq6u7dlZcVERFd766ECGhQ1de/v1AqaF3dpreXlwEFXf23pu7ub///f/v9bbyWlxVY8OLMAAAAAKr/QUMcFYDX/kYeqcIp69CyR8xihQAAPkj/QEU=\",\n" +
                "\t\"featureid\": \"cAPoPM0jxrinVrh8Nf43YFIAYHXSaofF\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}, {\n" +
                "\t\"appid\": 1,\n" +
                "\t\"eyeModel\": \"2\",\n" +
                "\t\"featureInfo\": \"lZUVEVX97oYQaWhZ36a3t5cABd3997Lu7G5//39//e3KWB1VAEbmvytqeEmGFhH/zHp7e4/O/xEAaHsRlf/uIpQQQGJ7v7/FB3v6vr/HyGkxM4UFe3KKbuqJuNWaEASfBAAQeXn/7/83KmooERX/ryIqrMDSvz8/FcTG7/sTFRHd//OiiMxtf/ujENTMrJKTTUhS6uepU1Lssr/eL8M6Tv2CKUEfDhWOlIv2Slk2dNOdOlAcDbF5McbiMFGVASCqERXd14eu6mp5UUHXVW3PylMX//0A7mpoEHn9taTubS0iIjeVlZUUQGhq6u+////+/v7u6gARWdv4TsokIK01r7IranjdxND3lMbyc3Wl78YA6vlZFU2Pjgo6npiam3tZO4TPsyBN3eo76v8UAGiVl7fq+ghF87YM/8qsFxIq/50RixZ/ndqIZKYmbCPBAExvFxLDym33VVx9aOry7s7t/WzXl6YzFxQASd/7pqLIzHd/uyFQ1MyukpdFeNqCK068urqXESj7u7Oz2t8VP6+NHf3dPRGg//0dMTExAIf/75e3+upIeTgoTM/OxxMqaurdlZUVERXd766ASEhQ1/e/v3BoSN2Pp7eXhgBF3fe3qu5uf///e//97b6XlU1wOEmpAAAAAKr/QUMcFYDX/kYeqcIp69CyR8xihQAAPkf/QEQ=\",\n" +
                "\t\"featureid\": \"dud6RN8rhsUmBPuBPWspoS0grskt530b\",\n" +
                "\t\"personid\": \"viPbGOSQmd01ZD417ZY9Aat4wt4mvJgURBiiIASxYY9SMhZf3zG379LjrMLArZsE\"\n" +
                "}]";
        Test.SearchRequest.Builder v =  Test.SearchRequest.newBuilder();
        v.setQuery(mg);
        v.setPageNumber(10);
        v.setResultPerPage(12);
        Test.SearchRequest s = v.build();
        System.out.println(s.toString());
        byte[] asd = s.toByteArray();
        System.out.println(s.toByteArray().length);

        long t1 = System.currentTimeMillis();
        Test.SearchRequest m = null;
        for (int i = 0;i<10*10000;i++){
            try {
                m = Test.SearchRequest.parseFrom(asd);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        System.out.println("100000次时间 = "+(System.currentTimeMillis()-t1));
        System.out.print(m.toString());
        ///////////////////  json //////////////////////////////
        TestJson testJson = new TestJson();
        testJson.setPage_number(10);
        testJson.setResult_per_page(12);
        testJson.setQuery(mg);

        String jsons = JSON.toJSONString(testJson);
        byte[] bbb = jsons.getBytes();
        System.out.println(jsons.getBytes().length);
        TestJson tj=null;
        long t2 = System.currentTimeMillis();
        for (int i = 0;i<10*10000;i++){
            try {
                tj = com.alibaba.fastjson.JSON.parseObject(bbb,TestJson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         System.out.println("json 100000 次 = "+(System.currentTimeMillis()-t2));
    }
}
