package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 日期类机构体
 *
 */
public class XDATE_S extends Structure {
    public XDATE_S(){
        super();
    }
    public XDATE_S(Pointer p){
        super(p);
    }
    public short year;
    public byte month;
    public byte day;
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("year","month","day");
    }
    public static class ByReference extends XDATE_S implements Structure.ByReference{}

    public static class ByValue extends XDATE_S implements Structure.ByValue{}
}
