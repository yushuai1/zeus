package com.彩票;

import java.util.*;

import static com.彩票.UtilTool.*;


/*
 * @创建人 : 于帅
 * @创建时间 :  2018/6/6 14:25
 * @描述 :  彩票模拟
 */
public class CoreCal {

    //拿出最近的多少期
    public static int numberData = 3;

    //预测个数
    public static int  numberType = 3;

    public static List<String> stringList = new ArrayList<>();


    public void runGet() {
        executor.execute(() -> {
            try {
                getNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runCal() {
        executor.execute(() -> {
            try {
                calNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/6/6 14:30
     * @描述 :
     * @参数 : 随机产生数据组合获取开奖号码
     * @返回 :
     */

    public void getNumber() throws Exception {

        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Set<Integer> set = new HashSet<>();
        while (true){
            Thread.sleep(10L);
            String uuid = UUID.randomUUID().toString();
            int index = getHash(uuid);
            int k = index%11+1;
            if (set.contains(k)){
                continue;
            }
            if (i==4){
                stringBuffer.append(k);
                UtilTool.queue.put(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                set = new HashSet<>();
                i = 0;
            }else if (i<4){
                stringBuffer.append(k).append("-");
                set.add(k);
                i++;
            }
        }
    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/6/6 14:59
     * @描述 :
     * @参数 : 处理彩票号
     * @返回 :
     */
    public void calNumber() throws Exception {

        List<Integer> yuche = new ArrayList<>();
        int k = 0;
        while (true){
            String number = queue.take();

            String[] oneNumber = number.split("-");

            if (yuche.size()==5){
                //计算成功率
                int hj = 0;
                for (String sd:oneNumber){
                    int ds = Integer.parseInt(sd);
                    yuche = yuche.subList(0,3);
                    for (Integer fg:yuche){
                        if (ds == fg){
                            hj++;
                            if (hj==3){
                                System.out.println("预测号码为："+yuche.toString());
                                System.out.println("出票号码为："+number);
                            }
                        }
                    }
                }
                yuche = new ArrayList<>();
                if (mapResult.containsKey(hj)){
                    mapResult.put(hj,mapResult.get(hj)+1);
                }else {
                    mapResult.put(hj,1);
                }
                System.out.println(mapResult.toString());
            }

            for (String s:oneNumber) {
                int mq = Integer.parseInt(s);
                if (mapTest.containsKey(mq)){
                    mapTest.put(mq,mapTest.get(mq)+1);
                }else {
                    mapTest.put(mq,1);
                }
            }
            k++;

            if (k<100){
                continue;
            }

            Map<Integer,Integer> map = sortMapByValue(mapTest);
            //预测下期号码
            int kg=0;
            for (Map.Entry<Integer,Integer> e:map.entrySet()) {
                kg++;
                if (kg<=5){
                    yuche.add(e.getKey());
                }else{
                    break;
                }
            }


//            System.out.println(number);
        }

    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/6/6 14:59
     * @描述 :
     * @参数 : 处理彩票号
     * @返回 :
     */
    public void calNumberBian() throws Exception {

    }



    public static void main(String[] sd) throws Exception {
        CoreCal coreCal =  new CoreCal();
        coreCal.runGet();
        coreCal.runCal();
    }
}
