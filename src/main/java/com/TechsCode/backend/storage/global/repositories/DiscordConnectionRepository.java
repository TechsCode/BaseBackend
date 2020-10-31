package com.techscode.backend.storage.global.repositories;

import com.techscode.backend.storage.global.entities.DiscordConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordConnectionRepository extends JpaRepository<DiscordConnection, Integer> {
    
    @Override
    public void delete(DiscordConnection discordConnection);
}
