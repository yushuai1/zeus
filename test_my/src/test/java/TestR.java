public class TestR {

    /**
     * max=单机最大并发，50万底库
     */
    static private final float max = 26;
    /**
     * 3台机器
     */
    static private final float pcCount = 3;

    /**
     * @param number 并发数量
     * @param cpu  cpu使用量
     * @param backTime 每次返回的平均时间
     */
    public static void runc(float number,float cpu,float backTime){

//        float once=number/pcCount;

        //单机的tpc
        float tps = max*backTime*cpu;
        //3台机器的tps
        float sumTps = tps*pcCount;

        System.out.println("并发量："+number+" cpu使用率："+cpu+" 相应时间："+backTime+"  tps:"+sumTps);


    }
    public static void main(String[] args) {

        runc(50.0f,0.57f,1.8f);
    }
}
