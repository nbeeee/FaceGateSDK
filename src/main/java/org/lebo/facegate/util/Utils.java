package org.lebo.facegate.util;

public class Utils {
	public static String byte2Str(byte[] source,String code){
		try {
			if(source != null && source.length >0){
				int len = 0;
				for(byte b:source){
					if(b != 0){
						len ++;
					}else{
						break;
					}
				}
				if(code !=null){
					return new String(source,0,len,code);
				}else{
					return new String(source,0,len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static byte[] string2ByteArray(String str,int len){
		byte b[]=new byte[len];
		for(int i=0;i<str.length();i++){
			b[i]=Byte.parseByte((int)str.charAt(i)+"");
		}
		return b;
	}
}
