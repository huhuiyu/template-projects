package top.huhuiyu.codebuilder.util.test;

import top.huhuiyu.api.dbutils.datasource.DataSourceBuilder;
import top.huhuiyu.api.dbutils.datasource.DataSourceInfo;
import top.huhuiyu.api.fileutil.FileUtil;
import top.huhuiyu.api.utils.JsonUtils;
import top.huhuiyu.codebuilder.utils.*;

import java.io.File;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * CodeBuilder测试
 *
 * @author 胡辉煜
 */
public class CodeBuilderTest {

  public static void testInfo() {
    try {
      Map<String, TemplatePathInfo> map = CodeBuilderUtil.getTemplatePathInfo("templates/SpringbootMybatisBase");
      for (String key : map.keySet()) {
        System.out.println(key + "===>" + map.get(key));
      }
      System.out.println("========================================================");
      String config = FileUtil.readTextFile("templates/SpringbootMybatisBase/config.json");
      BuilderConfigInfo builderConfigInfo = JsonUtils.parse(config, BuilderConfigInfo.class);
      System.out.println(builderConfigInfo);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static void testConnection(DataSourceInfo dataSourceInfo) throws Exception {
    Class.forName(dataSourceInfo.getDriver());
    Connection connection = DriverManager.getConnection(dataSourceInfo.getUrl(), dataSourceInfo.getUsername(), dataSourceInfo.getPassword());
    PreparedStatement ps = connection.prepareStatement("select now()");
    ResultSet rs = ps.executeQuery();
    rs.next();
    System.out.println(rs.getTimestamp(1));
    rs.close();
    ps.close();
    connection.close();
  }

  public static void testTemplates(String templateConfig, DataSourceBuilder dataSourceBuilder) throws Exception {
    DataSourceInfo dataSourceInfo = dataSourceBuilder.getMySQLDataSourceInfo();
    CodeBuilder codeBuilder = CodeBuilder.getInstance(dataSourceInfo, templateConfig);
    List<File> files = codeBuilder.build();
    for (File file : files) {
      System.out.println(file);
    }
  }

  public static void testJson() throws Exception {
    Map<String, TypeInfo> map = new HashMap<>(20);
    map.put(Timestamp.class.getName(), new TypeInfo(Timestamp.class.getName(), Date.class.getName()));
    map.put(java.sql.Date.class.getName(), new TypeInfo(java.sql.Date.class.getName(), Date.class.getName()));
    String jsonmap = JsonUtils.stringify(map);
    System.out.println(jsonmap);
    Map<String, TypeInfo> jmap = JsonUtils.parse(jsonmap, HashMap.class, String.class, TypeInfo.class);
    System.out.println(jmap);
    List<TypeInfo> list = new ArrayList<>();
    list.add(new TypeInfo(Timestamp.class.getName(), Date.class.getName()));
    list.add(new TypeInfo(java.sql.Date.class.getName(), Date.class.getName()));
    String jsonlist = JsonUtils.stringify(list);
    System.out.println(jsonlist);
    List<TypeInfo> jlist = JsonUtils.parse(jsonlist, ArrayList.class, TypeInfo.class);
    System.out.println(jlist);
  }

  public static void main(String[] args) throws Exception {
    DataSourceBuilder dataSourceBuilder = new DataSourceBuilder();
    dataSourceBuilder.setInfo("teach_database", "huhuiyu.cn", "3306", "student", "Password-1234", "");
    testTemplates("templates/springbootproject/config.json", dataSourceBuilder);
    // testTemplates("templates/SpringbootMybatisBase/config.json", dataSourceBuilder);
    // testTemplates("templates/Tables/config.json", dataSourceBuilder);
    // testConnection(dataSourceBuilder.getMySQLDataSourceInfo());
    // testInfo();
    // testJson();
  }
}
