package com.techscode.backend.storage.global.repositories;

import com.techscode.backend.storage.global.entities.SpigotConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpigotConnectionRepository extends JpaRepository<SpigotConnection, Integer> {
}
