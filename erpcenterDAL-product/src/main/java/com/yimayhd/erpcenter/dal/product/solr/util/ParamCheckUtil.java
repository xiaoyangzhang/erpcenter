package com.yimayhd.erpcenter.dal.product.solr.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ParamCheckUtil {

    
    /**
     * 
     * 功能描述: <br>
     * 〈检查list是否为空〉
     *
     * @param list
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> boolean checkListNull(List<T> list) {
        if (null == list || list.isEmpty() || 0 == list.size()) {
            return true;
        }
        return false;
    }

    
    /**
     * 
     * 功能描述: <br>
     * 〈检查字符串是否为空〉
     *
     * @param str
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String checkString(String str){
        return StringUtils.isBlank(str) ? "" : str;
    }
    

}
