package com.crud.training.repository;

import com.crud.training.model.database.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {

  @Query(value = "SELECT * FROM kecamatan WHERE kecamatan.kabupaten_id = ?1 ORDER BY kecamatan.name ASC", nativeQuery = true)
  List<Kecamatan> findAllKecamatanByKabupatenIdOrderByNameAsc(Long id);
}
