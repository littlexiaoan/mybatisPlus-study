package com.xiaoan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoan.mapper.UserMapper;
import com.xiaoan.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //链式编程
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println);//和我们刚才学习的map对比一下
    }

    @Test
    void test2() {
        //查询名字等于安国斌
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "安国斌");
        User user = userMapper.selectOne(wrapper);//查询一个数据，出现多个结果使用list 或者map
        System.out.println(user);
    }

    @Test
    void test3() {
        //查询年龄在20~30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);//区间
        Long count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    //模糊查询
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //左和右 %e%
        //名字不包含a且有些以t开头
        wrapper
                .notLike("name", "a")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id", "select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序(Desc降序，Asc升序)
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

}
