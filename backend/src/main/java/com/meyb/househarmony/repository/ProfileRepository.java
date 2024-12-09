package com.meyb.househarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meyb.househarmony.entity.Profile;

/**
 * Repository interface for Profile entity providing CRUD operations.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
