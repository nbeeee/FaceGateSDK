package org.lebo.facegate.callback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;


import org.lebo.facegate.FaceGateApi.LogonAcceptCallback;
import org.lebo.facegate.FaceGateApi.RecvMessageCallback;
import org.lebo.facegate.FaceGateApi.SearchCallback;
import org.lebo.facegate.constant.Constant.SEARCH_TYPE_E;
import org.lebo.facegate.structure.LOGON_INFO_S;
import org.lebo.facegate.structure.MESSAGE_INFO_S;
import org.lebo.facegate.structure.XPERSON_ITEM_S;
import org.lebo.facegate.structure.XRECORD_ITEM_S;
import org.lebo.facegate.util.Utils;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

public class FaceGateCallback {
	public static class LogonAcceptCallbackImpl implements LogonAcceptCallback{
		public HANDLE gHandle;
		
		@Override
		public int callback(int bOnLine, HANDLE hHandle, LOGON_INFO_S.ByReference pointer) {
			 gHandle = hHandle;
			 if (bOnLine == 1){
				 System.out.println("设备上线 \n  ");
				String deviceName = Utils.byte2Str(pointer.szDeviceName, null);
				String szUserName = Utils.byte2Str(pointer.szUserName, null);
				String logonInfo = String.format("设备名称:%s \n设备IP : %s \n用户名 ：%s  \n密码 ： %s ", deviceName,new String(pointer.szIPAddr)
						,szUserName,new String(pointer.szPassword));
				System.out.println(logonInfo);
			 }else{
				//离线通知 注意：离线时最后一个参数<pstLogonInfo> 为空无效
				 System.out.println("设备下线 \n");
			 }
			 return 0;
		}

		@Override
		public HANDLE getHandle() {
			return gHandle;
		}

	}
	
	public static class RecvMessageCallbackImpl implements RecvMessageCallback{
		private int customId;
		private String fileDir;
		private XRECORD_ITEM_S recordItem;
		private String imagePath;
		private  FileImageOutputStream imageOutput;
		@Override
		public int callback(HANDLE hHandle, MESSAGE_INFO_S.ByReference pMsgHead, Pointer pData, int Len) {
			if(pMsgHead.dwMsgType == 0){
				customId = pMsgHead.stRecordItem.stPersonInfo.dwCustomizeID;
				recordItem = pMsgHead.stRecordItem;

			}else if(pMsgHead.dwMsgType == 1){
				int dwID = customId = pMsgHead.stSnapItem.dwID;
				int imageSize = pMsgHead.stSnapItem.dwImageSize;
				//System.out.println("dwId: "+dwID + " , imageSize:"+imageSize);
			}
			try {
				imagePath = fileDir + File.separator + pMsgHead.dwMsgType + File.separator + customId + "_" + System.currentTimeMillis() + ".png";
				File image = new File(imagePath);
				if(!image.exists()){
					image.createNewFile();
				}
				imageOutput = new FileImageOutputStream(image);
				imageOutput.write(pData.getByteArray(0, Len),0,Len);
				imageOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
		@Override
		public XRECORD_ITEM_S getRecordItem() {
			return this.recordItem;
		}
		@Override
		public void recordItemClear() {
			this.recordItem = null;
			this.imagePath = null;
			
		}
		@Override
		public void setImageDir(String fileDir) {
			File dir = new File(fileDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			if(dir.isDirectory()){
				this.fileDir = fileDir;
			}else
				this.fileDir = dir.getParent();
		}
		@Override
		public String getImagePath() {
			return this.imagePath;
		}
	}



	public static class SearchCallbackImpl implements SearchCallback{
		private String fileDir;

		private List<XPERSON_ITEM_S> persons;
		@Override
		public int callback(HANDLE hHandle, int SearchType, Pointer pData, int Len) {

			if (SEARCH_TYPE_E.SEARCH_TYPE_PERSON == SearchType){
				byte[] pDataBts = pData.getByteArray(0, Len);
				
				XPERSON_ITEM_S personItems = new XPERSON_ITEM_S();
				int personItemSize =  personItems.size();

				
				Pointer personItemPtr = personItems.getPointer();
				personItemPtr.write(0, pDataBts, 0, personItemSize);
				personItems.read();

				persons.add(personItems);
				String imagePath = "";
				FileOutputStream fos = null;
				try {
					imagePath = fileDir + File.separator + personItems.dwID + ".png";
					fos = new FileOutputStream(new File(imagePath));
					fos.write(pDataBts, personItemSize, Len - personItemSize);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return 0;
		}

		@Override
		public void setImageDir(String fileDir) {
			File dir = new File(fileDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			if(dir.isDirectory()){
				this.fileDir = fileDir;
			}else
				this.fileDir = dir.getParent();

		}

		@Override
		public void setPersonList(List<XPERSON_ITEM_S> persons) {
			this.persons = persons;
		}
	}
}
