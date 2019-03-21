import java.util.HashMap;
import java.util.Map;

public class Test {


    public static double scoreConfused(int score) {
        double maxValue = 3840f;
		/*int step = 10;
		double segment = maxValue / step;
		return (double) score / segment;*/
        String confusedScore = String.format("%.6f",(double) score /maxValue);
        return Double.valueOf(confusedScore);
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(scoreConfused(3100));

//        Map<String,String> map = new HashMap<>();
//        map.put("yu","yu");
//        for (int i=0;i<10000;i++){
//            long t1 = System.currentTimeMillis();
//            er:
//            while (true){
//                map.get("yu");
//                if (System.currentTimeMillis()-t1>50){
//                    break er;
//                }else {
////                    Thread.sleep(10L);
//
//                }
//            }
//            System.out.println("-----: "+i);
//        }

    }
}
