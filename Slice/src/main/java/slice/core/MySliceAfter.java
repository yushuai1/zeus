package slice.core;

import entry.balancer.BalancerInfo;
import entry.slice.SliceEntry;
import slice.auxiliary.AbsBalancer;

import java.util.List;

public class MySliceAfter extends AbsBalancer{

    /**
     * 就是数据新增的时候判定平衡
     * @param sliceEntry
     * @param balancerInfoList
     * @return
     */
    @Override
    protected List<BalancerInfo> isBalancer(SliceEntry sliceEntry, List<BalancerInfo> balancerInfoList) {
        return null;
    }

    /**
     * 向平衡器发送请求
     * @param sliceEntry
     * @param balancerInfoList
     */
    @Override
    protected void asyBalancerRequest(SliceEntry sliceEntry, List<BalancerInfo> balancerInfoList) {

    }
}
