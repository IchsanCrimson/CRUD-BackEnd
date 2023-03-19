package com.crud.training.model.response;

public class KecamatanInfoResponse {
  private Long id;

  private String name;

  private Long kabupatenId;

  private String kabupatenName;

  private Long provinsiId;

  private String provinsiName;

  public KecamatanInfoResponse() {}

  public KecamatanInfoResponse(Long id, String name, Long kabupatenId, String kabupatenName, Long provinsiId, String provinsiName) {
    this.id = id;
    this.name = name;
    this.kabupatenId = kabupatenId;
    this.kabupatenName = kabupatenName;
    this.provinsiId = provinsiId;
    this.provinsiName = provinsiName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getKabupatenId() {
    return kabupatenId;
  }

  public void setKabupatenId(Long kabupatenId) {
    this.kabupatenId = kabupatenId;
  }

  public String getKabupatenName() {
    return kabupatenName;
  }

  public void setKabupatenName(String kabupatenName) {
    this.kabupatenName = kabupatenName;
  }

  public Long getProvinsiId() {
    return provinsiId;
  }

  public void setProvinsiId(Long provinsiId) {
    this.provinsiId = provinsiId;
  }

  public String getProvinsiName() {
    return provinsiName;
  }

  public void setProvinsiName(String provinsiName) {
    this.provinsiName = provinsiName;
  }
}
