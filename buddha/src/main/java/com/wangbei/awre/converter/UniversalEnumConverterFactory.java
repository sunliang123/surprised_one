package com.wangbei.awre.converter;

import com.wangbei.util.CommonalityEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
* @author yuyidi 2017-06-27 10:18:51
* @class com.wangbei.awre.converter.UniversalEnumConverterFactory
* @description 通用的枚举转换工厂类
*/
public class UniversalEnumConverterFactory implements ConverterFactory<String, CommonalityEnum> {

    public static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends CommonalityEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);
        if (result == null) {
            result = new IntegerStrToEnum<>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

    class IntegerStrToEnum<T extends CommonalityEnum> implements Converter<String, T> {
        private final Class<T> enumType;
        private Map<Integer, T> enumMap = new HashMap<>();

        public IntegerStrToEnum(Class<T> enumType) {
            this.enumType = enumType;
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getIndex(), e);
            }
        }


        @Override
        public T convert(String source) {
            T result = enumMap.get(Integer.valueOf(source));
            if (result == null) {
                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }
    }
}
