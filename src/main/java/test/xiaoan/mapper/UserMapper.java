package test.xiaoan.mapper;

import test.xiaoan.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ann
 * @since 2023-03-11 09:08:36
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
