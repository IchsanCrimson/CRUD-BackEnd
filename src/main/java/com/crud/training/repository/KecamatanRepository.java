package com.crud.training.repository;

import com.crud.training.model.database.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {

  @Query(value = "SELECT * FROM kecamatan WHERE kecamatan.kabupaten_id = ?1", nativeQuery = true)
  List<Kecamatan> findAllKecamatanByKabupatenId(Long id);
}
