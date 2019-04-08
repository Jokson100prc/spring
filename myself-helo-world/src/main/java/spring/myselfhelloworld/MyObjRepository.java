package spring.myselfhelloworld;

import org.springframework.data.repository.CrudRepository;

public interface MyObjRepository extends CrudRepository<MyObject, Integer> {
}
