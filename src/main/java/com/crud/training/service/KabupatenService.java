package com.crud.training.service;

import com.crud.training.model.database.Kabupaten;
import com.crud.training.model.database.Provinsi;
import com.crud.training.repository.KabupatenRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class KabupatenService {
  private final KabupatenRepository kabupatenRepository;

  private final ProvinsiService provinsiService;

  public KabupatenService(KabupatenRepository kabupatenRepository, ProvinsiService provinsiService) {
    this.kabupatenRepository = kabupatenRepository;
    this.provinsiService = provinsiService;
  }

  public List<Kabupaten> getKabupatenList(Long provinsiId) {
    return kabupatenRepository.findAllKabupatenByProvinsiId(provinsiId);
  }

  public void addKabupaten(Long provinsiId, String kabupatenName) throws Exception {
    validateKabupatenValue(provinsiId, kabupatenName);

    Provinsi provinsi = provinsiService.getProvinsi(provinsiId);

    if (Objects.nonNull(provinsi)) {
      Kabupaten kabupaten = new Kabupaten(kabupatenName, provinsi);
      kabupatenRepository.save(kabupaten);
    } else {
     throw new Exception("Provinsi with id: " + provinsiId + " not found");
    }
  }

  private void validateKabupatenValue(Long provinsiId, String kabupatenName) throws Exception {
    if (Objects.isNull(provinsiId)) {
      throw new Exception("Provinsi id must not be empty");
    }

    if (!StringUtils.hasText(kabupatenName)) {
      throw new Exception("Kabupaten name must not be empty");
    }
  }

  public Boolean updateKabupaten(Long kabupatenId, String newKabupatenName, Long provinsiId) throws Exception {
    validateKabupatenValue(provinsiId, newKabupatenName);

    return kabupatenRepository.findById(kabupatenId)
        .map(kabupaten -> updateKabupatenValue(kabupaten, newKabupatenName, provinsiId))
        .map(kabupatenRepository::save)
        .map(kabupaten -> Boolean.TRUE)
        .orElseThrow(() -> new Exception("Kabupaten with id: " + kabupatenId + " not found"));
  }

  private Kabupaten updateKabupatenValue(Kabupaten kabupaten, String name, Long provinsiId) throws RuntimeException {
    kabupaten.setName(name);

    Provinsi provinsi = provinsiService.getProvinsi(provinsiId);

    if (Objects.nonNull(provinsi)) {
      kabupaten.setProvinsi(provinsi);
    } else {
      throw new RuntimeException("Provinsi with id: " + provinsiId + " not found");
    }

    return kabupaten;
  }

  public void deleteKabupaten(Long kabupatenId) {
    kabupatenRepository.deleteById(kabupatenId);
  }


  public Kabupaten getKabupaten(Long kabupatenId) {
    return kabupatenRepository.findById(kabupatenId)
        .orElseGet(() -> null);
  }
}
