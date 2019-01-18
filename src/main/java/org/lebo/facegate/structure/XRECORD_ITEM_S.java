package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import org.lebo.facegate.FaceGateApi;

import java.util.Arrays;
import java.util.List;

/**
 * 抓拍记录数据结构体
 *
 */
public class XRECORD_ITEM_S extends Structure {
    public XRECORD_ITEM_S(){
        super();
    }
    public XRECORD_ITEM_S(Pointer p){
        super(p);
    }
    //在数据库中的 ID 号
    public int dwID;
    //创建时间
    public XTIME_S stCreateTime = new XTIME_S.ByReference();
    // 相似度 1 黑白名单比对相似度
    public float fSimilarity1;
    //相似度 2 身份证比对相似度
    public float fSimilarity2;
    //0: 无 1: 开门 2： 拒绝
    public int   dwStatus;
    //验证类型 身份证验证:VT_IDCARD 黑白名单验证: VT_DBPERSO 身 份 证 + 黑 白 名 单 :VT_IDCARD_DBPERSON
    public int   dwVerfyType;
    //人员信息
    public XPERSON_ITEM_S stPersonInfo;
    //图像数据大小: 现场图片
    public int dwImageSize1;
    //图像数据大小: 注册图片
    public int dwImageSize2;

    public byte[] szRes = new byte[28];

    @Override
    public String toString(){
        String info = "{dwID:" + dwID + ", fSimilarity1:" +fSimilarity1 + ",fSimilarity2:" +fSimilarity2+ ",dwStatus:" + dwStatus +" ,dwVerfyType:"+dwVerfyType
                + ",stPersonInfo:" + stPersonInfo +"}";
        return info;

    }
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwID","stCreateTime","fSimilarity1","fSimilarity2","dwStatus","dwVerfyType",
                "stPersonInfo","dwImageSize1","dwImageSize2","szRes");
    }
    public static class ByReference extends XRECORD_ITEM_S implements Structure.ByReference{}

    public static class ByValue extends XRECORD_ITEM_S implements Structure.ByValue{}
}
