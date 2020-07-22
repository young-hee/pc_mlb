package com.plgrim.ncp.framework.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * utility class for java reflection
 */
@Slf4j
public class ReflectionUtil {
    public static Field[] getDeclaredFieldsAll(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        Class<?> currentClass = type;

        while (currentClass != Object.class) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }

        return fields.toArray(new Field[]{});
    }

    public static Field getDeclaredField(Class<?> type, String fieldName) {
        Class<?> currentType = type;
        Field resultField = null;

        while (!Object.class.equals(currentType)) {
            try {
                resultField = currentType.getDeclaredField(fieldName);
                break;
            } catch (Exception e) {
                currentType = currentType.getSuperclass();
            }
        }

        return resultField;
    }

    public static Field getIterableField(Class<?> type, String propName) {
        Class<?> currentType = type;
        Field resultField = null;

        while (!Object.class.equals(currentType)) {
            try {
                Field[] fields = currentType.getDeclaredFields();
                Field field = getTargetField(fields, propName);
                if (field != null) {
                    resultField = field;
                    resultField.setAccessible(true);
                    break;
                } else {
                    currentType = currentType.getSuperclass();
                }
            } catch (Exception e) {
                resultField = null;
            }
        }
        return resultField;
    }

    private static Field getTargetField(Field[] fields, String fieldName) {
        for (Field field : fields) {
            Class<?> type = field.getType();
            String currentFieldName = field.getName();

            if (!currentFieldName.startsWith(fieldName) && !currentFieldName.endsWith(fieldName)) {
                continue;
            }

            if (!List.class.isAssignableFrom(type) &&
                    !Object[].class.isAssignableFrom(type)) {
                continue;
            }

            return field;
        }

        return null;
    }

    /**
     * Entity Copy
     *
     * @param src
     * @param desc
     */
    public static void entityCopy(Object src, Object desc) {
        copyFields(src, desc, src.getClass());
    }

    /**
     * Field By Field Copy Value
     *
     * @param src
     * @param desc
     * @param clazz
     */
    private static void copyFields(Object src, Object desc, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            copyFieldValue(src, desc, f);
        }

        clazz = clazz.getSuperclass();
        if (clazz != null) {
            copyFields(src, desc, clazz);
        }
    }

    /**
     * Field Copy Value
     *
     * @param src
     * @param desc
     * @param f
     */
    private static void copyFieldValue(Object src, Object desc, Field f) {
        try {
            Object value = f.get(src);
            // f.set(desc, value);
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(f.getName(), desc.getClass());
            if (objPropertyDescriptor != null && objPropertyDescriptor.getWriteMethod() != null) {
                objPropertyDescriptor.getWriteMethod().invoke(desc, value);
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static Object getFieldValue(Object obj, Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        Object result = null;
        try {
            result = field.get(obj);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    public static void setFieldValue(Object obj, Field field, Object value) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        try {
            field.set(obj, value);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }
}