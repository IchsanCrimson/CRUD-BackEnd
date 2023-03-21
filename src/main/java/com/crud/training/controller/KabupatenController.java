package com.crud.training.controller;

import com.crud.training.model.request.KabupatenRequest;
import com.crud.training.model.response.KabupatenInfoResponse;
import com.crud.training.model.response.KabupatenResponse;
import com.crud.training.service.KabupatenService;
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
@RequestMapping(value = "/kabupaten")
@CrossOrigin(origins = "http://localhost:8080")
public class KabupatenController {
  private final KabupatenService kabupatenService;

  public KabupatenController(KabupatenService kabupatenService) {
    this.kabupatenService = kabupatenService;
  }

  @GetMapping("/getAllWithProvinsiId")
  public ResponseEntity<List<KabupatenResponse>> getKabupatenList(
      @RequestParam Long provinsiId) {
    List<KabupatenResponse> kabupatenResponseList = kabupatenService.getKabupatenList(provinsiId).stream()
        .map(kabupaten -> new KabupatenResponse(kabupaten.getKabupaten_id(), kabupaten.getName()))
        .collect(Collectors.toList());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(kabupatenResponseList);
  }

  @GetMapping("/{kabupatenId}/info")
  public ResponseEntity<KabupatenInfoResponse> getKabupatenInfo(
      @PathVariable Long kabupatenId) {
    KabupatenInfoResponse kabupatenInfoResponse = Optional.ofNullable(kabupatenService.getKabupaten(kabupatenId))
        .map(kabupaten -> new KabupatenInfoResponse(
            kabupaten.getKabupaten_id(),
            kabupaten.getName(),
            kabupaten.getProvinsi().getProvinsi_id(),
            kabupaten.getProvinsi().getName()))
        .orElseGet(() -> new KabupatenInfoResponse());

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(kabupatenInfoResponse);
  }

  @PostMapping("/create")
  public ResponseEntity<String> addKabupaten(
      @RequestBody KabupatenRequest kabupatenRequest) {
    try {
      kabupatenService.addKabupaten(
          kabupatenRequest.getProvinsiId(),
          kabupatenRequest.getNewKabupatenName());

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(Boolean.TRUE));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @PutMapping("/{kabupatenId}/update")
  public ResponseEntity<String> updateKabupaten(
      @PathVariable Long kabupatenId,
      @RequestBody KabupatenRequest kabupatenRequest) {
    try {
      Boolean updateStatus = kabupatenService.updateKabupaten(
          kabupatenId,
          kabupatenRequest.getNewKabupatenName(),
          kabupatenRequest.getProvinsiId());

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(String.valueOf(updateStatus));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @DeleteMapping("/{kabupatenId}/delete")
  public ResponseEntity<String> deleteKabupaten(
      @PathVariable Long kabupatenId) {
    try {
      kabupatenService.deleteKabupaten(kabupatenId);
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
