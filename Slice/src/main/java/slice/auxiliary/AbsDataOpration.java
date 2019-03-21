package slice.auxiliary;

import entry.slice.SliceEntry;
import entry.slice.SliceOpration;
import slice.core.MySliceAfter;

public abstract class AbsDataOpration extends MySliceAfter{

    /**
     * 返回分片
     * @param sliceEntry
     * @return
     */
    public final String sliceName(SliceEntry sliceEntry){

        String sliceMemory = null;
        if (sliceEntry == null) {
            return sliceMemory;
        }

        /**
         * 数据仅仅增加
         */
        if (sliceEntry.getSliceOpration().equals(SliceOpration.DATA_ADD_ONLY)){
            sliceMemory = dataAddOnly(sliceEntry);
        }

        /**
         * 数据删除仅仅
         */
        if (sliceEntry.getSliceOpration().equals(SliceOpration.DATA_REDUCE_ONLY)){
            sliceMemory = dataReduceOnly(sliceEntry);
            calDataBalancer(sliceEntry);
        }


        return sliceMemory;
    }

    public abstract String dataAddOnly(SliceEntry sliceEntry);

    public abstract String dataReduceOnly(SliceEntry sliceEntry);

}
