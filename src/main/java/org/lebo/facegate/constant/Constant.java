package org.lebo.facegate.constant;

public class Constant {
	public interface SEARCH_TYPE_E {
		int SEARCH_TYPE_PERSON	 = 0x0;		//名单记录搜索
		int SEARCH_TYPE_IPCAM	 = 0x1;		//抓拍记录搜索			
		int SEARCH_TYPE_RECORD	 = 0x2;		//控制记录搜索
		int SEARCH_TYPE_LOG	 = 0x3;		//日志搜索 
	}
	
	public interface RET_TYPE_E {
		int RET_NONE			= 0x0;
		int RET_SUCCESS			= 0x1;					
		int RET_ERROR			= 0x2;
		int RET_CMDERR			= 0x3;
		int RET_DATA_TOOLONG	= 0x4;
		int RET_MEMALLOC_ERR	= 0x5;
		int RET_DATALEN_ERR		= 0x6;
		int RET_WRITE2DISK_ERR  = 0x7;
		int RET_WRITE2DISK_ERR2 = 0x8;
		int RET_NOFACE_ERR		= 0x9;
		int RET_NORESULT		= 0xA;
		int RET_IMPORTING		= 0xB;	//正在批量导入
		int RET_PERSON_MAX		= 0xC;  //人员名单已达最大值
	}

	public interface CMD_TYPE_E {
		int CMD_NONE			= 0x0;
		int CMD_HEARBEAT		= 0x1;

		//1. 连接
		int CMD_LOGON			= 0x2;		//连接平台

		//2. 消息
		int CMD_MESSAGE			= 0x3;		//实时消息

		int CMD_CLOSE_DEV		= 0x4;		//关闭设备

		//3. 参数
		int CMD_GET_SYSTEM_PARAM=0x5;		//XSYSTEM_PARAM_S
		int CMD_SET_SYSTEM_PARAM=0x6;

		int CMD_GET_IPC_PARAM=0x7;			//IPC_PARAM_S

		int CMD_GET_AUTO_MAINTAIN=0x8;
		int CMD_SET_AUTO_MAINTAIN=0x9;		//自动重启时间

		int CMD_GET_DEVICE_TYPE=0xA;		//获取设备类型 0: 普通一体机 x86; 1: 4路抓拍比对服务器， 2: 9路抓拍比对服务器， 3.一体机 3399， 4，门禁机
		int CMD_FACEPICTURE_OPENDOOR=0xB;	//人脸图片开门
		int CMD_GET_PICTURE_EIGENVALUE=0xC; //获取图片特征值

		int CMD_GET_VERIFY_PARAM= 0x10;		//XVERIFY_PARAM_S
		int CMD_SET_VERIFY_PARAM= 0x11;

		int CMD_GET_SOUND_PARAM	= 0x12;		//XSOUND_PARAM_S
		int CMD_SET_SOUND_PARAM	= 0x13;

		int CMD_GET_USER_PARAM	= 0x14;		//XUSER_PARAM_S
		int CMD_SET_USER_PARAM	= 0x15;

		int CMD_GET_NET_PARAM	= 0x16;		//XNET_PARAM_S
		int CMD_SET_NET_PARAM	= 0x17;

		int CMD_GET_NTP_PARAM	= 0x18;		//XNTP_PARAM_S
		int CMD_SET_NTP_PARAM	= 0x19;

		int CMD_GET_TIME		= 0x1A;		//XTIME_S
		int CMD_SET_TIME		= 0x1B;

		int CMD_GET_CENTER_PARAM= 0x1C;		//XCENTER_PARAM_S
		int CMD_SET_CENTER_PARAM= 0x1D;

		int CMD_RESORE_DEFAULT	= 0x1E;		//恢复出厂值
		int CMD_REBOOT			= 0x1F;		//重启

