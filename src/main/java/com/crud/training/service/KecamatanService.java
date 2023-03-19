package com.crud.training.service;

import com.crud.training.model.database.Kecamatan;
import com.crud.training.model.database.Kabupaten;
import com.crud.training.repository.KecamatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class KecamatanService {
  private final KecamatanRepository kecamatanRepository;

  private final KabupatenService kabupatenService;

  @Autowired
  public KecamatanService(KecamatanRepository kecamatanRepository, KabupatenService kabupatenService) {
    this.kecamatanRepository = kecamatanRepository;
    this.kabupatenService = kabupatenService;
  }

  public List<Kecamatan> getKecamatanList(Long kabupatenId) {
    return kecamatanRepository.findAllKecamatanByKabupatenId(kabupatenId);
  }

  public Kecamatan getKecamatan(Long kecamatanId) {
    return kecamatanRepository.findById(kecamatanId)
        .orElseGet(() -> null);
  }

  public void addKecamatan(Long kabupatenId, String newKecamatanName) throws Exception {
    validateKecamatanValue(kabupatenId, newKecamatanName);

    Kabupaten kabupaten = kabupatenService.getKabupaten(kabupatenId);

    if (Objects.nonNull(kabupaten)) {
      Kecamatan kecamatan = new Kecamatan(newKecamatanName, kabupaten);
      kecamatanRepository.save(kecamatan);
    } else {
     throw new Exception("Kabupaten with id: " + kabupatenId + " not found");
    }
  }

  private void validateKecamatanValue(Long kabupatenId, String kecamatanName) throws Exception {
    if (Objects.isNull(kabupatenId)) {
      throw new Exception("Kabupaten id must not be empty");
    }

    if (StringUtils.isEmpty(kecamatanName)) {
      throw new Exception("Kecamatan name must not be empty");
    }
  }

  public Boolean updateKecamatan(Long kecamatanId, String newKecamatanName, Long kabupatenId) throws Exception {
    validateKecamatanValue(kabupatenId, newKecamatanName);

    return kecamatanRepository.findById(kecamatanId)
        .map(kecamatan -> updateKecamatanValue(kecamatan, newKecamatanName, kabupatenId))
        .map(kecamatanRepository::save)
        .map(kecamatan -> Boolean.TRUE)
        .orElseThrow(() -> new Exception("Kecamatan with id: " + kecamatanId + " not found"));
  }

  private Kecamatan updateKecamatanValue(Kecamatan kecamatan, String name, Long kabupatenId) throws RuntimeException {
    if(!StringUtils.isEmpty(name)) {
      kecamatan.setName(name);
    }

    if(Objects.nonNull(kabupatenId)) {
      Kabupaten kabupaten = kabupatenService.getKabupaten(kabupatenId);

      if (Objects.nonNull(kabupaten)) {
        kecamatan.setKabupaten(kabupaten);
      } else {
        throw new RuntimeException("Kabupaten with id: " + kabupatenId + " not found");
      }
    }

    return kecamatan;
  }

  public void deleteKecamatan(Long kecamatanId) {
    kecamatanRepository.deleteById(kecamatanId);
  }
}
