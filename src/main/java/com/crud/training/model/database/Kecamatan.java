package com.crud.training.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kecamatan")
public class Kecamatan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long kecamatan_id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "kabupaten_id")
  private Kabupaten kabupaten;

  public Kecamatan() {}
  public Kecamatan(String name, Kabupaten kabupaten) {
    this.name = name;
    this.kabupaten = kabupaten;
  }

  public Long getKecamatan_id() {
    return kecamatan_id;
  }

  public void setKecamatan_id(Long kecamatan_id) {
    this.kecamatan_id = kecamatan_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Kabupaten getKabupaten() {
    return kabupaten;
  }

  public void setKabupaten(Kabupaten kabupaten) {
    this.kabupaten = kabupaten;
  }
}
