package com.crud.training.repository;

import com.crud.training.model.database.Kabupaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KabupatenRepository  extends JpaRepository<Kabupaten, Long> {

  @Query(value = "SELECT * FROM kabupaten WHERE kabupaten.provinsi_id = ?1", nativeQuery = true)
  List<Kabupaten> findAllKabupatenByProvinsiId(Long id);
}
