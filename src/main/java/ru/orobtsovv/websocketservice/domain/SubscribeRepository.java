package ru.orobtsovv.websocketservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, SubscribeId> {
    @Query("select s.from from SubscribeEntity s " +
            "where s.to=:id")
    List<Integer> findSubscribers(int id);

    @Transactional
    @Modifying
    void deleteAllByFromOrTo(int from, int to);
}
