import java.util.HashMap;
import java.util.Map;

public class Start {

    public static void main(String as[]){

        Map map = new HashMap();
        map.put("asd","asd");

        TestFather test = (TestFather) new Test(12,"yu");

        test.setClasses("nihao");

        System.out.println(test.toString());

    }
}
