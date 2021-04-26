package com.ymmihw.javax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CascadeTypeRemoveIntegrationTest {

  private static EntityManagerFactory factory;
  private static EntityManager entityManager;

  @BeforeAll
  public static void setup() {
    factory = Persistence.createEntityManagerFactory("jpa-h2-removal");
    entityManager = factory.createEntityManager();
  }

  @Test
  public void whenOrderRequestIsDeleted_thenDeleteShipmentInfo() {
    createOrderRequestWithShipmentInfo();

    OrderRequest orderRequest = entityManager.find(OrderRequest.class, 1L);

    entityManager.getTransaction().begin();
    entityManager.remove(orderRequest);
    entityManager.getTransaction().commit();

    assertEquals(0, findAllOrderRequest().size());
    assertEquals(0, findAllShipmentInfo().size());
  }

  private void createOrderRequestWithShipmentInfo() {
    ShipmentInfo shipmentInfo = new ShipmentInfo("name");
    OrderRequest orderRequest = new OrderRequest(shipmentInfo);

    entityManager.getTransaction().begin();
    entityManager.persist(orderRequest);
    entityManager.getTransaction().commit();

    assertEquals(1, findAllOrderRequest().size());
    assertEquals(1, findAllShipmentInfo().size());
  }

  private List<OrderRequest> findAllOrderRequest() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<OrderRequest> cq = cb.createQuery(OrderRequest.class);
    Root<OrderRequest> root = cq.from(OrderRequest.class);
    CriteriaQuery<OrderRequest> findAll = cq.select(root);
    TypedQuery<OrderRequest> findAllQuery = entityManager.createQuery(findAll);

    return findAllQuery.getResultList();
  }

  private List<ShipmentInfo> findAllShipmentInfo() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<ShipmentInfo> cq = cb.createQuery(ShipmentInfo.class);
    Root<ShipmentInfo> root = cq.from(ShipmentInfo.class);
    CriteriaQuery<ShipmentInfo> findAll = cq.select(root);
    TypedQuery<ShipmentInfo> findAllQuery = entityManager.createQuery(findAll);

    return findAllQuery.getResultList();
  }

}
