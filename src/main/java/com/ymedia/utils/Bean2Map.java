package com.ymedia.utils;


import org.springframework.cglib.core.ReflectUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Bean2Map {

    public static <T> T toBean(Class<T> clazz, Map map) throws Exception {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (InvocationTargetException e) {
                        System.out.println("字段映射失败");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("实例化 JavaBean 失败");

        }
        return (T) obj;
    }


    public static Map toMap(Object bean) throws Exception {
            Class<? extends Object> clazz = bean.getClass();
            Map<Object, Object> returnMap = new HashMap<Object, Object>();
            BeanInfo beanInfo = null;
            try {
                beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = descriptor.getReadMethod();
                        Object result = null;
                        result = readMethod.invoke(bean, new Object[0]);
                        if (null != propertyName) {
                            propertyName = propertyName.toString();
                        }
                        if (null != result) {
                            result = result.toString();
                        }
                        returnMap.put(propertyName, result);
                    }
                }
                return returnMap;
            }catch (Exception e){
                e.printStackTrace();
            }
        return returnMap;
    }
}
