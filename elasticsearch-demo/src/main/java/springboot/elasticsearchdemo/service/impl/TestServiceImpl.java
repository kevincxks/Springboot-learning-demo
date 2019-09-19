package springboot.elasticsearchdemo.service.impl;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.elasticsearchdemo.model.Person;
import springboot.elasticsearchdemo.service.TestService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private JestClient jestClient;

    @Override
    public void savePerson(Person person) {
        Index index = new Index.Builder(person).index(Person.INDEX_NAME).type(Person.TYPE).build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("插入完成");
    }

    @Override
    public void savePerson(List<Person> persons) {
        Bulk.Builder bulk = new Bulk.Builder();
        for(Person person : persons) {
            Index index = new Index.Builder(person).index(Person.INDEX_NAME).type(Person.TYPE).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            log.info("ES 插入完成");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Person> searchPerson(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(Person.INDEX_NAME).addType(Person.TYPE).build();
        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(Person.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
