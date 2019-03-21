package slice.auxiliary;

import entry.balancer.BalancerInfo;
import entry.base.MachineInfo;
import entry.slice.SliceEntry;
import entry.slice.SliceOpration;
import slice.core.MySliceAfter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsMachineOpration extends MySliceAfter {

    /**
     * 返回分片
     * @param sliceEntry
     * @return
     */
    public final Boolean sliceName(SliceEntry sliceEntry){

        List<BalancerInfo> balancerInfoList = new ArrayList<>();
        if (sliceEntry == null) {
            return true;
        }

        /**
         * 机器增加
         */
        if (sliceEntry.getSliceOpration().equals(SliceOpration.MACHINE_ADD_ONLY)){
            machineReduceOnly(sliceEntry.getMachineInfoList().get(0),balancerInfoList);
        }

        /**
         * 机器删除
         */
        if (sliceEntry.getSliceOpration().equals(SliceOpration.MACHINE_REDUCE_ONLY)){
            machineAddOnly(sliceEntry.getMachineInfoList().get(0),balancerInfoList);
        }

        /**
         * 做善后工作，主要就是调用平衡器
         */
        calMechineBalancer(sliceEntry,balancerInfoList);
        return true;
    }



    public abstract void machineReduceOnly(MachineInfo machineInfo,List<BalancerInfo> balancerInfoList);

    /**
     * 机器新增分为两种情况，一种是直接给出所有的分片，一种是只启动一片，满了之后再启动一片
     * @return
     */
    public abstract void machineAddOnly(MachineInfo machineInfo,List<BalancerInfo> balancerInfoList);
}
