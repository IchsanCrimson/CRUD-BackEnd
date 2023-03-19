package com.crud.training.model.database;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "kabupaten")
public class Kabupaten {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long kabupaten_id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "provinsi_id")
  private Provinsi provinsi;

  @OneToMany(mappedBy = "kabupaten", cascade = CascadeType.ALL)
  private List<Kecamatan> kecamatanList;

  public Kabupaten() {}
  public Kabupaten(String name, Provinsi provinsi) {
    this.name = name;
    this.provinsi = provinsi;
  }

  public Long getKabupaten_id() {
    return kabupaten_id;
  }

  public void setKabupaten_id(Long kabupaten_id) {
    this.kabupaten_id = kabupaten_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Provinsi getProvinsi() {
    return provinsi;
  }

  public void setProvinsi(Provinsi provinsi) {
    this.provinsi = provinsi;
  }

  public List<Kecamatan> getKecamatanList() {
    return kecamatanList;
  }

  public void setKecamatanList(List<Kecamatan> kecamatanList) {
    this.kecamatanList = kecamatanList;
  }
}
