package springboot.elasticsearchdemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.elasticsearchdemo.model.Person;
import springboot.elasticsearchdemo.service.TestService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private TestService testService;

    @GetMapping("/save")
    public String save(long id,String name){
        System.out.println("save接口");
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        testService.savePerson(person);
        return "ok";
    }

    @GetMapping("/search")
    public List<Person> search(String name){
        List<Person> people = null;
        people = testService.searchPerson(name);
        return people;
    }
}
