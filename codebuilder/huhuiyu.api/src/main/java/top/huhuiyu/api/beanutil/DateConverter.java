package top.huhuiyu.api.beanutil;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义时间格式转换器
 *
 * @author 胡辉煜
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DateConverter implements Converter {

  private static final Logger log = LoggerFactory.getLogger(DateConverter.class);

  private String format = "yyyy-MM-dd";

  public DateConverter() {
  }

  /**
   * 设置日期格式
   *
   * @param format 日期格式
   */
  public void setFormat(String format) {
    this.format = format;
  }

  @Override
  public Object convert(Class c, Object value) {
    // 空值不转换
    if (value == null) {
      return null;
    }
    // 类型匹配将不执行转换
    if (MyBeanUtils.classIsType(value.getClass(), c)) {
      return value;
    }
    // 空字符不执行转换
    if ((value == null) || value.toString().trim().equals("")) {
      return null;
    }
    log.debug(String.format("日期数据转换：%s(%s)", value, c));
    Date v = getDate(value);
    if (v == null) {
      return value;
    } else if (c == java.util.Date.class) {
      return v;
    } else if (c == java.sql.Date.class) {
      return new java.sql.Date(v.getTime());
    } else if (c == java.sql.Time.class) {
      return new java.sql.Time(v.getTime());
    } else if (c == java.sql.Timestamp.class) {
      return new java.sql.Timestamp(v.getTime());
    } else {
      return value;
    }
  }

  /**
   * 转换对象值到日期
   *
   * @param value 要转换的对象
   * 
   * @return 对象对应的日期值
   */
  public Date getDate(Object value) {
    if (format != null) {
      Date v = tryConvert(value, format);
      return v;
    }
    return null;
  }

  /**
   * 转换日期数据
   *
   * @param value  要转换的数据
   * @param format 日期格式
   * 
   * @return 日期
   */
  public static Date tryConvert(Object value, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat();
      sdf.applyPattern(format);
      Date v = sdf.parse(String.valueOf(value));
      return v;
    } catch (Exception e) {
      return null;
    }
  }

}
