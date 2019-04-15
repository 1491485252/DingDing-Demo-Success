package com.cat.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 集合操作工具类
 * 
 * @author JINJF
 *
 */
public class CollectionUtils {

    /**
     * 快速创建Set集合用
     * 
     * @param elements
     * @return
     */
    public static <E> Set<E> asSet(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptySet();
        }
        LinkedHashSet<E> set = new LinkedHashSet<E>(elements.length * 4 / 3 + 1);
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * 快速创建List用
     * 
     * @param elements
     * @return
     */
    public static <E> List<E> asList(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }
        List<E> list = new ArrayList<E>(elements.length * 4 / 3 + 1);
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * Array转化为List
     * 
     * @param arr
     * @return
     */
    public static <E> List<E> toList(E[] arr) {
        if (isEmpty(arr)) {
            return Collections.emptyList();
        }

        List<E> list = Arrays.asList(arr);
        return list;
    }

    /**
     * 判断集合是否为空
     * 
     * @param c
     * @return true/false
     */
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    /**
     * 判断集合是否不为空
     * 
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    /**
     * 取得集合的长度
     * 
     * @param c
     * @return
     */
    public static int size(Collection<?> c) {
        return c != null ? c.size() : 0;
    }

    /**
     * 判断数组是否为空
     * 
     * @param arr
     * @return
     */
    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length <= 0;
    }

    /**
     * 判断数组是否不为空
     * 
     * @param arr
     * @return
     */
    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 删除集合中指定的数据
     * 
     * @param from
     * @param target
     * @return
     */
    public static List<String> remove(List<String> from, List<String> target) {
        if (isNotEmpty(from) && isNotEmpty(target)) {
            List<String> temp = new ArrayList<String>(from);
            temp.removeAll(target);
            return temp;
        } else {
            if (isNotEmpty(from)) {
                return from;
            } else {
                return new ArrayList<String>();
            }
        }
    }

    /**
     * 获取集合中指定索引的值
     * 
     * @param list
     * @param i
     * @return
     */
    public static String get(List<String> list, int i) {
        if (isNotEmpty(list)) {
            if (list.size() > i) {
                return list.get(i);
            }
        }

        return StringUtils.EMPTY;
    }

    /**
     * 获取第一个非空值
     * 
     * @param list
     * @return
     */
    public static String getFirstNotEmpty(List<String> list) {
        if (isNotEmpty(list)) {
            for (String item : list) {
                if (StringUtils.isNotEmpty(item)) {
                    return item;
                }
            }
        }

        return StringUtils.EMPTY;
    }

}
