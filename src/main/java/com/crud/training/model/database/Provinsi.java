package com.crud.training.model.database;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "provinsi")
public class Provinsi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long provinsi_id;
  private String name;

  @OneToMany(mappedBy = "provinsi", cascade = CascadeType.ALL)
  private List<Kabupaten> kabupatenList;

  public Provinsi() {}

  public Provinsi(String name) {
    this.name = name;
  }

  public Long getProvinsi_id() {
    return provinsi_id;
  }

  public void setProvinsi_id(Long provinsi_id) {
    this.provinsi_id = provinsi_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Kabupaten> getKabupatenList() {
    return kabupatenList;
  }

  public void setKabupatenList(List<Kabupaten> kabupatenList) {
    this.kabupatenList = kabupatenList;
  }
}
