package slice.auxiliary;

import entry.balancer.BalancerInfo;
import entry.slice.SliceEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsBalancer {

    /**
     * 数据平衡计算
     */
    protected void calDataBalancer(SliceEntry sliceEntry){

        List<BalancerInfo> balancerInfoList = new ArrayList<>();
        if (isBalancer(sliceEntry,balancerInfoList).size()<1){
            return;
        }

        asyBalancerRequest(sliceEntry,balancerInfoList);
    }

    /**
     * 机器新加的平衡计算
     */
    protected void calMechineBalancer(SliceEntry sliceEntry,List<BalancerInfo> balancerInfoList){
        asyBalancerRequest(sliceEntry,balancerInfoList);
    }


    /**
     * 计算是否达到平衡,根据平衡点来判定是否达到平衡，平衡策略为达到平衡点和新加机器和删除机器
     * @param sliceEntry
     * @return
     */
    protected abstract List<BalancerInfo> isBalancer(SliceEntry sliceEntry,List<BalancerInfo> balancerInfoList);

    /**
     * 向平衡器发出异步命令，让分片器开始进行数据转移
     * @param sliceEntry
     * @param balancerInfoList
     */
    protected abstract void asyBalancerRequest(SliceEntry sliceEntry,List<BalancerInfo> balancerInfoList);
}
