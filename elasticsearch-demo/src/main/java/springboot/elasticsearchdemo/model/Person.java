package springboot.elasticsearchdemo.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -763638353551774166L;

    public static final String INDEX_NAME = "index_person";

    public static final String TYPE = "tstype";

    private Long id;

    private String name;


}
