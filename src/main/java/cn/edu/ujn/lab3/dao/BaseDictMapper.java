package cn.edu.ujn.lab3.dao;



import java.util.List;

import cn.edu.ujn.lab3.dao.BaseDict;

public interface BaseDictMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    BaseDict selectByPrimaryKey(String dictId);
    String selectIdByName(String name);
    BaseDict selectByName(String dictId);
    List<BaseDict> selectAll();
    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
}