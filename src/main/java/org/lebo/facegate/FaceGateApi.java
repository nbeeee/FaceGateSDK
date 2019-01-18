package org.lebo.facegate;

import com.sun.jna.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import org.lebo.facegate.structure.*;

import java.util.List;

public interface FaceGateApi extends Library {

	FaceGateApi instance = Native.loadLibrary("G:\\lib\\20190102\\FaceGateApi\\bin\\FaceGateApi.dll", FaceGateApi.class);

	/**
	 * 初始化
	 * wListenPort    初始化端口
	 * logonAcceptCbk 设备上线回调通知
	 * pMessageCBK	     实时消息回调
	 * pLocalAddr     启用服务地址，默认为空
	 * <p>
	 * return void
	 */
	void FACE_GATE_Init(int wListenPort, LogonAcceptCallback logonAcceptCBK, RecvMessageCallback messageCBK, byte[] pLocalAddr);

	/**
	 * 释放初始化
	 */
	void FACE_GATE_UnInit();

	/**
	 * 搜索人员数据
	 *
	 * @param hServer      验证后设备唯一标识
	 * @param pSearchCond  搜索条件
	 * @param searchResult 搜索返回数据
	 * @return
	 */
	int FACE_GATE_SearchAll(HANDLE hServer, XSEARCH_COND_S pSearchCond, XSEARCH_RESULT_S.ByReference searchResult);

	/**
	 * @param hServer   验证后设备唯一标识
	 * @param pReadInfo 读取数据条件
	 * @return
	 */
	int FACE_GATE_ReadDataItem(HANDLE hServer, XSEARCH_READ_S.ByReference pReadInfo);

	/**
	 * 搜索数据回调
	 */
	int FACE_GATE_SetSearchCallbak(SearchCallback searchCallback);

	/**
	 * 增加人员
	 */
	int FACE_GATE_AddPerson(HANDLE hServer, XPERSON_ITEM_S.ByReference person, byte[] pImageData);

	int FACE_GATE_EditPerson(HANDLE hServer, XPERSON_ITEM_S.ByReference person, byte[] pImageData);

	int FACE_GATE_DelPerson(HANDLE hServer, int dwPersonID, int nIDType);//nIDType 0.使用默认ID，1.使用应用dwCustomizeID

	int FACE_GATE_GetServerConfig(HANDLE hServer, int nConfigCommand, byte[] pConfigBuf, IntByReference nConfigBufSize);

	int FACE_GATE_SetServerConfig(HANDLE hServer, int nConfigCommand, Pointer pConfigBuf, int nConfigBufSize);

	/**
	 * 设备上线回调通知
	 * bOnLine      0.下线 1.上线
	 * hHandle	            通过验证后设备唯一标识
	 * pstLogonInfo 识别结果结构信息
	 */
	public static interface LogonAcceptCallback extends Callback {
		HANDLE getHandle();

		int callback(int bOnLine, HANDLE hHandle, LOGON_INFO_S.ByReference pointer);
	}

	/**
	 * 实时消息回调
	 */
	public static interface RecvMessageCallback extends Callback {
		void setImageDir(String fileDir);

		XRECORD_ITEM_S getRecordItem();

		String getImagePath();

		void recordItemClear();

		int callback(HANDLE hHandle, MESSAGE_INFO_S.ByReference pMsgHead, Pointer pData, int Len);
	}

	public static interface SearchCallback extends Callback {
		void setImageDir(String fileDir);

		void setPersonList(List<XPERSON_ITEM_S> persons);

		int callback(HANDLE hHandle, int SearchType, Pointer pData, int Len);
	}

}










