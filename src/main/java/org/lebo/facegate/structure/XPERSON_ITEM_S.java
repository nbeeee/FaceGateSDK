package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 人员信息数据结构体
 *
 */
public class XPERSON_ITEM_S extends Structure {
    public XPERSON_ITEM_S(){
        super();
    }
    public XPERSON_ITEM_S(Pointer p){
    }
    //人员在数据库中的 ID 号
    public int dwID;
    //0: 白名单 1: 黑名单
    public int dwPersonType;
    //姓名
    public byte[] szName = new byte[32];
    //性别
    public int dwGender;
    //民族
    public int dwNation;
    //证件类型  0:身份证
    public int dwCardType;
    //身份证号码
    public byte[] szIDCard = new byte[32];
    //生日
    public XDATE_S stBirthday = new XDATE_S.ByReference();
    //电话号码 1
    public byte[] szTelNum1 = new byte[32];
    public byte[] szTelNum2 = new byte[32];
    //籍贯
    public byte[] szNative = new byte[32];
    //地址
    public byte[] szAddress= new byte[72];
    //备注
    public byte[] szNotes = new byte[64];
    //0: 无 1: 公用卡号 2: 自动生成 3: 手动输入
    public int  dwMjCardFrom;
    //门禁卡号
    public int  dwMjCardNo;
    //后面跟的图片数据长度
    public int dwImageLen;
    //是否是临时名单:0 永久名单 1:临时名单
    public int bUseValidList;
    //ID 由上层分配， 上层保证唯一性， 增加、修改、 删除都使用这个 ID
    public int dwCustomizeID;
    //临时名单的有效起始时间
    public XTIME_S ValidBeginTime = new XTIME_S.ByReference();
    //临时名单的有效终止时间
    public XTIME_S ValidEndTime = new XTIME_S.ByReference();
    //创建时间<只读>
    public XTIME_S stCreateTime = new XTIME_S.ByReference();
    //名单通道权限:全部通道,1： 选择通道
    public int     bAllChannelEnable;
    //Jurisdiction 权限， 不同的接入通道
    public int     dwChn;
    //临时名单的有效次数
    public int     dwEffectNumber;//临时名单3的有效次数
    public byte[]    szIdCardId = new byte[18];       //身份证的ID号
    public byte[]    szAccessId = new byte[18];       //门禁卡号
    public byte[]    szWardenId = new byte[18];       //管理员的授权卡卡号
    public byte[]    szRoomNum = new byte[18];       //房屋号
    //保留位
    public byte[] szRes = new byte[64];


    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwID","dwPersonType","szName","dwGender","dwNation","dwCardType",
                "szIDCard","stBirthday","szTelNum1","szTelNum2","szNative","szAddress","szNotes",
                "dwMjCardFrom","dwMjCardNo", "dwImageLen","bUseValidList","dwCustomizeID","ValidBeginTime",
                "ValidEndTime","stCreateTime","bAllChannelEnable","dwChn","dwEffectNumber","szIdCardId",
                "szAccessId","szWardenId","szRoomNum","szRes");
    };

    public static class ByReference extends XPERSON_ITEM_S implements Structure.ByReference{};

    public static class ByValue extends XPERSON_ITEM_S implements Structure.ByValue{};

}