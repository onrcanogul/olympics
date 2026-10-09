package com.huawei.olympics.repository;

import com.huawei.olympics.entity.MedalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedalRecordRepository extends JpaRepository<MedalRecord, UUID> {
}
