package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

import java.util.Arrays;
import java.util.List;

public class ReciveRecordUnion extends Union {
    public ReciveRecordUnion(){
        super();
    }
    public ReciveRecordUnion(Pointer p){
        super(p);
    }

    //验证记录
    public XRECORD_ITEM_S stRecordItem = new XRECORD_ITEM_S.ByReference();
    //实时抓拍记录
    public XSNAP_ITEM_S stSnapItem = new XSNAP_ITEM_S.ByReference();

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("stRecordItem","stSnapItem");
    }
    public static class ByReference extends ReciveRecordUnion implements Structure.ByReference{}

    public static class ByValue extends ReciveRecordUnion implements Structure.ByValue{}
}
