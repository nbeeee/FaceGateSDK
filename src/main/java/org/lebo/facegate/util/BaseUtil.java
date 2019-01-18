package org.lebo.facegate.util;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.bind.DatatypeConverter;




public class BaseUtil {
	/**
	 * @Description (bean转byte数组)
	 * @param obj
	 * @return
	 */
	public static byte[] ObjectToByte(Object obj) {
	    byte[] bytes = null;  
	    try {  
	        // object to bytearray  
	        ByteArrayOutputStream bo = new ByteArrayOutputStream();  
	        ObjectOutputStream oo = new ObjectOutputStream(bo);  
	        oo.writeObject(obj);  
	  
	        bytes = bo.toByteArray();  
	        printHexString(bytes);
	        bo.close();  
	        oo.close();  
	    } catch (Exception e) {  
	        System.out.println("translation" + e.getMessage());  
	        e.printStackTrace();  
	    }  
	    return bytes;  
	}
	//将指定byte数组以16进制的形式打印到控制台   
	public static void printHexString( byte[] b) throws IOException {  
	   StringBuffer sb = new StringBuffer();
	   for (int i = 0; i < b.length; i++) {    
	     String hex = Integer.toHexString(b[i] & 0xFF);    
	     if (hex.length() == 1) {    
	       hex = '0' + hex ;    
	     }
	     sb.append(hex.toUpperCase());
	   }    
	   formatPrint(sb.toString());
	} 
	public static void formatPrint(String str){
		char[] chars = str.toCharArray();
		String pts = "";
		for(int i =0;i<chars.length;i++){
			pts +=chars[i];
			if( i>0 && (i+1)%4 == 0 ){
				pts += " ";
			}
			if( (i+1)%32 ==0 ){
				pts += '\n';
			}
		}
		System.out.println(pts);
	}
	 public static String toHex(int num){
	        char[] chs = new char[8];//定义容器，存储的是字符，长度为8.一个整数最多8个16进制数
	        int index = chs.length-1;
	        for(int i = 0;i<8;i++) {
	            int temp = num & 15;
	             
	            if(temp > 9){
	                chs[index] = ((char)(temp-10+'A'));
	            }else {
	                chs[index] = ((char)(temp+'0'));
	            }
	             
	            index--;
	            num = num >>> 4;          
	        }
	        return toString(chs);
	 }
	//将数组转为字符串
    public static String toString(char[] arr){
        String temp = "";
        for(int i = 0;i<arr.length;i++){
            temp = temp + arr[i];
        }
        return temp;
    }
    
    public static void printObjToHex(Object info) throws IllegalArgumentException, IllegalAccessException{
    	Class<?> cls = info.getClass();
    	
    	Field[] fields = cls.getDeclaredFields();
    	StringBuffer sb = new StringBuffer();
    	for(Field f:fields){
    		f.setAccessible(true);
    		String fieldType = f.getType().getSimpleName();
    		String fieldName = f.getName();
    		Object fieldValue ;
    		if(Modifier.isStatic(f.getModifiers())){
    			fieldValue = f.get(info.getClass().getSimpleName());
    		}else{
    			fieldValue = getFieldValueByName(fieldName,info);
    		}
    		if(fieldValue == null) continue;
    		if("long".equals(fieldType)){
    			sb.append(fieldName+":"+toBinary(Long.parseLong(fieldValue.toString()),16)+'\n');
    		} else if("int".equals(fieldType)){
    			sb.append(fieldName+":"+Integer.toHexString(Integer.parseInt(fieldValue.toString()))+'\n');
    		} else if("float".equals(fieldType)){
				sb.append(fieldName+":"+Float.toHexString(Float.parseFloat(fieldValue.toString()))+'\n');
    		} else if("byte[]".equals(fieldType)){
    			sb.append(fieldName+":"+bytes2hex03((byte[])fieldValue)+'\n');
    		} else if("char[]".equals(fieldType)){
    			sb.append(fieldName+":"+chars2hex((char[])fieldValue)+'\n');
    		} else if("XTIME_S".equals(fieldType)){
    			printObjToHex(fieldValue);
    		}
    	}
    	System.out.println(sb.toString());
    }
    
    public static Object getFieldValueByName(String fieldName, Object o) {  
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(o, new Object[] {});    
            return value;    
        } catch (Exception e) {    
            return null;    
        }    
    }  
    private static String toBinary(long n, int target) {
        String s = "";
        while (n != 0) {
            s = n % target + s;
            n = n / target;
        }
        return s;
    }
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
    	return DatatypeConverter.printHexBinary(bytes);
       // return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
    
    public static String bytes2hex03(byte[] bytes)  
    {  
        final String HEX = "0123456789abcdef";  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        for (byte b : bytes)  
        {  
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt(b & 0x0f));  
        }  
  
        return sb.toString();  
    }  
    
    public static String chars2hex(char[] chars){
    	StringBuilder sb = new StringBuilder(chars.length * 2);  
    	for(char c:chars){
    		System.out.println(c);
    		System.out.println(Integer.toHexString((int)c));
    		sb.append(Integer.toHexString((int)c));
    	}
    	return sb.toString();
    }

    public static String byte2String(byte[] bts){
    	StringBuffer str = new StringBuffer();
		for(int i=0;i< bts.length; i++){  
			char c = (char) bts[i];
			str.append(c);
         }
		System.out.println(str.toString());
		return str.toString();
    }
    public static String hex2String() throws IOException{
    	StringBuffer sb = new StringBuffer();
    	File f = new File("e:\\queryInfo.txt");
    	FileReader reader = new FileReader(f);
    	BufferedReader br = new BufferedReader(reader);
    	try {
    		
        	String line ="";
        	while( (line = br.readLine()) != null){
        		String[] splits = line.split(" ");
        		byte[] bts = new byte[splits.length];
        		int i =0;
        		for(String s:splits){
        			if(Strings.isNullOrEmpty(s)) continue;
        			System.out.println(0xff & Integer.parseInt(s, 16));
        			bts[i] = (byte)(0xff & Integer.parseInt(s, 16));i++;
        		}
        		sb.append(new String(bts, "utf-8"));
        	}
		} finally {
			br.close();
		}
    	
    	System.out.println(sb.toString());
    	return sb.toString();
    }
    public static void main(String[] args) throws IOException {
    	hex2String();
	}
}
