package util;

import com.alibaba.fastjson.JSON;
import entry.test.User;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

public class UtillTool {

    public static void main(String[] asd){

        List<User> users = new ArrayList<>();
        users.add(new User("yu",10));
        users.add(new User("shuai",11));
        users.add(new User("shi",12));

        System.out.println(JSON.toJSONString(users));
    }

    /**
     * 获取当前进程ID
     */
    private static String getCurrentThreadID()
    {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        return name.substring(0, name.indexOf("@"));
    }
}
