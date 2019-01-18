package org.lebo.facegate.structure;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * 设备认证信息结构体
 *
 */
public  class LOGON_INFO_S extends Structure {
    public LOGON_INFO_S(){
        super();
    }
    public LOGON_INFO_S(Pointer p){
        super(p);
    }
    //设备名称
    public byte[] szDeviceName = new byte[64];
    //设备IP地址
    public byte[] szIPAddr     = new byte[60];
    //设备端口
    public int 	  dwPort;
    //设备用户名
    public byte[] szUserName   = new byte[32];
    //设备密码
    public byte[] szPassword   = new byte[32];
    //设备ID号
    public int 	  dwDeviceID;

    public byte   nType;//0 普通一体机 x86, 1: 4路抓拍比对服务器 2: 9路抓拍比对服务器 3.一体机 3399 4，门禁机

    public byte nDatabaseType;//0:旧的数据库，1:新的数据库

    public byte[] szRes        = new byte[128];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("szDeviceName","szIPAddr","dwPort","szUserName","szPassword","dwDeviceID","nType","nDatabaseType","szRes");
    }
    public static class ByReference extends LOGON_INFO_S implements Structure.ByReference{}

    public static class ByValue extends LOGON_INFO_S implements Structure.ByValue{}

}
