package com.zsyc.framework.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 扫描项目里的所有配置项
 * Created by lcs on 2020/11/26.
 */
@Slf4j
public class SpringConfigureMetadataToDocs {

    private static final String BASE_PACKAGE_NAME = "com.zsyc";
    /**
     * resource 文件读取
     * @param resource
     * @return
     */
    private static String readJson(Resource resource){
        try {
            String json = new BufferedReader(new InputStreamReader(resource.getInputStream())).lines().collect(Collectors.joining("\n"));
            log.debug("{}\n{}", resource.getFilename(), json);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有配置项
     * @return
     * @throws IOException
     */
    public static List<Property> scanProperty() throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:**/*.json");
        return Arrays.stream(resources)
                .map(SpringConfigureMetadataToDocs::readJson)
                .filter(Objects::nonNull)
                .filter(data -> data.trim().startsWith("{"))
                .map(JSONObject::parseObject)
                .filter(json -> json.containsKey("properties"))
                .map(json -> json.getJSONArray("properties"))
                .reduce(new JSONArray(), (a, b) -> {
                    a.addAll(b);
                    return a;
                }).stream()
                .map(item -> (JSONObject) item)
                .map(json -> json.toJavaObject(Property.class))
                .filter(property -> {
                    String sourceType = property.getSourceType();
                    return sourceType != null && sourceType.startsWith(BASE_PACKAGE_NAME);
                })
                .sorted(Comparator.comparing(Property::getSourceType))
                .collect(Collectors.toList());
    }

    public static String toMD(List<String> ignoreKeys) throws IOException {
        return toMD(property -> !ignoreKeys.contains(property.getName()));
    }
    public static String toMD(Predicate<Property> predicate) throws IOException {
        String header = "| Key  | Default Value | Description | Type | Source Type |\n| :--- | :------------ | :---------- | :--- | :---------- |\n";
        String body = scanProperty().stream()
                .filter(predicate)
                .map(property -> String.format("| %s | %s  | %s  | %s | %s  |", property.getName(), property.getDefaultValue(), property.getDescription(), property.getType(), property.getSourceType()))
                .collect(Collectors.joining("\n"));
        return String.format("%s%s", header,body );
    }

    /**
     * 配置项
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Property{
        private String sourceType;
        private Object defaultValue;
        private String name;
        private String description;
        private String type;
        public Object getDefaultValue(){
            if(this.defaultValue == null) return "";
            return this.defaultValue;
        }
    }
}
