package com.crud.training.service;

import com.crud.training.model.database.Provinsi;
import com.crud.training.repository.ProvinsiRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProvinsiService {
  private final ProvinsiRepository provinsiRepository;

  private final String provinsiName = "name";

  public ProvinsiService(ProvinsiRepository provinsiRepository) {
    this.provinsiRepository = provinsiRepository;
  }

  public List<Provinsi> getAllProvinsiList() {
    return provinsiRepository.findAll(Sort.by(Sort.Direction.ASC, this.provinsiName));
  }

  public void addProvinsi(String newProvinsiName) throws Exception {
    validateProvinsiName(newProvinsiName);

    Provinsi provinsi = new Provinsi(newProvinsiName);
    provinsiRepository.save(provinsi);
  }

  public Boolean updateProvinsi(Long provinsiId, String newProvinsiName) throws Exception {
    validateProvinsiName(newProvinsiName);

    return provinsiRepository.findById(provinsiId)
        .map(provinsi -> updateProvinsiName(provinsi, newProvinsiName))
        .map(provinsiRepository::save)
        .map(provinsi -> Boolean.TRUE)
        .orElseThrow(() -> new Exception("Provinsi with id: " + provinsiId + " not found"));
  }

  private void validateProvinsiName(String name) throws Exception {
    if (!StringUtils.hasText(name)) {
      throw new Exception("Provinsi name must not be empty");
    }
  }

  private Provinsi updateProvinsiName(Provinsi provinsi, String name) {
    provinsi.setName(name);
    return provinsi;
  }

  public void deleteProvinsi(Long provinsiId) {
    provinsiRepository.deleteById(provinsiId);
  }

  public Provinsi getProvinsi(Long provinsiId) {
    return provinsiRepository.findById(provinsiId)
        .orElseGet(() -> null);
  }
}
