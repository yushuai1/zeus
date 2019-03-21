package entry.base;


import java.util.HashMap;
import java.util.Map;

/**
 * 机器信息
 */
public class MachineInfo {

    /**
     * 机器ip
     */
    private String machineIp;

    /**
     * cpu核心数
     */
    private int cpuCore;

    /**
     * 机器内存
     */
    private int flashMemory;

    /**
     * 机器虹膜数量
     */
    private long count = 0L;

    /**
     * 机器分片信息以及对应的分片
     */
    private Map<String,MemoryInfo> memoryInfoMap = new HashMap<>();

    public MachineInfo(String machineIp) {
        this.machineIp = machineIp;
    }

    public MachineInfo() {
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public int getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(int cpuCore) {
        this.cpuCore = cpuCore;
    }

    public int getFlashMemory() {
        return flashMemory;
    }

    public void setFlashMemory(int flashMemory) {
        this.flashMemory = flashMemory;
    }

    public long getCount() {
        if (this.memoryInfoMap==null){
            return 0L;
        }
        for (Map.Entry<String,MemoryInfo> entry:this.memoryInfoMap.entrySet()) {
            this.count = this.count + entry.getValue().getCount();
        }
        return count;
    }

    public Map<String, MemoryInfo> getMemoryInfoMap() {
        return memoryInfoMap;
    }

    public void setMemoryInfoMap(Map<String, MemoryInfo> memoryInfoMap) {
        this.memoryInfoMap = memoryInfoMap;
    }

    @Override
    public String toString() {
        return "MachineInfo{" +
                "machineIp='" + machineIp + '\'' +
                ", cpuCore=" + cpuCore +
                ", flashMemory=" + flashMemory +
                ", count=" + count +
                ", machineInfoMap=" + memoryInfoMap +
                '}';
    }
}
