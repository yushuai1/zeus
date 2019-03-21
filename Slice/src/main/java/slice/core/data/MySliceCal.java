package slice.core.data;

import entry.base.AppInfo;
import entry.base.MachineInfo;
import entry.base.MemoryInfo;
import entry.slice.SliceEntry;
import slice.auxiliary.AbsDataOpration;

import java.util.List;
import java.util.Map;

public class MySliceCal extends AbsDataOpration {


    /**
     * 数据仅仅增加，判定加到哪一个分片上
     *
     * @param sliceEntry
     * @return
     */
    @Override
    public String dataAddOnly(SliceEntry sliceEntry) {

        List<MachineInfo> machineInfoList = sliceEntry.getMachineInfoList();
        if (machineInfoList == null||machineInfoList.size()<1) {
            return null;
        }
        String memoryName = null;
        /**
         * 为了兼容平均和不平均两种情况，做全文遍历
         */

        Long memoryCount = Long.MAX_VALUE;

        for (MachineInfo machinfo:machineInfoList) {
            Map<String,MemoryInfo> memoryInfoMap = machinfo.getMemoryInfoMap();
            WR:
            for (Map.Entry<String, MemoryInfo> entry : memoryInfoMap.entrySet()) {
                /**
                 * 先检查app是否在此分片中，如果不在就直接过滤，目的是满足将来两种方案兼容
                 */
                Map<Long,AppInfo> appInfoMap = entry.getValue().getAppInfoMap();
                if (!appInfoMap.containsKey(sliceEntry.getAppId())){
                    continue WR;
                }

                /**
                 * 为了兼容，需要根据app中的数据来区分而不是根据片中的数据来区分
                 * 所以需要找到相应的app
                 */
                for (Map.Entry<Long,AppInfo> appInfo:appInfoMap.entrySet()){
                    long appCount = appInfo.getValue().getCount();
                    if (appCount<memoryCount){
                        memoryCount = appCount;
                        memoryName = entry.getValue().getMemoryName();
                    }
                }

            }
        }

        return memoryName;
    }

    /**
     * 数据删除
     *
     * @param sliceEntry
     * @return
     */
    @Override
    public String dataReduceOnly(SliceEntry sliceEntry) {
        return null;
    }

}
