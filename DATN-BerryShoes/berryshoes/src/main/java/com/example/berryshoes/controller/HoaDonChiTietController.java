package com.example.berryshoes.controller;

import com.example.berryshoes.entity.HoaDon;
import com.example.berryshoes.entity.HoaDonChiTiet;
import com.example.berryshoes.entity.SanPhamChiTiet;
import com.example.berryshoes.exception.MessageException;
import com.example.berryshoes.repository.HoaDonChiTietRepository;
import com.example.berryshoes.repository.HoaDonRepository;
import com.example.berryshoes.repository.SanPhamChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
@RequiredArgsConstructor
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping("/find-by-hoa-don")
    public ResponseEntity<?> findByHoaDon(@RequestParam Integer hoaDonId){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findHoaDonChiTietByIdHoaDon(hoaDonId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/them-vao-hoa-don")
    public ResponseEntity<?> addChiTiet(@RequestParam Integer hoaDonId, @RequestParam Integer chiTietSp, @RequestParam Integer soLuong){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findHoaDonChiTietByIdHoaDon(hoaDonId);
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(chiTietSp).get();
        if(soLuong > sanPhamChiTiet.getSoLuong()){
            throw new MessageException("Số lượng không được vượt quá "+sanPhamChiTiet.getSoLuong());
        }
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();
        Boolean check = false;
        for(HoaDonChiTiet h : list){
            if(h.getSanPhamChiTiet().getId() == chiTietSp){
                check = true;
                h.setSoLuong(soLuong.shortValue());
                hoaDonChiTietRepository.save(h);
                break;
            }
        }
        if(check == false){
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSoLuong(soLuong.shortValue());
            hoaDonChiTiet.setTrangThai(1);
            hoaDonChiTiet.setGiaSanPham(new BigDecimal(sanPhamChiTiet.getGiaTien()));
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateSoLuong")
    public ResponseEntity<?> updateSoLuong(@RequestParam Integer id, @RequestParam Integer soLuong){
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(id).get();
        if(soLuong > hoaDonChiTiet.getSanPhamChiTiet().getSoLuong()){
            throw new MessageException("Số lượng không được vượt quá "+hoaDonChiTiet.getSanPhamChiTiet().getSoLuong());
        }
        hoaDonChiTiet.setSoLuong(soLuong.shortValue());
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/xoa-chi-tiet-don-cho")
    public ResponseEntity<?> xoa(@RequestParam Integer id){
        hoaDonChiTietRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
