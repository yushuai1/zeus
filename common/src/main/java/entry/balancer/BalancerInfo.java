package entry.balancer;

public class BalancerInfo {

    /**
     * 是否允许转移，依赖于app的设置
     */
    private Boolean flag;

    /**
     * 从start转移到end，为片的id
     */
    private String startMemoryId;

    private String endMemoryId;

    /**
     * 从start转移到end，为片的名字
     */
    private String startMemoryName;

    private String endMemoryIdName;

    /**
     * 对应的appid
     */
    private Long appId;

    /**
     * 转移个数
     */
    private Long count;

    public BalancerInfo(Long appId) {
        this.appId = appId;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getStartMemoryId() {
        return startMemoryId;
    }

    public void setStartMemoryId(String startMemoryId) {
        this.startMemoryId = startMemoryId;
    }

    public String getEndMemoryId() {
        return endMemoryId;
    }

    public void setEndMemoryId(String endMemoryId) {
        this.endMemoryId = endMemoryId;
    }

    public String getStartMemoryName() {
        return startMemoryName;
    }

    public void setStartMemoryName(String startMemoryName) {
        this.startMemoryName = startMemoryName;
    }

    public String getEndMemoryIdName() {
        return endMemoryIdName;
    }

    public void setEndMemoryIdName(String endMemoryIdName) {
        this.endMemoryIdName = endMemoryIdName;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BalancerInfo{" +
                "flag=" + flag +
                ", startMemoryId='" + startMemoryId + '\'' +
                ", endMemoryId='" + endMemoryId + '\'' +
                ", startMemoryName='" + startMemoryName + '\'' +
                ", endMemoryIdName='" + endMemoryIdName + '\'' +
                ", appId=" + appId +
                ", count=" + count +
                '}';
    }
}
