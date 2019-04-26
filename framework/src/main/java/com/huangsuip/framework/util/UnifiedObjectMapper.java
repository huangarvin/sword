package com.huangsuip.framework.util;

import java.text.SimpleDateFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author HuangSuip
 */
public class UnifiedObjectMapper extends ObjectMapper {

    protected static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public UnifiedObjectMapper() {
        super();
        // 长整形转换成字符串，避免使用JS读取精度丢失问题
        SimpleModule sm = new SimpleModule();
        sm.addSerializer(Long.class, ToStringSerializer.instance);
        sm.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.registerModule(sm);
        //允许单引号
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, Boolean.TRUE)
            //未知的属性是否失败
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            //.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true)
            .setDateFormat(new SimpleDateFormat(DATE_FORMAT));
    }
}
