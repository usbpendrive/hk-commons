package com.hk.commons.util;

/**
 * Boolean工具类
 *
 * @author: kevin
 * @date 2017年9月12日上午10:05:40
 */
public abstract class BooleanUtils extends org.apache.commons.lang3.BooleanUtils {

    public static final String TRUE_CHINEASE = "是";

    public static final String FALSE_CHINEASE = "否";

    /**
     * <pre>
     * String 转换为 boolean
     * BooleanUtils.toBoolean(null); false
     * BooleanUtils.toBoolean(""); false
     *
     * BooleanUtils.toBoolean("yes"); true
     * BooleanUtils.toBoolean("YES"); true
     * BooleanUtils.toBoolean("是"); true
     * BooleanUtils.toBoolean("是 "); true
     * BooleanUtils.toBoolean(" 是"); true
     * BooleanUtils.toBoolean("true"); true
     * BooleanUtils.toBoolean("true "); true
     * BooleanUtils.toBoolean(" true"); true
     * BooleanUtils.toBoolean("t rue"); false
     * BooleanUtils.toBoolean("TRUE"); true
     * BooleanUtils.toBoolean("y"); true
     * BooleanUtils.toBoolean("Y"); true
     * BooleanUtils.toBoolean("on"); true
     * BooleanUtils.toBoolean(" on"); true
     * BooleanUtils.toBoolean("on "); true
     * BooleanUtils.toBoolean("o n"); false
     * BooleanUtils.toBoolean("ON"); true
     * BooleanUtils.toBoolean("1"); true
     *
     * 其它参数字符串 返回false
     * </pre>
     *
     * @param args 一个字符串参数
     * @return
     */
    public static boolean toBoolean(String args) {
        if (StringUtils.isBlank(args)) {
            return false;
        }
        final String trimValue = args.trim();
        return StringUtils.equalsIgnoreCase("yes", trimValue)
                || StringUtils.equals(TRUE_CHINEASE, trimValue)
                || StringUtils.equalsIgnoreCase("true", trimValue)
                || StringUtils.equalsIgnoreCase("y", trimValue)
                || StringUtils.equalsIgnoreCase("on", trimValue)
                || StringUtils.equals("1", trimValue);
    }

    /**
     * Boolean转中文
     *
     * @param bool
     * @return
     */
    public static String toBooleanChinese(boolean bool) {
        return bool ? TRUE_CHINEASE : FALSE_CHINEASE;
    }

}
