package org.lebo.facegate.structure;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class XCENTER_PARAM_S  extends Structure {
    public XCENTER_PARAM_S(){
        super();
    }
    public XCENTER_PARAM_S(Pointer p){
        super(p);
    }

    public int		  bConnectCenter;				//连接中心: 开关
    public byte[]	  szCenterUrl        = new byte[64];			//服务器地址
    public int		  dwCenterPort;				//服务器端口
    public byte[]    szCenterUser       = new byte[32];			//用户名
    public byte[]    szCenterPass       = new byte[32];			//密码
    public int		  bSendSnapImage;				//发送实时图片
    public int		  bSendVerifyResult;			//发送认证结果
    public byte[]	  szRes              = new byte[128];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("bConnectCenter","szCenterUrl","dwCenterPort","szCenterUser","szCenterPass",
                "bSendSnapImage","bSendVerifyResult","szRes");
    }

    public static class ByReference extends XCENTER_PARAM_S implements Structure.ByReference{}

    public static class ByValue extends XCENTER_PARAM_S implements Structure.ByValue{}
}

