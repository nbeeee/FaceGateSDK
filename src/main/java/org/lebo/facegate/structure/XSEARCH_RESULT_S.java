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
public class XSEARCH_RESULT_S extends Structure {
    public XSEARCH_RESULT_S(){
        super();
    }
    public XSEARCH_RESULT_S(Pointer p){
        super(p);
    }

    public int dwSearchType;		//SEARCH_TYPE_E

    public int dwSessionID;		//该搜索生成的ID
    public int dwTotalCount;		//搜索到的记录总个数

    // 备用
    public byte[] szRes = new byte[20];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwSearchType","dwSessionID","dwTotalCount","szRes");
    }
    public static class ByReference extends XSEARCH_RESULT_S implements Structure.ByReference{}

    public static class ByValue extends XSEARCH_RESULT_S implements Structure.ByValue{}
}
