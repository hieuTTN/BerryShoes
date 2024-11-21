package com.example.berryshoes.controller;

import com.example.berryshoes.dto.request.KhachHangRequest;
import com.example.berryshoes.dto.response.KhachHangResponse;
import com.example.berryshoes.dto.response.NhanVienResponse;
import com.example.berryshoes.entity.KhachHang;
import com.example.berryshoes.entity.NhanVien;
import com.example.berryshoes.service.KhachHangService;
import com.example.berryshoes.utils.UserUltis;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khachhang")
@RequiredArgsConstructor
public class KhachHangController {
    private final KhachHangService khachHangService;


    @Autowired
    private UserUltis userUltis;

    // Lấy tất cả khách hàng
    @GetMapping
    public ResponseEntity<List<KhachHang>> getAllKhachHang() {
        List<KhachHang> khachHangList = khachHangService.getAllKhachHang();
        return ResponseEntity.ok(khachHangList);
    }

    // Lấy khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(@PathVariable Integer id) {
        Optional<KhachHang> khachHang = khachHangService.getKhachHangById(id);
        return khachHang.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới khách hàng
    @PostMapping
    public ResponseEntity<KhachHang> createKhachHang(@RequestBody KhachHangRequest requestDTO) {
        KhachHang createdKhachHang = khachHangService.createKhachHang(requestDTO);
        return ResponseEntity.ok(createdKhachHang);
    }

    // Cập nhật khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Integer id, @RequestBody KhachHangRequest requestDTO) {
        KhachHang updatedKhachHang = khachHangService.updateKhachHang(id, requestDTO);
        return updatedKhachHang != null ? ResponseEntity.ok(updatedKhachHang) : ResponseEntity.notFound().build();
    }

    // Xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable Integer id) {
        khachHangService.deleteKhachHang(id);
        return ResponseEntity.noContent().build();
    }

    // Tìm khách hàng theo tài khoản
    @GetMapping("/taikhoan/{taiKhoan}")
    public ResponseEntity<KhachHang> getKhachHangByTaiKhoan(@PathVariable String taiKhoan) {
        KhachHang khachHang = khachHangService.findKhachHangByTaikhoan(taiKhoan);
        return khachHang != null ? ResponseEntity.ok(khachHang) : ResponseEntity.notFound().build();
    }

    // Tìm khách hàng theo tên hoặc số điện thoại
    @GetMapping("/search")
    public ResponseEntity<List<KhachHang>> findKhachHangByHoVaTenOrSoDienThoai(@RequestParam String hoVaTen, @RequestParam String soDienThoai) {
        List<KhachHang> khachHangList = khachHangService.findByHoVaTenOrSoDienThoai(hoVaTen, soDienThoai);
        return ResponseEntity.ok(khachHangList);
    }

    // Tìm khách hàng theo ngày sinh trong khoảng
    @GetMapping("/ngaysinh")
    public ResponseEntity<List<KhachHang>> findKhachHangByNgaySinhBetween(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<KhachHang> khachHangList = khachHangService.findKhachHangByNgaySinhBetween(startDate, endDate);
        return ResponseEntity.ok(khachHangList);
    }

    // Cập nhật mật khẩu cho khách hàng
    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestParam String taiKhoan, @RequestParam String newPassword) {
        khachHangService.updatePassword(taiKhoan, newPassword);
        return ResponseEntity.ok().build();
    }

    // Kiểm tra tồn tại số điện thoại
    @GetMapping("/exists/soDienThoai")
    public ResponseEntity<Boolean> existsBySoDienThoai(@RequestParam String soDienThoai) {
        boolean exists = khachHangService.existsBySoDienThoai(soDienThoai);
        return ResponseEntity.ok(exists);
    }

    // Kiểm tra tồn tại email
    @GetMapping("/exists/email")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        boolean exists = khachHangService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    // Tìm khách hàng theo số điện thoại
    @GetMapping("/search/soDienThoai")
    public ResponseEntity<KhachHang> searchKhachHangBySoDienThoai(@RequestParam String soDienThoai) {
        KhachHang khachHang = khachHangService.searchKhachHangBySoDienThoai(soDienThoai);
        return khachHang != null ? ResponseEntity.ok(khachHang) : ResponseEntity.notFound().build();
    }

    // Tìm khách hàng theo ID
    @GetMapping("/search/id")
    public ResponseEntity<KhachHang> searchKhachHangById(@RequestParam Integer id) {
        KhachHang khachHang = khachHangService.searchKhachHangById(id);
        return khachHang != null ? ResponseEntity.ok(khachHang) : ResponseEntity.notFound().build();
    }

    // Lấy tất cả khách hàng sắp xếp theo ID giảm dần
    @GetMapping("/order-by-id-desc")
    public ResponseEntity<List<KhachHang>> findAllByOrderByIdDesc() {
        List<KhachHang> khachHangList = khachHangService.findAllByOrderByIdDesc();
        return ResponseEntity.ok(khachHangList);
    }

    @PostMapping("/dang-dang-nhap")
    public ResponseEntity<?> nhanVienDangDangNhap(HttpServletRequest request) {
        KhachHang khachHang = userUltis.getLoggedInKhachHang(request);
        KhachHangResponse khachHangResponse = new KhachHangResponse();
        khachHangResponse.setMaKhachHang(khachHang.getMaKhachHang());
        khachHangResponse.setEmail(khachHang.getEmail());
        khachHangResponse.setAnh(khachHang.getAnh());
        khachHangResponse.setHoVaTen(khachHang.getHoVaTen());
        khachHangResponse.setGioiTinh(khachHang.getGioiTinh());
        khachHangResponse.setSoDienThoai(khachHang.getSoDienThoai());
        return new ResponseEntity<>(khachHangResponse, HttpStatus.OK);
    }
}
