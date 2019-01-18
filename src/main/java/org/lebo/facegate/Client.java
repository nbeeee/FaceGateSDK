package org.lebo.facegate;

import java.io.File;
import java.util.Scanner;

import org.lebo.facegate.FaceGateApi.LogonAcceptCallback;
import org.lebo.facegate.FaceGateApi.RecvMessageCallback;
import org.lebo.facegate.FaceGateApi.SearchCallback;

import org.lebo.facegate.callback.FaceGateCallback;

import com.sun.jna.platform.win32.WinNT.HANDLE;
import org.lebo.facegate.structure.XRECORD_ITEM_S;
import org.lebo.facegate.structure.XTIME_S;



public class Client extends Thread{
	private int    PORT = 6001;
	private byte[] ADDR = "188.128.0.133".getBytes();//与中心连接中设置的端口地址保持一致
	private LogonAcceptCallback lononAcceptCbk   = new FaceGateCallback.LogonAcceptCallbackImpl();
	private RecvMessageCallback recvmessageCbk   = new FaceGateCallback.RecvMessageCallbackImpl();
	private SearchCallback  searchCallback = new FaceGateCallback.SearchCallbackImpl();
	FaceGateApi instance = null;
	HANDLE ghandle = null;
	@Override
	public void start() {
		//保存人脸图片的路径 具体文件名为 customId + "_" + System.currentTimeMillis() + ".png"
		File image = new File("e:/face");
		if(!image.exists()){
			image.mkdir();
		}
		recvmessageCbk.setImageDir(image.getAbsolutePath());
		try {
			instance = FaceGateApi.instance;
			System.out.println(instance);
			//初始化函数
			instance.FACE_GATE_Init(PORT, lononAcceptCbk, recvmessageCbk, ADDR);
			
			Thread.sleep(3000);
			//设置搜索回调函数
			searchCallback.setImageDir("e:/face/search");
			instance.FACE_GATE_SetSearchCallbak(searchCallback);

			int errorsNum = 0;
			Scanner scanner = null;
			while(true) {
				if (ghandle == null) {
					//因为这里是模拟客户端连接必须等待回调接口返回
					ghandle = lononAcceptCbk.getHandle();
					errorsNum ++;
					if(errorsNum == 10) break;
					System.out.println("wait LogonAcceptCallback ....... ");
				} else {
					
					if (recvmessageCbk.getRecordItem() != null) {
						//如果有比对信息，这里可以获取比对
						XRECORD_ITEM_S recordItem = recvmessageCbk.getRecordItem();
						System.out.println("匹配相识度:" + recordItem.fSimilarity1 + "% 是否通过:" + (recordItem.dwStatus == 1 ? "是" : "否"));
						//摄像机位置信息
						System.out.println(recordItem.stPersonInfo.dwChn);
						//时间信息
						XTIME_S time = recordItem.stCreateTime;
						System.out.println(String.format("%04d%02d%02d %02d:%02d:%02d", time.year, time.month, time.day, time.hour, time.minute, time.second));
						//图片信息
						System.out.println("Image Path :" + recvmessageCbk.getImagePath());
						//处理完以后
						recvmessageCbk.recordItemClear();
					}
					System.out.println("input：");
					scanner = new Scanner(System.in);
					String line = scanner.nextLine();
					
					if("a".equals(line)){

						PersonOperate.addPerson(instance,ghandle);

					}else if("u".equals(line)){

						PersonOperate.updatePerson(instance,ghandle);

					}else if("d".equals(line)){

						PersonOperate.deletePerson(instance,ghandle);

					}else if("s".equals(line)){

						PersonOperate.searchPersonData(instance,ghandle,searchCallback);

					}else if("gc".equals(line)){

						ConfigOperate.getConfig(ghandle,instance);

					} else if("q".equals(line)){

						instance.FACE_GATE_UnInit();
						System.exit(0);
					}
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}




