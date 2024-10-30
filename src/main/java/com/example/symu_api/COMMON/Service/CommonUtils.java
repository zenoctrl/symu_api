package com.example.symu_api.COMMON.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CommonUtils {
    public static <T> Page<T> pageData(List<T> data, Pageable pageable) {
        if (data == null || data.isEmpty()) return new PageImpl<>(List.of(), pageable, 0);
        int pageStart = Math.toIntExact(pageable.getOffset());
        int pageEnd = Math.min(pageStart + pageable.getPageSize(), data.size());
        List<T> pagedData = data.subList(pageStart, pageEnd);
        return new PageImpl<>(pagedData, pageable, data.size());
    }
}
