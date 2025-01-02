package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Pagination을 사용하는 메서드에 Fetch Join 적용 시 Pagination이 메모리 상에서 수행됨
    // 이는 OutOfMemory 문제를 불러올 수 있으므로 JPA에서 사전에 예외를 발생시켜 수행되지 못하게 함
    // 따라서 EntityGraph를 적용하는 것이 적합하다고 판단함
    @EntityGraph(attributePaths = {"comments", "managers"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    int countById(Long todoId);
}
