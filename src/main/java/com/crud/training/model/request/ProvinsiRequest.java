package com.crud.training.model.request;

public class ProvinsiRequest {
  private String newProvinsiName;

  public ProvinsiRequest() {}

  public ProvinsiRequest(String newProvinsiName) {
    this.newProvinsiName = newProvinsiName;
  }

  public String getNewProvinsiName() {
    return newProvinsiName;
  }

  public void setNewProvinsiName(String newProvinsiName) {
    this.newProvinsiName = newProvinsiName;
  }
}
