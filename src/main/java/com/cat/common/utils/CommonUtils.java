package com.cat.common.utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.cat.common.Const;


/**
 * 常量操作工具类
 * 
 * @author JINJF
 *
 */
public class CommonUtils {
    private static final String REGEX_HANJI = "[^\\x00-\\xff]";
    private static boolean enctyptName = false;

    public static void setEnctyptName(boolean enctyptName) {
        CommonUtils.enctyptName = enctyptName;
    }

    /**
     * 获取字符长度，中文为两个字符，英文为一个字符
     * 
     * @param str
     * @return
     */
    public static int getLength(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        return str.replaceAll("[^\\x00-\\xff]", "**").length();
    }

    /**
     * 判断字符串为空或者大于最大长度 适用场合：字符串不能为空，且不能超过最大长度
     *
     * @param str 字符串值
     * @param maxLen 最大长度
     * @return
     */
    public static boolean isEmptyOrThanMaxLen(String str, int maxLen) {
        return (StringUtils.isEmpty(str) || (getLength(str) > maxLen));
    }

    /**
     * 判断字符串不为空且大于最大长度 适用场合：字符串可以为空，但不能超过最大长度
     * 
     * @param str 字符串值
     * @param maxLen 最大长度
     * @return true/false
     */
    public static boolean isNotEmptyAndThanMaxLen(String str, int maxLen) {
        return (StringUtils.isNotEmpty(str) && (getLength(str) > maxLen));
    }

    /**
     * 判断数组中的内容是否全部为空
     * 
     * @param cells
     * @return
     */
    public static boolean isAllEmpty(String[] cells) {
        if (!ArrayUtils.isEmpty(cells)) {
            int emptyCnt = 0;
            for (String cell : cells) {
                if (StringUtils.isEmpty(cell)) {
                    emptyCnt++;
                }
            }
            if (emptyCnt == cells.length) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * 创建UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return StringUtils.replaceChars(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 生成6位随机字符串
     * 
     * @return
     */
    public static String getRandomChar() {
        return getRandomChar(6);
    }

    /**
     * 生成随机字符串
     * 
     * @param length
     * @return
     */
    public static String getRandomChar(int length) {
        StringBuffer buffer = new StringBuffer("abcde2345678gfynmnpwx");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 格式化大小
     * 
     * @param bytes
     * @return
     */
    public static String formatBytes(String bytes) {
        long lbytes = 0L;
        if (StringUtils.isNotEmpty(bytes)) {
            lbytes = Long.parseLong(bytes);
        }

        return formatBytes(lbytes);
    }

    /**
     * 格式化大小代码
     * 
     * @param bytes
     * @return
     */
    public static String formatBytes(long bytes) {
        if (bytes == 0) {
            return Const.NONE_CHAR;
        }
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = String.valueOf(("KMGTPE").charAt(exp - 1));
        return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * 判断是否为Email
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (!StringUtils.isEmpty(email) && !email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            return false;
        }

        return true;
    }

    /**
     * 验证手机号码
     * 
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        //return mobile.matches("^1[3|4|5|6|7|8|9]\\d{9}$");
        return mobile.matches("^\\d{11,13}$");
    }

    /**
     * 校验数字
     * 
     * @param s
     * @return
     */
    public static boolean isSituationsNumer(String s) {
        String regx = "^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";
        Pattern pattern = Pattern.compile(regx);
        return pattern.matcher(s).matches();
    }

    /**
     * 校验数字、字母、汉字
     * 
     * @param s
     * @return
     */
    public static boolean isNumerLetterChineseChar(String s) {
        String regx = "[0-9a-zA-Z\u4e00-\u9fa5]*";
        Pattern pattern = Pattern.compile(regx);
        return pattern.matcher(s).matches();
    }

    /**
     * 自动转换为多行处理
     * 
     * @param s
     * @return
     */
    public static String changeMultiline(String s) {
        if (StringUtils.isNotEmpty(s)) {
            return StringUtils.replace(StringEscapeUtils.escapeHtml(s), "\n", "<br>");
        }
        return s;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * 
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (StringUtils.isEmpty(version1)) {
            return -1;
        }
        if (StringUtils.isEmpty(version2)) {
            return 1;
        }
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 截取字符串，按字节
     * 
     * @param str
     * @param lengthb
     * @return
     */
    public static String substrb(String str, int lengthb) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        if (getLengthb(str) <= lengthb) {
            return str;
        }
        Pattern pattern = Pattern.compile(REGEX_HANJI);
        StringBuilder sb = new StringBuilder();
        int len = 0;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            String s = String.valueOf(c);
            if ((pattern.matcher(s)).matches()) {
                len = len + 2;
            } else {
                len++;
            }
            if (len > lengthb) {
                break;
            }
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 获取本机Mac
     * 
     * @return
     */
    public static List<String> getLocalMac() {
        List<String> maces = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                byte[] mac = ni.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(toHex(b));
                        sb.append("-");
                    }
                    if (sb.length() > 1)
                        sb.deleteCharAt(sb.length() - 1);
                    if (sb.length() > 0) {
                        maces.add(StringUtils.upperCase(sb.toString()));
                    }
                }
            }
        } catch (SocketException e) {
        }
        return maces;
    }

    private static StringBuffer toHex(byte b) {
        byte factor = 16;
        int v = b & 0xff;
        byte high = (byte) (v / factor);
        byte low = (byte) (v % factor);
        StringBuffer buf = new StringBuffer();
        buf.append(toHexLow(high)).append(toHexLow(low));
        return buf;
    }

    private static char toHexLow(byte b) {
        if (b > 16 || b < 0) {
            throw new IllegalArgumentException("inpt parameter should less than 16 and greater than 0");
        }
        if (b < 10) {
            return (char) ('0' + (char) b);
        } else {
            return (char) ('A' + (b - 10));
        }
    }

    /**
     * 获取字符串长度（字节）
     * 
     * @param str
     * @return
     */
    public static int getLengthb(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        return str.replaceAll(REGEX_HANJI, "**").length();
    }

    /**
     * 替换用户名
     * 
     * @param str
     * @return
     */
    public static String changeName(String str) {
        if (enctyptName) {
            if (StringUtils.isBlank(str)) {
                return "-";
            } else {
                int size = StringUtils.length(str);
                int paddingSize = 0;
                String first = StringUtils.left(str, 1);
                String last = StringUtils.right(str, 1);
                if (size <= 2) {
                    paddingSize = 1;
                    last = StringUtils.EMPTY;
                } else if (size <= 5) {
                    paddingSize = size - 2;
                } else {
                    paddingSize = 3;
                }
                return first + StringUtils.repeat("*", paddingSize) + last;
            }
        } else {
            return StringUtils.defaultIfEmpty(str, "-");
        }
    }
}
