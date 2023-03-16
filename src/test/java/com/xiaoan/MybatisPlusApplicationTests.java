package com.xiaoan;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoan.mapper.UserMapper;
import com.xiaoan.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper，所有的方法都来自父类，我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数是一个 Wrapper，条件构造器，这里我们先不用 null
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    void testInsert() {
        User user = new User();
        user.setName("小安学mybatis");
        user.setAge(22);
        user.setEmail("2430277611@qq.com");

        int result = userMapper.insert(user);//帮我们自动生成id
        System.out.println(result);//受影响的行数
        System.out.println(user);//发现，id会自动回填
    }

    //测试更新
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1634118967983501316L);
        user.setName("小安学mybatis_plus");
        user.setAge(18);
        // 注意：updateById 参数是一个对象！
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁 成功！
    @Test
    void testOptimisticLocker() {
        //1. 查询用户信息
        User user = userMapper.selectById(1L);
        //2. 修改用户信息
        user.setName("安国斌");
        user.setEmail("918978283@qq.com");
        //3. 执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁 失败！ 多线程下
    @Test
    void testOptimisticLocker2() {
        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("安国斌111");
        user.setEmail("918978283@qq.com");

        // 模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("安国斌222");
        user2.setEmail("918978283@qq.com");
        userMapper.updateById(user2);

        // 自旋锁来多次尝试提交！
        userMapper.updateById(user);//如果没有乐观锁就会覆盖插队线程的值！
    }

    //测试查询
    @Test
    void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //测试批量查询
    @Test
    void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //按条件查询之一使用map
    @Test
    void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义要查询
        map.put("name", "小安学mybatis_plus"); //where name ="小安学mybatis_plus"
        map.put("age", 18);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    void testPae() {
        // 参数一：当前页 第1页
        // 参数二：页面大小 5条
        // 使用了分页插件后，所有的分页操作也变得简单！
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }

    //测试删除
    @Test
    void testDeleteById() {
        userMapper.deleteById(1L);
    }

    //通过id批量删除
    @Test
    void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(1634118967983501315L, 1634118967983501314L));
    }

    //通过map删除
    @Test
    void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "小安学mybatis_plus");
        userMapper.deleteByMap(map);
    }
}
