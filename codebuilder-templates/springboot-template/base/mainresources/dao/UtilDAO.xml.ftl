<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 工具类 mapper -->
<mapper namespace="${builderUtil.getSubPackage("dao")}.UtilDAO">
    <!-- 查询数据库当前时间 -->
  <select id="queryTime" resultType="java.util.Date">
    select now()
  </select>

  <!-- 查询数据库当前时间戳 -->
  <select id="queryTimestamp" resultType="java.lang.Long">
    select unix_timestamp()
  </select>
</mapper>