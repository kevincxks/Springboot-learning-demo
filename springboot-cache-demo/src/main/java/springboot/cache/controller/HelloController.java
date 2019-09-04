package springboot.cache.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.cache.model.User;
import springboot.cache.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cache")
public class HelloController {

    @Resource
    private UserService userService;

    @GetMapping("/demo")
    public User getCache(@RequestParam("id") Integer id){
        return userService.getUser(id);
    }

    @GetMapping("list")
    public List<User> getList(){
        return userService.list();
    }

    @GetMapping("evict")
    public void evictCache(){
        userService.evictCache();
    }
}
