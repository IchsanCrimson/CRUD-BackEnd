package com.crud.training.model.response;

public class KabupatenInfoResponse {
  private Long id;
  private String name;

  private Long provinsiId;

  private String provinsiName;

  public KabupatenInfoResponse() {}

  public KabupatenInfoResponse(Long id, String name, Long provinsiId, String provinsiName) {
    this.id = id;
    this.name = name;
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
