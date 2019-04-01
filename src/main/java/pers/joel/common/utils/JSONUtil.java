package pers.joel.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Object 与 JSON 相互转换 <br/>
 * List &lt Object &gt 与 JSON 相互转换 <br/>
 * Object 只取部分 properties 转换成 MAP <br/>
 * Object 只取部分 properties 转换成 JSON <br/>
 * List &lt Object &gt 只取部分 properties 转换成 List &lt Map &gt <br/>
 * List &lt Object &gt 只取部分 properties 转换成 JSON <br/>
 *
 * @author <a href="mailto:javaworld@qq.com">Woody</a> on 2014-10-22
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public final class JSONUtil {
    private transient static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper uiObjectMapper = new ObjectMapper();

    static {
        uiObjectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        uiObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        uiObjectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        uiObjectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        uiObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Date.class, new MultiDateFormatDeSerializer());
        uiObjectMapper.registerModule(simpleModule);
    }

    private static final ObjectMapper cacheObjectMapper = new ObjectMapper();

    static {
        cacheObjectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        cacheObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        cacheObjectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        cacheObjectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        cacheObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        cacheObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        cacheObjectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    /**
     * 写 Cache 和别处不一样
     */
    public static String toCacheJson(Object object) {
        if (object == null)
            return "";

        try {
            logger.trace("class={}, object={}", object.getClass().getName(), object.toString());
            return cacheObjectMapper.writer().writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("toJson caught IOException: ", e);
        }

        return null;
    }

    public static Object readCacheJson(String content) {
        if (content == null || content.trim().equals(""))
            return null;

        content = content.replaceAll("Collections\\$UnmodifiableMap", "HashMap");
        content = content.replaceAll("Collections\\$UnmodifiableSet", "HashSet");

        try {
            return cacheObjectMapper.readerFor(Object.class).readValue(content);
        } catch (IOException e) {
            if (!(e instanceof JsonMappingException) || e.getMessage().indexOf("shiroSavedRequest") < 0)
                logger.warn("readJson caught IOException: ", e);
        }

        return null;
    }

    public static <T> T readCacheJson(String content, Class<T> valueType) {
        if (content == null || content.trim().equals(""))
            return null;

        content = content.replaceAll("Collections\\$UnmodifiableMap", "HashMap");
        content = content.replaceAll("Collections\\$UnmodifiableSet", "HashSet");

        try {
            return cacheObjectMapper.readerFor(valueType).readValue(content);
        } catch (IOException e) {
            if (!(e instanceof JsonMappingException) || e.getMessage().indexOf("shiroSavedRequest") < 0)
                logger.warn("readJson caught IOException: ", e);
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     * 普通对象转换：toJson(UcUser)
     * List转换：toJson(List)
     * Map转换:toJson(Map)
     * 不管什么类型，都可以直接传入这个方法
     *
     * @param object JavaBean对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        return toJson(object, null);
    }

    /**
     * 天朝的日期格式很多情况下和帝国主义的不一样，所以带上日期格式进行序列化是必要的
     * toJson(你的对象, new SimpleDateFormat("yyyy年MM月dd"))
     */
    public static String toJson(Object object, DateFormat dateFormat) {
        if (object == null)
            return "";

        if (dateFormat != null) {
            SimpleModule module = new SimpleModule("", Version.unknownVersion());
            module.addSerializer(Date.class, new MyDateSerializer(dateFormat));
            module.addSerializer(java.sql.Date.class, new MyDate2Serializer(dateFormat));
            module.addSerializer(java.sql.Time.class, new MyTimeSerializer(dateFormat));
            module.addSerializer(Timestamp.class, new MyTimestampSerializer(dateFormat));
            uiObjectMapper.registerModule(module);
        }

        try {
            return uiObjectMapper.writer(dateFormat).writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("toJson caught IOException: ", e);
        }

        return null;
    }

    /**
     * 使用泛型方法，把 json 字符串转换为相应的 JavaBean 对象。
     * 转换为普通 JavaBean：readJson(json,UCUser.class)
     * 转换为 List:readJson(json,List.class) 。
     * 但是如果我们想把json转换为特定类型的 List ，比如 List<Student> ，就不能直接进行转换。
     * 因为 readJson(json,List.class) 返回的其实是 List<Map> 类型，你不能指定 readJson() 的第二个参数是 List<UcUser>.class ，所以不能直接转换。
     * 我们可以把 readJson()的第二个参数传递为 UcUser[].class 。然后使用 Arrays.asList() 方法把得到的数组转换为特定类型的 List 。
     * 或者用下面的重载方法 readJson(json,List.class,UcUser.class)
     * 转换为 Map：readJson(json,Map.class)
     * 我们使用泛型，得到的也是泛型
     *
     * @param content   要转换的JavaBean类型
     * @param valueType 原始json字符串数据
     * @return JavaBean对象
     */
    public static <T> T readJson(String content, Class<T> valueType) {
        if (content == null || content.trim().equals(""))
            return null;
        return readJson(content, valueType, null);
    }

    private static void registerDf(String dateFormatStr) {
        if (!StringUtils.isEmpty(dateFormatStr)) {
            DateFormat df = new SimpleDateFormat(dateFormatStr);
            SimpleModule module = new SimpleModule("", Version.unknownVersion());
            module.addDeserializer(Date.class, new MyDateDeSerializer(df));
            uiObjectMapper.registerModule(module);
            uiObjectMapper.getDeserializationConfig().with(df);
        }
    }

    public static <T> T readJson(String content, Class<T> valueType, String dateFormatStr) {
        if (content == null || content.trim().equals(""))
            return null;

        registerDf(dateFormatStr);

        try {
            return uiObjectMapper.readerFor(valueType).readValue(content);
        } catch (IOException e) {
            logger.warn("readJson caught IOException: ", e);
        }
        return null;
    }

    public static <T> T readJson2(String content, JavaType javaType) {
        return readJson2(content, javaType, null);
    }

    /**
     * 天朝的日期格式很多情况下和帝国主义的不一样，所以带上日期格式进行序列化是必要的
     * readJson(content, UcUser.class, "yyyy-MM-dd HH:mm")
     */
    public static <T> T readJson2(String content, JavaType javaType, String dateFormatStr) {
        if (content == null || content.trim().equals(""))
            return null;

        registerDf(dateFormatStr);

        try {
            return uiObjectMapper.readerFor(javaType).readValue(content);
        } catch (IOException e) {
            logger.warn("readJson caught IOException: ", e);
        }
        return null;
    }

    /**
     * 获取泛型的 Collection Type，如 List<UcUser>
     *
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T readJson2Collection(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {

        JavaType javaType = uiObjectMapper.getTypeFactory().constructParametrizedType(collectionClass, collectionClass, elementClasses);

        return readJson2(jsonStr, javaType);
    }

    /**
     * 获取泛型的 Collection Type，如 List<UcUser>，带日期格式化参数
     *
     * @param jsonStr         json字符串
     * @param dateFormatStr   日期格式化字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T readJson2Collection(String jsonStr, String dateFormatStr, Class<?> collectionClass, Class<?>... elementClasses) {

        JavaType javaType = uiObjectMapper.getTypeFactory().constructParametrizedType(collectionClass, collectionClass, elementClasses);

        return readJson2(jsonStr, javaType, dateFormatStr);
    }

    public static Map<String, Object> readJson2Map(String jsonStr) {
        return readJson(jsonStr, Map.class);
    }

    /**
     * 从一个对象中取出相应的字段做成以 &lt 字段名，字段值 &gt 的 Map
     * 主要用于界面显示时去掉不想传到界面的字段
     *
     * @param o          任何 Object
     * @param properties 字段们
     * @return Map
     */
    public static <T> Map<String, Object> object2MapPartially(T o, String... properties) {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            BeanInfo bi = Introspector.getBeanInfo(o.getClass());

            for (String p : properties) {

                boolean foundProperty = false;

                for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {

                    if (pd.getName().equals(p)) {
                        Object value = pd.getReadMethod().invoke(o, (Object[]) null);
                        resultMap.put(p, value);
                        foundProperty = true;
                        break;
                    }

                }

                if (!foundProperty) {

                    final String message = "按说这种情况不可能啊，在【" + Object.class.getName() + "】中没有找到 [" + p + "] 属性，太恐怖了，请联系码农！";

                    if (logger.isWarnEnabled()) {

                        logger.warn(message);

                    }
                }
            }
        } catch (Exception e) {
            final String message = "在【" + o + "】中读取 [" + Arrays.toString(properties) + "] 属性出错";
            if (logger.isErrorEnabled()) {
                logger.error(message, e);
            }
        }
        return resultMap;
    }

    /**
     * 同 {@link #object2MapPartially}
     * 只是参数字段为需要去掉的字段
     * 适用于某些 Bean 字段大部分都需要，写起来很费事，只去掉某些敏感的不需要看的字段
     */
    public static <T> Map<String, Object> object2MapPartially2(T o, String... properties) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            BeanInfo bi = Introspector.getBeanInfo(o.getClass());

            for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
                boolean foundProperty = false;
                for (String p : properties) {
                    if (pd.getName().equals(p)) {
                        foundProperty = true;
                        break;
                    }
                }
                if (!foundProperty) {
                    Object value = pd.getReadMethod().invoke(o, (Object[]) null);
                    resultMap.put(pd.getName(), value);
                }
            }
        } catch (Exception e) {
            final String message = "在【" + o + "】中读取除 [" + Arrays.toString(properties) + "] 之外的属性出错";
            if (logger.isErrorEnabled()) {
                logger.error(message, e);
            }
        }
        return resultMap;
    }

    /**
     * 把一个对象只取部分字段转换成 JSON
     */
    public static <T> String toJsonPartially(T o, String... properties) {
        return toJson(object2MapPartially(o, properties));
    }

    public static <T> String toJsonPartially(T o, DateFormat df, String... properties) {
        return toJson(object2MapPartially(o, properties), df);
    }

    /**
     * List &lt Object &gt 只取部分字段 转换成 List &lt Map &lt String, Object &gt &gt
     */
    public static <T> List<Map<String, Object>> list2ListMapPartially(List<T> list, String... properties) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (T o : list) {
            resultList.add(JSONUtil.object2MapPartially(o, properties));
        }
        return resultList;
    }

    public static <T> String list2JsonPartially(List<T> list, String... properties) {
        return toJson(list2ListMapPartially(list, properties));
    }

    public static <T> String list2JsonPartially(List<T> list, DateFormat df, String... properties) {
        return toJson(list2ListMapPartially(list, properties), df);
    }

    /**
     * 把一个对象去掉部分字段转换成 JSON
     */
    public static <T> String toJsonPartially2(T o, String... properties) {
        return toJson(object2MapPartially2(o, properties));
    }

    public static <T> String toJsonPartially2(T o, DateFormat df, String... properties) {
        return toJson(object2MapPartially2(o, properties), df);
    }

    /**
     * List &lt Object &gt 只取部分字段 转换成 List &lt Map &lt String, Object &gt &gt
     */
    public static <T> List<Map<String, Object>> list2ListMapPartially2(List<T> list, String... properties) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (T o : list) {
            resultList.add(JSONUtil.object2MapPartially2(o, properties));
        }
        return resultList;
    }

    public static <T> String list2JsonPartially2(List<T> list, String... properties) {
        return toJson(list2ListMapPartially2(list, properties));
    }

    public static <T> String list2JsonPartially2(List<T> list, DateFormat df, String... properties) {
        return toJson(list2ListMapPartially2(list, properties), df);
    }


    static class MyDateSerializer extends JsonSerializer<Date> {

        private DateFormat df = null;

        public MyDateSerializer(DateFormat df) {
            this.df = df;
        }

        public MyDateSerializer(String format) {
            this.df = new SimpleDateFormat(format);
        }

        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(df.format(value));
        }

    }

    static class MyTimestampSerializer extends JsonSerializer<Timestamp> {

        private DateFormat df = null;

        public MyTimestampSerializer(DateFormat df) {
            this.df = df;
        }

        public MyTimestampSerializer(String format) {
            this.df = new SimpleDateFormat(format);
        }

        @Override
        public void serialize(Timestamp value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(df.format(value));
        }

    }

    static class MyDate2Serializer extends JsonSerializer<java.sql.Date> {

        private DateFormat df = null;

        public MyDate2Serializer(DateFormat df) {
            this.df = df;
        }

        public MyDate2Serializer(String format) {
            this.df = new SimpleDateFormat(format);
        }

        @Override
        public void serialize(java.sql.Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(df.format(value));
        }

    }

    static class MyTimeSerializer extends JsonSerializer<Time> {

        private DateFormat df = null;

        public MyTimeSerializer(DateFormat df) {
            this.df = df;
        }

        public MyTimeSerializer(String format) {
            this.df = new SimpleDateFormat(format);
        }

        @Override
        public void serialize(Time value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(df.format(value));
        }

    }

    static class MyDateDeSerializer extends JsonDeserializer<Date> {

        private DateFormat df = null;

        public MyDateDeSerializer(DateFormat df) {
            this.df = df;
        }

        public MyDateDeSerializer(String format) {
            this.df = new SimpleDateFormat(format);
        }

        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonToken t = jp.getCurrentToken();
            if (t == JsonToken.VALUE_NUMBER_INT) {
                return new Date(jp.getLongValue());
            }
            if (t == JsonToken.VALUE_NULL) {
                return null;
            }
            if (t == JsonToken.VALUE_STRING) {
                try {
                    String str = jp.getText().trim();
                    if (str.length() == 0) {
                        return null;
                    }
                    return df.parse(str);
                } catch (IllegalArgumentException | ParseException iae) {
                    throw ctxt.weirdStringException("Exception", Date.class, "not a valid representation (error: " + iae.getMessage() + ")");
                }
            }
            throw ctxt.mappingException(Date.class, t);

        }
    }

    static class MultiDateFormatDeSerializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            if (jp.getCurrentToken() == JsonToken.VALUE_NULL) {
                return null;
            }
            if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
                try {
                    String str = jp.getText().trim();
                    if (str.length() == 0) {
                        return null;
                    }
                    return DateUtils.parseDate(jp.getText(), "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-ddTHH:mm:ss");
                } catch (IllegalArgumentException | ParseException iae) {
                    throw ctxt.weirdStringException("Exception", Date.class, "not a valid representation (error: " + iae.getMessage() + ")");
                }
            }
            throw ctxt.mappingException(Date.class, jp.getCurrentToken());
        }
    }

}
