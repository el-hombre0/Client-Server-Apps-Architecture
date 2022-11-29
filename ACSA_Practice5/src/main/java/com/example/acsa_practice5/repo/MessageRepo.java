package com.example.acsa_practice5.repo;

import com.example.acsa_practice5.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
