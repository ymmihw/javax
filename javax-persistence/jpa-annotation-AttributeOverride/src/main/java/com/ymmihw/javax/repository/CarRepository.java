package com.ymmihw.javax.repository;

import com.ymmihw.javax.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
