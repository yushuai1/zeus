package slice;

import com.sun.org.apache.bcel.internal.generic.IF_ICMPGT;
import entry.slice.SliceEntry;
import entry.slice.SliceOpration;
import slice.core.data.MySliceCal;
import slice.core.machine.MyMachine;

public class Test {

    public static void main(String[] asd) {

        /**
         * 数据操作
         */
        SliceEntry sliceEntry = new SliceEntry(SliceOpration.DATA_ADD_ONLY);
        MySliceCal mySliceCal = new MySliceCal();
        mySliceCal.sliceName(sliceEntry);

        /**
         * 机器操作
         */
        SliceEntry sliceEntrya = new SliceEntry(SliceOpration.MACHINE_ADD_ONLY);
        MyMachine myMachine = new MyMachine();
        myMachine.sliceName(sliceEntrya);



    }
}
