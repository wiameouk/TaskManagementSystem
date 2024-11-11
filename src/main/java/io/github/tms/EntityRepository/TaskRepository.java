package io.github.tms.EntityRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.tms.Entity.Task;

@Repository
public interface TaskRepository extends  JpaRepository<Task , UUID>{
    
}
