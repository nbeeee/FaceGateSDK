package org.lebo.facegate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lebo.facegate.constant.Constant;
import org.lebo.facegate.constant.Constant.RET_TYPE_E;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import org.lebo.facegate.structure.XPERSON_ITEM_S;
import org.lebo.facegate.structure.XSEARCH_COND_S;
import org.lebo.facegate.structure.XSEARCH_READ_S;
import org.lebo.facegate.structure.XSEARCH_RESULT_S;
import org.lebo.facegate.util.BaseUtil;
import org.lebo.facegate.util.Utils;

public class PersonOperate {
	public static Gson gson = new GsonBuilder().create();

	public static void addPerson(FaceGateApi instance,HANDLE ghandle){
		FileInputStream  fis = null;
		try {
			fis = new FileInputStream(new File("E:\\document\\image\\16.png"));
			int imageLen = fis.available();
			byte[] imageData = new byte[imageLen];
			fis.read(imageData);

			XPERSON_ITEM_S.ByReference person = new XPERSON_ITEM_S.ByReference();
			byte[] szName = "zhangsan".getBytes();
			byte[] szIDCard = "4301221988923333".getBytes();
			System.arraycopy(szName, 0, person.szName, 0, szName.length);//切记这里有byte数组赋值时候用这个方式
			System.arraycopy(szIDCard, 0, person.szIDCard, 0, szIDCard.length);
			person.dwPersonType = 0;
			person.dwImageLen = imageLen;
			person.dwMjCardFrom = 0;
			person.dwCustomizeID = 100;
			person.dwGender = 1;
			person.bUseValidList = 1;//是否临时名单 0 否
			person.ValidBeginTime.year = 2018;
			person.ValidBeginTime.month = 12;
			person.ValidBeginTime.day = 29;
			person.ValidBeginTime.hour = 17;
			person.ValidBeginTime.minute = 0;
			person.ValidBeginTime.second = 0;

			person.ValidEndTime.year = 2019;
			person.ValidEndTime.month = 12;
			person.ValidEndTime.day = 29;
			person.ValidEndTime.hour = 17;
			person.ValidEndTime.minute = 0;
			person.ValidEndTime.second = 0;

			int nRet = instance.FACE_GATE_AddPerson(ghandle, person, imageData);

			System.out.println("新增人员名单返回" + nRet);
			if(nRet == RET_TYPE_E.RET_SUCCESS){
				System.out.println("新增人员名单成功");
			}else{
				System.out.println("新增人员名单失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updatePerson(FaceGateApi instance,HANDLE ghandle){
		FileInputStream  fis = null;
		try {
			fis = new FileInputStream(new File("E:\\document\\image\\16.png"));
			int imageLen = fis.available();
			byte[] imageData = new byte[imageLen];
			fis.read(imageData);

			XPERSON_ITEM_S.ByReference person = new XPERSON_ITEM_S.ByReference();
			byte[] szName = "lisi".getBytes();
			byte[] szIDCard = "43012219889255555".getBytes();
			System.arraycopy(szName, 0, person.szName, 0, szName.length);//切记这里有byte数组赋值时候用这个方式
			System.arraycopy(szIDCard, 0, person.szIDCard, 0, szIDCard.length);
			person.dwPersonType = 0;
			person.dwImageLen = imageLen;
			person.dwMjCardFrom = 0;
			person.dwCustomizeID = 100;
			person.dwGender = 1;
			person.bUseValidList = 1;//是否临时名单 0 否
			person.ValidBeginTime.year = 2018;
			person.ValidBeginTime.month = 12;
			person.ValidBeginTime.day = 29;
			person.ValidBeginTime.hour = 17;
			person.ValidBeginTime.minute = 0;
			person.ValidBeginTime.second = 0;

			person.ValidEndTime.year = 2019;
			person.ValidEndTime.month = 12;
			person.ValidEndTime.day = 29;
			person.ValidEndTime.hour = 17;
			person.ValidEndTime.minute = 0;
			person.ValidEndTime.second = 0;

			int nRet = instance.FACE_GATE_EditPerson(ghandle, person, imageData);

			System.out.println("修改人员名单返回" + nRet);
			if(nRet == RET_TYPE_E.RET_SUCCESS){
				System.out.println("修改人员名单成功");
			}else{
				System.out.println("修改人员名单失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deletePerson(FaceGateApi instance,HANDLE ghandle){
		int nRet = instance.FACE_GATE_DelPerson(ghandle, 100, 1);
		System.out.println("删除人员名单返回" + nRet );
		if(nRet == RET_TYPE_E.RET_SUCCESS){
			System.out.println("删除人员名单成功");
		}else{
			System.out.println("删除人员名单失败");
		}
	}

	public static void searchPersonData(FaceGateApi instance, HANDLE ghandle, FaceGateApi.SearchCallback searchCallback) throws Exception{
		XSEARCH_RESULT_S.ByReference searchResult = new XSEARCH_RESULT_S.ByReference();

		//搜索条件填充
		XSEARCH_COND_S.ByReference pSearchCond = new XSEARCH_COND_S.ByReference();
		//byte[] names = "lisi".getBytes();
		//System.arraycopy(names,0,pSearchCond.szName,0,names.length);
		pSearchCond.dwSearchType = Constant.SEARCH_TYPE_E.SEARCH_TYPE_PERSON;
		pSearchCond.dwGender = 2;//性别    0: 男   1: 女  2: 所有
		pSearchCond.dwAgeMin = 1;
		pSearchCond.dwAgeMax = 100;
		pSearchCond.stBgnTime.year = 2019;
		pSearchCond.stBgnTime.month = 1;
		pSearchCond.stBgnTime.day = 1;
		pSearchCond.stBgnTime.hour = 0;
		pSearchCond.stBgnTime.minute = 0;
		pSearchCond.stBgnTime.second = 0;

		pSearchCond.stEndTime.year = 2019;
		pSearchCond.stEndTime.month = 1;
		pSearchCond.stEndTime.day = 15;
		pSearchCond.stEndTime.hour = 23;
		pSearchCond.stEndTime.minute = 59;
		pSearchCond.stEndTime.second = 59;


		pSearchCond.dwType.dwPersonType = 2;//名单管理: 0: 白名单   1: 黑名单 2: 所有
		//搜索函数调用
		int nRet = instance.FACE_GATE_SearchAll(ghandle, pSearchCond, searchResult);

		if (nRet == RET_TYPE_E.RET_SUCCESS){
			//数据读取条件填充
			XSEARCH_READ_S.ByReference SearchRead = new XSEARCH_READ_S.ByReference();
			SearchRead.dwBeginNo = 0;
			SearchRead.dwReqCount = 5;
			SearchRead.dwSearchType = searchResult.dwSearchType;
			SearchRead.dwSessionID = searchResult.dwSessionID;
			List<XPERSON_ITEM_S> persons = new ArrayList<XPERSON_ITEM_S>();
			searchCallback.setPersonList(persons);
			instance.FACE_GATE_ReadDataItem(ghandle, SearchRead);
			Thread.sleep(1000);
			for(int i=0;i<persons.size();i++){
				XPERSON_ITEM_S personItems = persons.get(i);
				int dwID = personItems.dwID;
				int dwGender = personItems.dwGender;
				int dwCoustomId = personItems.dwCustomizeID;
				String szName = Utils.byte2Str(personItems.szName,"GBK");
				String szIDCard = Utils.byte2Str(personItems.szIDCard,null);

				System.out.println(String.format("dwID: %d, dwGender: %d, dwCoustomId: %d, szName: %s, szIDCard: %s",
						dwID,dwGender,dwCoustomId,szName,szIDCard));
			}
		}else{
			System.out.println("读取搜索数据失败 nRet = " + nRet);
		}
	}
}
