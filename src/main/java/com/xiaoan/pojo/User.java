package com.xiaoan.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //对应数据库中的主键（uuid、自增id、雪花算法、redis、zookeeper！）
    /**
     * @TableId(type = IdType.AUTO) 自增主键
     * 1.引入此注解
     * 2.数据库字段一定要自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version //乐观锁的Version注解
    private Integer version;
    //高版本直接加此注解就行
    @TableLogic//逻辑删除
    private Integer deleted;
    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
