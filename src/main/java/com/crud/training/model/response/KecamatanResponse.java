package com.crud.training.model.response;

public class KecamatanResponse {

  private Long id;

  private String name;

  public KecamatanResponse(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
