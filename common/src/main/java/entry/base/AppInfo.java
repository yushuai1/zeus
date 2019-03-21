package entry.base;

public class AppInfo {

    /**
     * appid
     */
    private Long appId;

    /**
     * app 名字
     */
    private String appNmae;

    /**
     * 某一个分片下的app的数据量
     */
    private long count;

    /**
     * 左眼数据量
     */
    private int leftCount;

    /**
     * 右眼数据量
     */
    private int rightCount;

    /**
     * 所在机器ip
     */
    private String ip;

    /**
     * 所在片的片号
     */
    private String memoryId;

    public AppInfo(Long appId) {
        this.appId = appId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppNmae() {
        return appNmae;
    }

    public void setAppNmae(String appNmae) {
        this.appNmae = appNmae;
    }

    public long getCount() {
        return this.leftCount+this.rightCount;
    }



    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(String memoryId) {
        this.memoryId = memoryId;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appId=" + appId +
                ", appNmae='" + appNmae + '\'' +
                ", count=" + count +
                ", leftCount=" + leftCount +
                ", rightCount=" + rightCount +
                ", ip='" + ip + '\'' +
                ", memoryId='" + memoryId + '\'' +
                '}';
    }
}
