package com.example.berryshoes.repository;

import com.example.berryshoes.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT h FROM HoaDon h WHERE h.maHoaDon =?1 OR h.email =?1 OR h.soDienThoai =?1 ORDER BY h.ngayTao DESC")
    List<HoaDon> findHoaDonByMaOrSdtOrEmail(String inputSearch);
    @Query("SELECT h FROM HoaDon h where h.id =?1")
    HoaDon findHoaDonById(Integer id);

    @Query("SELECT h FROM HoaDon h where h.id =?1")
    Optional<HoaDon> findById(Integer id);

    @Query("SELECT h FROM HoaDon h where h.khachHang.id =?1 ORDER BY h.id DESC")
    List<HoaDon> findAllByKhachHang(Integer id);
    @Query("SELECT h FROM HoaDon h WHERE h.trangThai =?1 AND h.khachHang.id =?2 ORDER BY h.id DESC")
    List<HoaDon> findHoaDonByTrangThaiAndKhachhang(Integer trangThai, Integer id);

    Page<HoaDon> findAllByTrangThaiAndLoaiHoaDonAndNgayTaoGreaterThanEqualAndNgayTaoLessThanEqual(
            Integer trangThai, Boolean loaihd, Date tu, Date den, Pageable p);
    @Query("SELECT h FROM HoaDon h ORDER BY h.id DESC")
    Page<HoaDon> findAlls(Pageable p);

    @Query("SELECT h FROM HoaDon h where h.trangThai=?1 ORDER BY h.id DESC ")
    Page<HoaDon> findAllByTrangthai(Integer trangThai, Pageable p);

    Long countAllByTrangThai(Integer trangThai);

    Page<HoaDon> findAllByLoaiHoaDonAndNgayTaoGreaterThanEqualAndNgayTaoLessThanEqual(Boolean loaihd, Date tu, Date den, Pageable p);

    Page<HoaDon> findAllByTrangThaiAndNgayTaoGreaterThanEqualAndNgayTaoLessThanEqual(Integer trangThai, Date tu, Date den, Pageable p);

    Page<HoaDon> findAllByTrangThaiAndLoaiHoaDon(Integer trangThai, Boolean loaihd, Pageable p);

    Page<HoaDon> findAllByNgayTaoGreaterThanEqualAndNgayTaoLessThanEqual(Date tu, Date den, Pageable p);

    Page<HoaDon> findAllByLoaiHoaDon(Boolean loaihd, Pageable p);

    List<HoaDon> findAllById(Integer id);

    List<HoaDon> findAllByTrangThaiAndLoaiHoaDon(Integer id, Boolean loadHD);

    @Query("SELECT h FROM HoaDon h ORDER BY h.id DESC")
    List<HoaDon> timHDGanNhat();

    @Query("SELECT h FROM HoaDon h  WHERE h.maHoaDon =?1")
    HoaDon timHDTheoMaHD(String mahd);

    boolean existsById(Integer id);

    @Query("select h from HoaDon h where h.trangThai = ?1")
    Page<HoaDon> findByTrangThai(Integer trangthai, Pageable pageable);
}
