package com.huawei.olympics.repository;

import com.huawei.olympics.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

    @Query("""
            SELECT c
            FROM Country c
            LEFT JOIN c.records r
            GROUP BY c
            ORDER BY SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.GOLD   THEN 1 ELSE 0 END) DESC,
                     SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.SILVER THEN 1 ELSE 0 END) DESC,
                     SUM(CASE WHEN r.medalType = com.huawei.olympics.enumeration.MedalTypes.BRONZE THEN 1 ELSE 0 END) DESC,
                     LOWER(c.name) ASC,
                     c.id ASC
            """)
    List<Country> findAllOrderedByRanking();
}
