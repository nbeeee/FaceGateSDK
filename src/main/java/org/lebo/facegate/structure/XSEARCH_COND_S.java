package org.lebo.facegate.structure;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 搜索人员数据
 *
 */
public class XSEARCH_COND_S extends Structure {
    public XSEARCH_COND_S(){
        super();
    }
    public XSEARCH_COND_S(Pointer p){
        super(p);
    }

    public int dwSearchType;//4

    public XTIME_S stBgnTime = new  XTIME_S.ByReference();//开始时间 8
    public XTIME_S stEndTime = new  XTIME_S.ByReference();//结束时间 8


    public int		bTimeDown;			//0: 升序  1: 降序 4

    //public SerchCondValue dwCondValue = new SerchCondValue.ByReference();

    public SearchTypeUnion dwType = new SearchTypeUnion.ByReference();



    //名单库 及 控制记录搜索过滤条件
    public byte[]		szName = new byte[32]; //32
    public int			dwGender;			//性别    0: 男   1: 女  2: 所有 4


    public int			dwAgeMin;			//年龄范围: 最小	0 该条件无效 4
    public int			dwAgeMax;			//			最大	0 该条件无效 4

    public int			dwMjCardNo;			//门禁卡号: 0 该条件无效 4
    public byte[]		szIDCard = new byte[32];		//身份证号: 0 该条件无效 32

    public byte[]		szTel = new byte[32];			//电话
    public byte[]		szAaddr = new byte[64];		//地址

    public int 			dwChn;//Jurisdiction 权限，不同的接入通道,人脸分析服务器版本

    public float			MaxSimilarity;//以图搜图使用，最大相似度
    public int				picLen;		//以图搜图使用，图片的长度
    // 备用
    public byte[] szRes = new byte[40];

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("dwSearchType","stBgnTime","stEndTime","bTimeDown","dwType","szName","dwGender",
                "dwAgeMin","dwAgeMax","dwMjCardNo","szIDCard","szTel","szAaddr","dwChn","MaxSimilarity","picLen","szRes");
    };

    public static class ByReference extends XSEARCH_COND_S implements Structure.ByReference{}

    public static class ByValue extends XSEARCH_COND_S implements Structure.ByValue{}
}