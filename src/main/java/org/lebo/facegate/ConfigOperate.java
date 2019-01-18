package org.lebo.facegate;


import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import org.lebo.facegate.constant.Constant;
import org.lebo.facegate.structure.XCENTER_PARAM_S;
import org.lebo.facegate.util.Utils;

public class ConfigOperate {
    public static void getConfig(HANDLE ghandle,FaceGateApi instance){
        XCENTER_PARAM_S center_param = new XCENTER_PARAM_S();
        Pointer pointer = center_param.getPointer();
        try{
            int size = center_param.size();
            byte[] paramBts = new byte[size];
            IntByReference sizeref = new IntByReference(size);
            int cmd_type = Constant.CMD_TYPE_E.CMD_GET_CENTER_PARAM;
            int nRet = instance.FACE_GATE_GetServerConfig(ghandle, cmd_type, paramBts,sizeref);
            if (nRet == Constant.RET_TYPE_E.RET_SUCCESS){
                pointer.write(0,paramBts,0,size);
                center_param.read();
                String szCenterUrl = Utils.byte2Str(center_param.szCenterUrl, null);
                String szCenterUser = Utils.byte2Str(center_param.szCenterUser, null);
                String szCenterPass = Utils.byte2Str(center_param.szCenterPass, null);
                int port = center_param.dwCenterPort;
                System.out.println(String.format("server:%s ,port:%d ,user:%s ,passwd:%s",szCenterUrl,port,szCenterUser,szCenterPass));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
