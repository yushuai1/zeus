package entry.slice;

import entry.base.MachineInfo;

import java.util.*;

public class SliceEntry {
    /**
     * 数据和机器的操作指令
     */
    private SliceOpration sliceOpration;

    /**
     * 对应的数据所在的APP，
     */
    private long appId;
    /**
     * 机器IP
     *
     * @return
     */
    private String ip;

    private List<MachineInfo> machineInfoList;

    public SliceOpration getSliceOpration() {
        return sliceOpration;
    }

    public void setSliceOpration(SliceOpration sliceOpration) {
        this.sliceOpration = sliceOpration;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<MachineInfo> getMachineInfoList() {
        return machineInfoList;
    }

    public void setMachineInfoList(List<MachineInfo> machineInfoList) {
        this.machineInfoList = machineInfoList;
    }

    public SliceEntry(SliceOpration sliceOpration) {
        this.sliceOpration = sliceOpration;
    }

    @Override
    public String toString() {
        return "SliceEntry{" +
                "sliceOpration=" + sliceOpration +
                ", appId=" + appId +
                ", ip='" + ip + '\'' +
                ", machineInfoList=" + machineInfoList +
                '}';
    }
}
