package com.crud.training.controller;

import com.crud.training.model.request.ProvinsiRequest;
import com.crud.training.model.response.ProvinsiResponse;
import com.crud.training.service.ProvinsiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provinsi")
@CrossOrigin(origins = "http://localhost:8080")
public class ProvinsiController {

  private final ProvinsiService provinsiService;

  public ProvinsiController(ProvinsiService provinsiService) {
    this.provinsiService = provinsiService;
  }

  @GetMapping(value = "/getAll")
  public ResponseEntity<List<ProvinsiResponse>> getAllProvinsiList() {
    List<ProvinsiResponse> provinsiResponseList = provinsiService.getAllProvinsiList().stream()
        .map(provinsi -> new ProvinsiResponse(provinsi.getProvinsi_id(), provinsi.getName()))
        .collect(Collectors.toList());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(provinsiResponseList);
  }

  @PostMapping("/create")
  public ResponseEntity<String> addProvinsi(
      @RequestBody ProvinsiRequest provinsiRequest) {
    try {
      provinsiService.addProvinsi(provinsiRequest.getNewProvinsiName());
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(Boolean.TRUE));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @PutMapping("/{provinsiId}/update")
  public ResponseEntity<String> updateProvinsiName(
      @PathVariable Long provinsiId,
      @RequestBody ProvinsiRequest provinsiRequest) {
    try {
      Boolean updateStatus = provinsiService.updateProvinsi(provinsiId, provinsiRequest.getNewProvinsiName());
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(updateStatus));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @DeleteMapping("/{provinsiId}/delete")
  public ResponseEntity<String> deleteProvinsi(
      @PathVariable Long provinsiId) {
    try {
      provinsiService.deleteProvinsi(provinsiId);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(Boolean.TRUE));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }
}
