package com.ymmihw.javax.daos;

import org.springframework.data.repository.CrudRepository;
import com.ymmihw.javax.models.NullableItem;
public interface NullableItemRepository extends CrudRepository<NullableItem, Long> {
}
