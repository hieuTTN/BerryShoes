package com.example.berryshoes.dto.response;

import com.example.berryshoes.entity.SanPham;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SanPhamSpecification implements Specification<SanPham> {

    private List<Integer> thuongHieuIds;
    private Integer minPrice;
    private Integer maxPrice;
    private List<Integer> deGiayIds;
    private List<Integer> chatLieuIds;

    @Override
    public Predicate toPredicate(Root<SanPham> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();

        if (thuongHieuIds != null && !thuongHieuIds.isEmpty()) {
            predicate = cb.and(predicate, root.get("thuongHieu").get("id").in(thuongHieuIds));
        }

        if (deGiayIds != null && !deGiayIds.isEmpty()) {
            predicate = cb.and(predicate, root.get("deGiay").get("id").in(deGiayIds));
        }

        if (chatLieuIds != null && !chatLieuIds.isEmpty()) {
            predicate = cb.and(predicate, root.get("chatLieu").get("id").in(chatLieuIds));
        }

        if (minPrice != null) {
            System.out.println("min: "+minPrice);
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("giaBan"), minPrice));
        }

        if (maxPrice != null) {
            System.out.println("max: "+maxPrice);
            predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("giaBan"), maxPrice));
        }
//        predicate = cb.and(predicate, cb.notEqual(root.get("deleted"), true));
        return predicate;
    }
}
