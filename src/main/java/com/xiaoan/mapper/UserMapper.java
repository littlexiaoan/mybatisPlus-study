package com.xiaoan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上面基础基本的类BaseMapper
@Repository//持久层
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD操作都已经编写完成了
}
