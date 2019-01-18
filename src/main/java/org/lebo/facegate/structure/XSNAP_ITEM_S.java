package org.lebo.facegate.structure;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 抓拍现场图片
 *
 */
public class XSNAP_ITEM_S extends Structure {
    public XSNAP_ITEM_S(){
        super();
    }
    public XSNAP_ITEM_S(Pointer p){
        super(p);
    }
    // 在数据库中的 ID 号
    public int dwID;
    //创建时间
    public XTIME_S stCreateTime = new  XTIME_S.ByReference();
    // bmp
    public int dwImageType;
    //图像数据大小: 现场图片
    public int dwImageSize;
    //比对结果 0： 黑白名单比对失败， 1： 比对成功是黑名单， 2： 比对成功是白名单， 3：身份证+人脸比对失败， 4： 身份证+人脸比对成功， 5： 身份证+名单+人脸比对成功
    //public int FacePictureType;
    // 备用
    public byte[] szRes = new byte[44];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwID","stCreateTime","dwImageType","dwImageSize","szRes");
    }
    public static class ByReference extends XSNAP_ITEM_S implements Structure.ByReference{}

    public static class ByValue extends XSNAP_ITEM_S implements Structure.ByValue{}
}
