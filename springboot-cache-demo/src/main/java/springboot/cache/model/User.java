package springboot.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {


   private Integer id;

   private String name;

   private Integer age;




}