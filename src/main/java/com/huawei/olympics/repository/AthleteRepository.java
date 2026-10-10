package com.huawei.olympics.repository;

import com.huawei.olympics.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, UUID> {

    @Query("""
            SELECT a
            FROM Athlete a
            LEFT JOIN FETCH a.country c
            LEFT JOIN a.records r
            GROUP BY a, c
            ORDER BY COUNT(r) DESC,
                     SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.GOLD   THEN 1 ELSE 0 END) DESC,
                     SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.SILVER THEN 1 ELSE 0 END) DESC,
                     SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.BRONZE THEN 1 ELSE 0 END) DESC,
                     LOWER(a.name) ASC,
                     a.id ASC
            """)
    List<Athlete> findAllOrderedByRanking();
}
