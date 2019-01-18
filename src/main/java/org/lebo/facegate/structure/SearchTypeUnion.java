package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

import java.util.Arrays;
import java.util.List;

public class SearchTypeUnion extends Union {
    public SearchTypeUnion(){
        super();
    }
    public SearchTypeUnion(Pointer p){
        super(p);
    }
    public int		dwPersonType;		//名单管理: 0: 白名单   1: 黑名单 2: 所有 4
    public int		dwOpenDoor;			//控制记录: 0: 所有  1: 开门      2: 拒绝 4
    public int		dwLogType;			//日志:		0: 全部	 1: 参数设置  2: 添加名单 3: 删除名单 4

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwPersonType","dwOpenDoor","dwLogType");
    }

    public static class ByReference extends SearchTypeUnion implements Structure.ByReference{}

    public static class ByValue extends SearchTypeUnion implements Structure.ByValue{}
}