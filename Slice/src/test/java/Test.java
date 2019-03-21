import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {


    /**
     * 备份数量，可以根据内存来判定
     * 含义：当没有并发的时候备份仅仅增加了健壮性
     *       当出现并发的时候，就会增大并发数，但是如果机器不给力就会造成并发变慢
     */
    private final static int bak = 2;
    /**
     * 要分的片数，根据cpu来计算
     */
    private final static int slience = 10;
    public final static ConcurrentLinkedQueue<String> auxiliaryList = new ConcurrentLinkedQueue<>();
    static {
        for (int i = 1; i <= bak; i++) {
            for (int k = 1; k <= slience; k++) {
                auxiliaryList.offer(String.format("MEMORY%s", k));
            }
        }
    }

    /**
     * 根据cpu和内存来计算
     */
    public final static Map<String, labors> map = new HashMap<>();
    static {
        map.put("1", new labors(4.0, 0.0));
        map.put("2", new labors(8.0, 0.0));
        map.put("3", new labors(6.0, 0.0));
        map.put("4", new labors(3.0, 0.0));
    }

    public static void main(String[] asr) {

        Double sum = 0.0;
        for (Map.Entry<String, labors> entry : map.entrySet()) {
            sum = sum + entry.getValue().getMemoryCount();
        }
        for (Map.Entry<String, labors> entry : map.entrySet()) {
            entry.getValue().setMemoryBl(entry.getValue().getMemoryCount() / sum);
        }
        for (Map.Entry<String, labors> entry : map.entrySet()) {
            Double number = entry.getValue().getMemoryBl() * auxiliaryList.size();
            entry.getValue().set余数部分(number-Math.floor(number));
            entry.getValue().set整数部分((int)Math.floor(number));
            entry.getValue().setMemoryNumber((int)Math.floor(number));
        }

        Integer 整数之和 = 0;
        for (Map.Entry<String, labors> entry : map.entrySet()) {
            整数之和 = 整数之和+entry.getValue().get整数部分();
        }

        Integer 需要向上取证的个数 = auxiliaryList.size()-整数之和;

        List<Map.Entry<String, labors>> list = new ArrayList<Map.Entry<String, labors>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, labors>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<String, labors> o1, Map.Entry<String, labors> o2) {
                return o2.getValue().get余数部分().compareTo(o1.getValue().get余数部分());
            }
        });

        for (int i=0;i<需要向上取证的个数;i++){
            String key = list.get(i).getKey();
            map.get(key).setMemoryNumber(map.get(key).getMemoryNumber()+1);
        }

        System.out.println(JSON.toJSONString(map));
    }

    static class labors {
        private Double memoryCount;
        private Double memoryBl;
        private Integer memoryNumber;
        private Integer 整数部分;
        private Double 余数部分;


        public labors(Double memoryCount, Double memoryBl) {
            this.memoryCount = memoryCount;
            this.memoryBl = memoryBl;
        }

        public Integer get整数部分() {
            return 整数部分;
        }

        public void set整数部分(Integer 整数部分) {
            this.整数部分 = 整数部分;
        }

        public Double get余数部分() {
            return 余数部分;
        }

        public void set余数部分(Double 余数部分) {
            this.余数部分 = 余数部分;
        }

        public Integer getMemoryNumber() {
            return memoryNumber;
        }

        public void setMemoryNumber(Integer memoryNumber) {
            this.memoryNumber = memoryNumber;
        }

        public Double getMemoryCount() {
            return memoryCount;
        }

        public void setMemoryCount(Double memoryCount) {
            this.memoryCount = memoryCount;
        }

        public Double getMemoryBl() {
            return memoryBl;
        }

        public void setMemoryBl(Double memoryBl) {
            this.memoryBl = memoryBl;
        }
    }
}
