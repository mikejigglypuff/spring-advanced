package org.example.expert.domain.common.service;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.springframework.data.jpa.repository.JpaRepository;

// 서비스 계층에서 관리되던 Exception 메시지의 관리를 맡는 역할
// 주로 Entity 조회 관련 예외 메시지가 많이 중복되기에 이를 관리
public class EntityLookupService {
    public static <T> T findEntityById(JpaRepository<T, Long> repository, long id, Class<T> classType) {
        return repository.findById(id)
            .orElseThrow(() -> new InvalidRequestException(classType.getSimpleName() + " not found"));
    }
}
