/*
 * FileName: ParamCheckUtil.java
 * Author:   liubb
 * Date:     2016年5月16日 上午11:28:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.erpcenter.dal.product.solr.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈判断是否为空〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
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
     * 〈判断数组〉
     *
     * @param array
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkArrayNull(Object[] array) {
        if (null == array || 0 == array.length) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈判断map〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <K, V> boolean checkMapNull(Map<K, V> map) {
        if (null == map || 0 == map.size()) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈判断string〉
     *
     * @param str
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String checkString(String str) {
        return StringUtils.isBlank(str) ? "" : str;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈判断距离参数〉
     *
     * @param latitude
     * @param longitude
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkDistanceParmError(String[] longitude, String[] latitude) {
        if (ParamCheckUtil.checkArrayNull(latitude) || ParamCheckUtil.checkArrayNull(longitude)) {
            return true;
        }
        if (0 == ParmUtil.objectToDouble(latitude[0]) && 0 == ParmUtil.objectToDouble(longitude[0])) {
            return true;
        }
        return false;
    }
}