		//4. 名单管理: 添加 删除 修改
		int CMD_ADD_PERSON		= 0x20;
		int CMD_DEL_PERSON		= 0x21;
		int CMD_MODIFY_PERSON	= 0x22;
		int CMD_IMPORT_PERSON	= 0x23;		//导入名单
		int CMD_IMPORT_END		= 0x24;		//导入结束
		int CMD_IMPORT_REQ_COUNT= 0x25;		//请求设备导入名单总数
		int CMD_IMPORT_START	= 0x2E;		//导入开始

		int CMD_FACELIB_IMPORT  = 0x26;		//人脸库导入
		int CMD_FACELIB_EXPORT  = 0x27;		//人脸库导出
		int CMD_DEL_PERSON_CUSTOMIZE = 0x28;//CustomizeID方式删除
		int CMD_BATCHDEL_PERSON		= 0x29;//数据库ID批量删除
		int CMD_BATCHDEL_PERSON_CUSTOMIZE=0x2A;//用户自定义ID批量删除
		int CMD_GET_LISTINFO_BYID=0x2B;//根据ID获取用户信息
		int CMD_GET_LISTINFO_BYCUSTOMIZE=0x2C;//根据用户自定义ID获取用户信息
		int CMD_REMOTE_OPENDOOR	= 0x2D;	//远程开门

		//5. 搜索
		int CMD_SEARCH_OPEN		= 0x30;
		int CMD_SEARCH_READ		= 0x31;
		int CMD_SEARCH_CLOSE	= 0x32;

		//升级
		int CMD_UPGRADE_FILE	= 0x40;		//文件升级
		int CMD_SEND_FILE_END	= 0x41;		//发送升级文件结束
		int CMD_EXPORT_NAMELIST = 0x42;		//导出名单库

		int CMD_GET_ENTER_IPC	=0X50;		//获取接入摄像机参数，最大9路
		int CMD_SET_ENTER_IPC	=0X51;		//设置接入设置参数，最大9路
		int CMD_GET_WORKTYPE	=0X52;		//获取工种参数
		int CMD_SET_WORKTYPE	=0X53;		//设置工种参数
		int CMD_GET_SERVER_BASICPARA=0X54;	//获取人脸分析服务器基本参数。信息发布；通道选择；清空抓拍和比对信息
		int CMD_SET_SERVER_BASICPARA=0X55;	//设置人脸分析服务器基本参数
		int CMD_FACE_DETECT		=0X56;		//服务功能，人脸检测
		int CMD_FACE_COMPARISON =0X57;		//服务功能，人脸1:1比对
		int CMD_GETSCALE_AGEGENDERWORKTYPE=0X59;//获取性别比例，年龄段比例，工种比例接口
	/*数据回复格式：GENDERSCALE_S(男女性人数）+AGESCALE_S(年龄段人数)+WORKTYPESCALE_S(工种人数)
	当天的数据，工种的类型可能会变化，同时使用CMD_GET_WORKTYPE同步*/

		//西安丝路智慧旅游科技有限公司
		int CMD_GET_RECOGNITIONMODE = 0X61; //获取模式
		int CMD_SET_RECOGNITIONMODE = 0x62;	//设置模式
		int CMD_GET_GUISTYLE = 0x63;		//获取界面
		int CMD_SET_GUISTYLE = 0x64;		//设置界面

		//录像下载 回放
		int CMD_RECPLAY_START	= 0x70;			//开始播放、下载
		int CMD_RECPLAY_CTRL	= 0x71;			//播放、下载控制
		int CMD_RECPLAY_STOP	= 0x72;			//停止播放、下载
		int CMD_RECSEARCH_START = 0x75;			//开始搜索

		//门禁机
		int CMD_GET_ACCESSPWD = 0x80;			//获取已设置的密码
		int CMD_ADD_ACCESSPWD = 0x81;			//密码添加
		int CMD_DEL_ACCESSPWD = 0x82;			//密码删除
		int CMD_CLR_ACCESSPWD = 0x83;			//清除所有密码
	}
}

