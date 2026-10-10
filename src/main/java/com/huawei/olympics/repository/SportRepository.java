package com.huawei.olympics.repository;

import com.huawei.olympics.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {

    @Query("""
            SELECT s
            FROM Sport s
            LEFT JOIN s.records r
            GROUP BY s
            ORDER BY COUNT(r) DESC,
                     LOWER(s.name) ASC,
                     s.id ASC
            """)
    List<Sport> findAllOrderedByRanking();
}
