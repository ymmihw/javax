package com.ymmihw.javax.daos;

import org.springframework.data.repository.CrudRepository;
import com.ymmihw.javax.models.NotNullItem;
public interface NotNullItemRepository extends CrudRepository<NotNullItem, Long> {
}
