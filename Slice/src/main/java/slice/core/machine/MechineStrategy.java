package slice.core.machine;

import entry.balancer.BalancerInfo;
import entry.base.MachineInfo;
import slice.auxiliary.interFace.MachineAddInterFace;

import java.util.List;

public class MechineStrategy {

    private MachineAddInterFace machineAddInterFace;


    public MechineStrategy(MachineAddInterFace machineAddInterFace) {
        this.machineAddInterFace = machineAddInterFace;
    }

    public void calSlice(MachineInfo machineInfo,List<BalancerInfo> balancerInfoList) {
        machineAddInterFace.calSlice(machineInfo,balancerInfoList);
    }

}
