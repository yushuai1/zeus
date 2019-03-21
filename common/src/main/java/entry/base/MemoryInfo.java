package entry.base;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryInfo {

    /**
     * 分片的片号，这个是随机产生的uuid
     */
    private String memoryId;

    /**
     * 分片名字对应的就是redis要监控的名字
     */
    private String memoryName;

    /**
     * 该片下有多少数据
     */
    private long count;

    /**
     * 该片下所有的app
     */
    private Map<Long,AppInfo> appInfoMap = new HashMap<>();

    private String ip;

    public MemoryInfo() {
        this.memoryId = UUID.randomUUID().toString().replace("-","");
    }

    public String getMemoryId() {
        return memoryId;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public long getCount() {

        if (this.appInfoMap==null){
            return 0;
        }
        for (Map.Entry<Long,AppInfo> entry:this.appInfoMap.entrySet()) {
            this.count = this.count + entry.getValue().getCount();
        }
        return count;
    }


    public Map<Long, AppInfo> getAppInfoMap() {
        return appInfoMap;
    }

    public void setAppInfoMap(Map<Long, AppInfo> appInfoMap) {
        this.appInfoMap = appInfoMap;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "MemoryInfo{" +
                "memoryId='" + memoryId + '\'' +
                ", memoryName='" + memoryName + '\'' +
                ", count=" + count +
                ", appInfoMap=" + appInfoMap +
                ", ip='" + ip + '\'' +
                '}';
    }
}
