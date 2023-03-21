package com.crud.training.controller;

import com.crud.training.model.request.KecamatanRequest;
import com.crud.training.model.response.KecamatanInfoResponse;
import com.crud.training.model.response.KecamatanResponse;
import com.crud.training.service.KecamatanService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/kecamatan")
@CrossOrigin(origins = "http://localhost:8080")
public class KecamatanController {
  private final KecamatanService kecamatanService;

  public KecamatanController(KecamatanService kecamatanService) {
    this.kecamatanService = kecamatanService;
  }

  @GetMapping("/getAllWithKabupatenId")
  public ResponseEntity<List<KecamatanResponse>> getKecamatanList(
      @RequestParam Long kabupatenId) {
    List<KecamatanResponse> kecamatanResponseList = kecamatanService.getKecamatanList(kabupatenId).stream()
        .map(kecamatan -> new KecamatanResponse(kecamatan.getKecamatan_id(), kecamatan.getName()))
        .collect(Collectors.toList());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(kecamatanResponseList);
  }

  @GetMapping("/{kecamatanId}/info")
  public ResponseEntity<KecamatanInfoResponse> getKecamatanInfo(
      @PathVariable Long kecamatanId) {
    KecamatanInfoResponse kecamatanInfoResponse = Optional.ofNullable(kecamatanService.getKecamatan(kecamatanId))
        .map(kecamatan -> new KecamatanInfoResponse(
            kecamatan.getKecamatan_id(),
            kecamatan.getName(),
            kecamatan.getKabupaten().getKabupaten_id(),
            kecamatan.getKabupaten().getName(),
            kecamatan.getKabupaten().getProvinsi().getProvinsi_id(),
            kecamatan.getKabupaten().getProvinsi().getName()))
        .orElseGet(() -> new KecamatanInfoResponse());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(kecamatanInfoResponse);
  }

  @PostMapping("/create")
  public ResponseEntity<String> addKecamatan(
      @RequestBody KecamatanRequest kecamatanRequest) {
    try {
      kecamatanService.addKecamatan(
          kecamatanRequest.getKabupatenId(),
          kecamatanRequest.getNewKecamatanName());

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(Boolean.TRUE));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @PutMapping("/{kecamatanId}/update")
  public ResponseEntity<String> updateKecamatan(
      @PathVariable Long kecamatanId,
      @RequestBody KecamatanRequest kecamatanRequest) {
    try {
      Boolean updateStatus = kecamatanService.updateKecamatan(
          kecamatanId,
          kecamatanRequest.getNewKecamatanName(),
          kecamatanRequest.getKabupatenId());

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(updateStatus));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @DeleteMapping("/{kecamatanId}/delete")
  public ResponseEntity<String> deleteKecamatan(
      @PathVariable Long kecamatanId) {
    try {
      kecamatanService.deleteKecamatan(kecamatanId);
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
