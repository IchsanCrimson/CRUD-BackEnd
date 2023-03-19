package com.crud.training.model.request;

public class KecamatanRequest {
  private Long kabupatenId;
  private String newKecamatanName;

  public KecamatanRequest() {}
  public KecamatanRequest(Long kabupatenId, String newKecamatanName) {
    this.kabupatenId = kabupatenId;
    this.newKecamatanName = newKecamatanName;
  }

  public Long getKabupatenId() {
    return kabupatenId;
  }

  public void setKabupatenId(Long kabupatenId) {
    this.kabupatenId = kabupatenId;
  }

  public String getNewKecamatanName() {
    return newKecamatanName;
  }

  public void setNewKecamatanName(String newKecamatanName) {
    this.newKecamatanName = newKecamatanName;
  }
}
