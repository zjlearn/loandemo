package com.example.loandemo.dao;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * create by zhangjun1 on 2017/9/9
 * 这个类用于将Model中list类型的属性 使用Mybatis转化为String存储到数据库中
 */

@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder sb= new StringBuilder();
        Iterator iterator = parameter.iterator();
        for(int k=0; k<parameter.size()-1; k++){
            sb.append(parameter.get(k));
            sb.append(" ");
        }
        //加入最后一个
        sb.append(parameter.get(parameter.size()-1));
        ps.setString(i, sb.toString());
        System.out.println("in the typeHandler, "+ sb.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String temp= rs.getString(columnName);
        List<String> result= Arrays.asList(temp.split(" "));
        return result;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String temp= rs.getString(columnIndex);
        List<String> result= Arrays.asList(temp.split(" "));
        return result;
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String temp= cs.getString(columnIndex);
        List<String> result= Arrays.asList(temp.split(" "));
        return result;
    }
}
