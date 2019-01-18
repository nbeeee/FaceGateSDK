package org.lebo.facegate.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 时间类机构体
 *
 */
public class XTIME_S extends Structure {
    public XTIME_S(){
        super();
    }
    public XTIME_S(Pointer p){
        super(p);
    }
    public short year;
    public byte month;
    public byte day;
    public byte week;
    public byte hour;
    public byte minute;
    public byte second;


    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("year","month","day","week","hour","minute","second");
    }
    public static class ByReference extends XTIME_S implements Structure.ByReference{}

    public static class ByValue extends XTIME_S implements Structure.ByValue{}
}