package slice.auxiliary.interFace;

import entry.balancer.BalancerInfo;
import entry.base.MachineInfo;

import java.util.List;

public interface MachineAddInterFace {

    public void calSlice(MachineInfo machineInfo,List<BalancerInfo> balancerInfoList);
}
