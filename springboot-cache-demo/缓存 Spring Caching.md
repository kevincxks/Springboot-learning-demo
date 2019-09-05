### 缓存 Spring Caching

> Cache is always a key-value storage and Spring caching is not different in this



To enable *Spring caching* support, we need to take care of following two important points.

- We need to identify and annotate methods that need to be cached.
- Cache configuration: Enable caching support



To enable caching annotation, we need to add the annotation @EnableCaching to one the application @Configuration class.

```Java
@Configuration
@EnableCaching
public class AppConfig {

    // Cache Manager configurations
}
```

@EnableCaching 使得post processor检查每一个bean的public方法是否存在caching注解。如果找到这样的注释，则自动创建代理以拦截方法调用并相应地处理缓存行为。



*Spring缓存抽象*提供了与不同缓存提供者的整合方式，通过定义  `CacheManager`去控制和管理应用中的Cache。

```java
@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("product");
    }
}
```

或许从xml的角度能看得更清楚些：

```xml
<!-- simple cache manager -->
<bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
    <property name="caches">
        <set>
            <bean class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" p:name="default"/>
            <bean class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" p:name="books"/>
        </set>
    </property>
</bean>
```

Spring Boot使用一个简单的实现  `ConcurrentHashMap` 作为缓存存储。如果应用程序中不存在其他缓存库，则这是默认值。



Spring缓存API提供了一组可供使用的*Java注释*。

| **Annotation** | **Description**                                          |
| -------------- | -------------------------------------------------------- |
| `@Cacheable`   | Triggers cache population                                |
| `@Caching`     | Regroups multiple cache operations                       |
| `@CacheConfig` | Shares some common cache-related settings at class-level |
| `@CacheEvict`  | Cache eviction                                           |
| `@CachePut`    | Updates the cache                                        |



**这些注解并没有严格要求标注在哪里，虽然一般而言会放在service方法上，但实际上放在Controller方法也会起作用。**



####  @Cacheable

简单来说，这个注释用于指示我们希望将此方法的结果存储到缓存中，因此，在后续调用中，返回缓存中的值而无需实际执行该方法。

```java
@Cacheable("products")
public List<Product> getProducts() {...}
```

getProducts（）将在实际调用方法之前首先检查缓存“products”，然后缓存结果。此注解允许指定多个名称，以便使用多个缓存。

```java
@Cacheable("baseProducts","varaintProducts")
public List<Product> getProducts() {...}
```

如果两个缓存中包含所需的结果，则直接返回结果不需要调用方法。



 ####  @CacheEvict

```java
@CacheEvict(cacheNames="products",allEntries=true,beforeInvocation = false)
public List<Product> getProducts() {...}
```

这个注解与`@Cacheable`作用相反，用于删除缓存，allEntres则表示删除整个缓存的内容。`beforeInvocation`能够在方法执行前驱逐缓存，默认为false。


这里必须提一下缓存名字**CacheNames**和**Key**的区别，缓存本身有一个名字，这个名字指代的是一整个缓存容器，它可以容纳多个键值对。而用于查找缓存的是Key，Key默认是方法参数**的值**(默认使用SimpleKeyGenerator生成key)

```java
public static Object generateKey(Object... params) {
  if (params.length == 0) {
    return SimpleKey.EMPTY;
  } else {
    if (params.length == 1) {
      Object param = params[0];
      if (param != null && !param.getClass().isArray()) {
        return param;
      }
    }

    return new SimpleKey(params);
  }
}
```

可以看到如果只有一个参数，则直接返回。至于有多个参数，实际上返回的是以参数为内容的对象数组的deepHashCode（Arrays类中）



 ####  @CachePut

每次都执行方法，把键值对插入缓存，用于更新缓存。



**注意不要把@CachePut和*@Cacheable用在同一个方法上**



 ####  @CacheConfig

类级别的注解，用于集中管理一部分配置。

```java
@CacheConfig("products")
public class ProductService{

    @Cacheable
    public List<Product> getProducts() {...}
}
```

比如这样，相当于指定了所有@Cacheable的名字。




 ####  @Caching

用于嵌套多个缓存注解，因为Java不允许多个重名注解。此处不赘述。



#### 自定义Key

```java
@Cacheable(cacheNames="address", key="#customer")
public Address getAddress(final Customer customer)() {...}

@Cacheable(cacheNames="address", key="#customer.id")
public Address getAddress(final Customer customer)() {...}
```



你甚至还能自己写一个key生成器，此处不赘述



#### 缓存同步

在多线程环境，有可能同一个键值对会被加入/更新多次，我们希望做一定的同步处理。

```java
@Cacheable(value="products",sync=true)
public List<Product> getProducts() {...}
```

此时，计算并且放入缓存的操作会互斥。





#### 条件缓存

```java
@Cacheable(cacheNames="address", condition="#customer.profile.country='US'")
public Address getAddress(final Customer customer)() {...}
```



```java
@Cacheable(cacheNames="address", unless="#customer.profile.country='US'")
public Address getAddress(final Customer customer)() {...}
```

`unless` 在调用方法后进行评估。





#### 整合Caffeine

Caffeine是一个非常接近最优的强大缓存，其性能远比常用的ehcache和Guava cache更强。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>2.7.0</version>
</dependency>
```

其实Spring中已经内置了CaffeineCacheManager，你可以直接使用，但是不具备定制功能。

```java
@Configuration
public class CaffeineCacheConfig {

 public CacheManager cacheManager() {
  CaffeineCacheManager cacheManager = new CaffeineCacheManager("test");
  cacheManager.setCaffeine(caffeineCacheBuilder());
  return cacheManager;
 }

 Caffeine < Object, Object > caffeineCacheBuilder() {
  return Caffeine.newBuilder()
   .initialCapacity(100)
   .maximumSize(500)
   .expireAfterAccess(10, TimeUnit.MINUTES)
   .weakKeys()
   .recordStats();
 }
}

```

CaffeineCacheManager其实本身也内置了一个map，装有多个缓存容器，初始化时可以指定多个cacheName，相当于将多个不同名的缓存容器交由CacheManager管理。

