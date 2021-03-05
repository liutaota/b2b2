package com.zsyc.framework.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lcs on 2019-05-18.
 */
public class EnumScan {

    public static Map<String, Map<String, List<Map<String, String>>>> scanAllEnum(String basePackage, Class interfaceClass) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + basePackage.replaceAll("\\.", "/")
                + "/**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourcePatternResolver);

        Map<String, List<Map<String, String>>> data = Arrays.stream(resources).map(resource -> {
            try {
                String name = metaReader.getMetadataReader(resource).getClassMetadata().getClassName();
                return ClassUtils.resolveClassName(name, System.class.getClassLoader());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(item -> item != null)
                .filter(clazz -> interfaceClass.isAssignableFrom(clazz) && !clazz.isAssignableFrom(interfaceClass))
                .collect(Collectors.toMap(clazz -> {
                    String[] name = clazz.getName().split("\\.");
                    return name[name.length - 1].replace("$", ".");
                }, clazz -> {
                    try {
                        return dataValuses((Class<? extends Enum>) clazz);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }));

        return formatEnumData(data);
    }

    private static List<Map<String, String>> dataValuses(Class<? extends Enum> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Enum[] valuses = (Enum[]) clazz.getMethod("values").invoke(null);
        return Arrays.stream(valuses).map(val -> {
            Map<String, String> map = new HashMap<>();

            try {
                map.put("val", (String) clazz.getMethod("val").invoke(val));
                map.put("desc", (String) clazz.getMethod("desc").invoke(val));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return map;
        }).collect(Collectors.toList());
    }

    private static Map<String, Map<String, List<Map<String, String>>>> formatEnumData(Map<String, List<Map<String, String>>> data) {
        Map<String, Map<String, List<Map<String, String>>>> enumData = new HashMap<>();
        data.forEach((key, value) -> {
            String[] keys = key.split("\\.");
            String className = keys[0].substring(0, 1).toLowerCase() + keys[0].substring(1);
            Map<String, List<Map<String, String>>> data1 = enumData.get(className);
            if (data1 == null) {
                data1 = new HashMap<>();
                enumData.put(className, data1);
            }
            data1.put(keys[1].substring(1, 2).toLowerCase() + keys[1].substring(2), value);

        });
        return enumData;
    }

}
