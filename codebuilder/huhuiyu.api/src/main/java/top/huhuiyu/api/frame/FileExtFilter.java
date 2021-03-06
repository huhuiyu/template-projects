package top.huhuiyu.api.frame;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * 文件扩展名过滤器
 *
 * @author 胡辉煜
 */
public class FileExtFilter extends FileFilter {
  public static final String SPILT = ",";
  private String[] filters;

  public FileExtFilter(String filter) {
    super();
    filters = filter.split(SPILT);
  }

  @Override
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return true;
    }
    String name = f.getName();
    for (String ext : filters) {
      if (name.toLowerCase().endsWith("." + ext.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getDescription() {
    return "文件过滤";
  }

}
