package springboot.elasticsearchdemo.service;

import springboot.elasticsearchdemo.model.Person;

import java.util.List;

public interface TestService {

    void savePerson(Person person);

    void savePerson(List<Person> persons);

    List<Person> searchPerson(String searchContent);
}
