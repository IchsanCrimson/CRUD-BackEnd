package com.crud.training.model.request;

public class KabupatenRequest {
  private Long provinsiId;
  private String newKabupatenName;

  public KabupatenRequest() {}
  public KabupatenRequest(Long provinsiId, String newKabupatenName) {
    this.provinsiId = provinsiId;
    this.newKabupatenName = newKabupatenName;
  }

  public Long getProvinsiId() {
    return provinsiId;
  }

  public void setProvinsiId(Long provinsiId) {
    this.provinsiId = provinsiId;
  }

  public String getNewKabupatenName() {
    return newKabupatenName;
  }

  public void setNewKabupatenName(String newKabupatenName) {
    this.newKabupatenName = newKabupatenName;
  }
}
