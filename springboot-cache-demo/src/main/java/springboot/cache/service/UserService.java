package springboot.cache.service;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;
import springboot.cache.mapper.UserMapper;
import springboot.cache.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = {"user"})
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private DruidDataSource druidDataSource;

    @Cacheable("test")
    public User getUser(Integer id){
        log.info("正在查询数据库");
        return userMapper.findUserById(id);

    }

    @CacheEvict(value = "test",allEntries = true,beforeInvocation = false)
    public void evictCache(){

    }

    @Cacheable("test")
    @Caching
    public List<User> list() {
        log.info("正在查询数据库");
        log.info(druidDataSource.toString());
        return userMapper.list();
    }
}
