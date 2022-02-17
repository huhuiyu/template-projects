package top.huhuiyu.api.dbutils.test;

import java.util.List;

import top.huhuiyu.api.dbutils.DbUtils;
import top.huhuiyu.api.dbutils.datasource.DataSourceBuilder;
import top.huhuiyu.api.dbutils.datasource.DataSourceInfo;
import top.huhuiyu.api.dbutils.meta.MetaUtilsBean;
import top.huhuiyu.api.dbutils.meta.TableInfo;

/**
 * 临时测试
 * 
 * @author 胡辉煜
 */
public class Temp {

  public static void one() throws Exception {
    DataSourceBuilder dataSourceBuilder = new DataSourceBuilder();
    dataSourceBuilder.setDatabase("db_teach_demo");
    dataSourceBuilder.setInfo("db_teach_demo", "huhuiyu.top", "3306", "test", "User4-test", null);
    DataSourceInfo info = dataSourceBuilder.getMySQLDataSourceInfo();
    System.out.println(info);
    System.out.println(dataSourceBuilder.getMSSQLDataSourceInfo());
    System.out.println(dataSourceBuilder.getOracleDataSourceInfo());
  }

  public static void two() throws Exception {
    DataSourceBuilder dataSourceBuilder = new DataSourceBuilder();
    dataSourceBuilder.setDatabase("db_teach_demo");
    dataSourceBuilder.setInfo("db_teach_demo", "huhuiyu.top", "3306", "test", "User4-test", null);
    MetaUtilsBean metaUtilsBean = new MetaUtilsBean(dataSourceBuilder.getMySQLDataSourceInfo());
    List<TableInfo> list = metaUtilsBean.processMetaInfo();
    for (TableInfo tableInfo : list) {
      System.out.println(tableInfo);
    }
  }

  public static void three() throws Exception {
    DbUtils dbUtils = new DbUtils();
    String colname = "user_name";
    String tablename = "tb_admin_role";
    System.out.println(dbUtils.getEntityName(tablename));
    System.out.println(dbUtils.getEntityFieldName(tablename));
    System.out.println(dbUtils.getTableGetter(tablename));
    System.out.println(dbUtils.getTableSetter(tablename));
    System.out.println(dbUtils.getJavaFieldName(colname));
    System.out.println(dbUtils.getJavaGetter(colname, Boolean.class.getName()));
    System.out.println(dbUtils.getJavaSetter(colname));
    System.out.println("==========================");
    colname = "username";
    tablename = "tbrole";
    System.out.println(dbUtils.getEntityName(tablename));
    System.out.println(dbUtils.getEntityFieldName(tablename));
    System.out.println(dbUtils.getTableGetter(tablename));
    System.out.println(dbUtils.getTableSetter(tablename));
    System.out.println(dbUtils.getJavaFieldName(colname));
    System.out.println(dbUtils.getJavaGetter(colname, String.class.getName()));
    System.out.println(dbUtils.getJavaSetter(colname));
  }

  public static void main(String[] args) throws Exception {
     Temp.one();
     Temp.two();
     Temp.three();
  }
}
