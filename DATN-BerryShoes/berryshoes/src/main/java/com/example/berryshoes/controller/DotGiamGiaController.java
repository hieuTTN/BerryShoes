package com.example.berryshoes.controller;

import com.example.berryshoes.dto.request.DotGiamGiaRequest;
import com.example.berryshoes.dto.response.DotGiamGiaResponse;
import com.example.berryshoes.entity.DotGiamGia;
import com.example.berryshoes.service.DotGiamGiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dot-giam-gia")
@RequiredArgsConstructor
public class DotGiamGiaController {

    private final DotGiamGiaService dotGiamGiaService;

    // Lấy tất cả các đợt giảm giá
    @GetMapping
    public ResponseEntity<List<DotGiamGiaResponse>> getAllDotGiamGia() {
        List<DotGiamGiaResponse> responses = dotGiamGiaService.getAllDotGiamGia();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // Lấy đợt giảm giá theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DotGiamGiaResponse> getDotGiamGiaById(@PathVariable Integer id) {
        Optional<DotGiamGiaResponse> response = dotGiamGiaService.getDotGiamGiaById(id);
        return response.map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo mới một đợt giảm giá
    @PostMapping
    public ResponseEntity<DotGiamGiaResponse> createDotGiamGia(@RequestBody DotGiamGiaRequest requestDTO) {
        DotGiamGiaResponse response = dotGiamGiaService.createDotGiamGia(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật thông tin đợt giảm giá
    @PutMapping("/{id}")
    public ResponseEntity<DotGiamGiaResponse> updateDotGiamGia(@PathVariable Integer id, @RequestBody DotGiamGiaRequest requestDTO) {
        DotGiamGiaResponse response = dotGiamGiaService.updateDotGiamGia(id, requestDTO);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa đợt giảm giá theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDotGiamGia(@PathVariable Integer id) {
        dotGiamGiaService.deleteDotGiamGia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Tìm kiếm đợt giảm giá theo điều kiện và phân trang
    @GetMapping("/search")
    public ResponseEntity<Page<DotGiamGia>> findAllOrderByNgayTaoDESC(
            @RequestParam(required = false) String keySearch,
            @RequestParam(required = false) Timestamp tungaySearch,
            @RequestParam(required = false) Timestamp denngaySearch,
            @RequestParam(required = false) Integer ttSearch,
            Pageable pageable) {

        Page<DotGiamGia> page = dotGiamGiaService.findAllOrderByNgayTaoDESC(keySearch, tungaySearch, denngaySearch, ttSearch, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    // Lấy đợt giảm giá theo ID
    @GetMapping("/find/{id}")
    public ResponseEntity<DotGiamGia> findDotGiamGiaById(@PathVariable Integer id) {
        DotGiamGia dotGiamGia = dotGiamGiaService.findDotGiamGiaById(id);
        return dotGiamGia != null ? new ResponseEntity<>(dotGiamGia, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Lấy đợt giảm giá mới nhất
    @GetMapping("/latest")
    public ResponseEntity<DotGiamGia> findFirstByOrderByNgayTaoDesc() {
        DotGiamGia dotGiamGia = dotGiamGiaService.findFirstByOrderByNgayTaoDesc();
        return dotGiamGia != null ? new ResponseEntity<>(dotGiamGia, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
