package test.xiaoan.serviceImpl;

import test.xiaoan.pojo.User;
import test.xiaoan.mapper.UserMapper;
import test.xiaoan.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ann
 * @since 2023-03-11 09:08:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
