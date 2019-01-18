package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import org.lebo.facegate.FaceGateApi;

import java.util.Arrays;
import java.util.List;

/**
 * 实时消息数据结构体
 * dwMsgType	  0: 验证结果 1: 实时抓拍图片
 *
 */
public class MESSAGE_INFO_S extends Structure{
    public MESSAGE_INFO_S(){
        super();
    }
    public MESSAGE_INFO_S(Pointer p){
        super(p);
    }
    //0: 验证结果 1: 实时抓拍图片
    public int dwMsgType;

    //public ReciveRecordUnion.ByReference stItem = new ReciveRecordUnion.ByReference();
    //验证记录
    public XRECORD_ITEM_S stRecordItem = new XRECORD_ITEM_S.ByReference();
    //实时抓拍记录
    public XSNAP_ITEM_S stSnapItem = new XSNAP_ITEM_S.ByReference();

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwMsgType","stRecordItem","stSnapItem");
    }
    public static class ByReference extends MESSAGE_INFO_S implements Structure.ByReference{}

    public static class ByValue extends MESSAGE_INFO_S implements Structure.ByValue{}
}
