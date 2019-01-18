package org.lebo.facegate.structure;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 读取搜索数据
 *
 */
public class XSEARCH_READ_S extends Structure {
    public XSEARCH_READ_S(){
        super();
    }
    public XSEARCH_READ_S(Pointer p){
        super(p);
    }

    public int dwSearchType;		//SEARCH_TYPE_E

    public int dwSessionID;		    //搜索生成的ID

    public int dwBeginNo;			//开始编号
    public int dwReqCount;			//读多少条

    // 备用
    public byte[] szRes = new byte[20];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwSearchType","dwSessionID","dwBeginNo","dwReqCount","szRes");
    }
    public static class ByReference extends XSEARCH_READ_S implements Structure.ByReference{}

    public static class ByValue extends XSEARCH_READ_S implements Structure.ByValue{}
}
