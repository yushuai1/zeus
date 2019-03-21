package slice.core.machine;

import entry.balancer.BalancerInfo;
import entry.base.MachineInfo;
import entry.slice.SliceEntry;
import slice.auxiliary.AbsMachineOpration;
import slice.auxiliary.interFace.MachineAddInterFace;

import java.util.List;

public class MyMachine extends AbsMachineOpration {
    @Override
    public void machineReduceOnly(MachineInfo machineInfo, List<BalancerInfo> balancerInfoList) {

    }

    @Override
    public void machineAddOnly(MachineInfo machineInfo, List<BalancerInfo> balancerInfoList) {
        MachineAddInterFace machineAdd = null;
        if (true) {
            machineAdd = new MechineAddDisposable();
        } else {
            machineAdd = new MechineAddOneByOne();
        }
        MechineStrategy mechineStrategy = new MechineStrategy(machineAdd);

        mechineStrategy.calSlice(machineInfo,balancerInfoList);
    }
}
