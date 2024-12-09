package com.meyb.househarmony.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meyb.househarmony.entity.Profile;
import com.meyb.househarmony.exception.ProfileNotFoundException;
import com.meyb.househarmony.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing Profile entities.
 * Handles business logic for profile operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    
    /**
     * Retrieves all profiles from the database.
     * @return List of all profiles
     */
    public List<Profile> listAllProfiles() {
        return profileRepository.findAll();
    }
    
    /**
     * Retrieves a profile by its ID.
     * @param id The ID of the profile to retrieve
     * @return The found profile
     * @throws ProfileNotFoundException if profile is not found
     */
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
            .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));
    }
    
    /**
     * Creates a new profile.
     * @param profile The profile to create
     * @return The created profile with generated ID
     */
    public Profile createProfile(Profile profile) {
        if (profile.getName() == null || profile.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Profile name cannot be empty");
        }
        return profileRepository.save(profile);
    }
    
    /**
     * Updates an existing profile.
     * @param id The ID of the profile to update
     * @param profileDetails The updated profile details
     * @return The updated profile
     * @throws ProfileNotFoundException if profile is not found
     */
    public Profile updateProfile(Long id, Profile profileDetails) {
        Profile profile = getProfileById(id);
        profile.setName(profileDetails.getName());
        profile.setIcon(profileDetails.getIcon());
        return profileRepository.save(profile);
    }
    
    /**
     * Deletes a profile by its ID.
     * @param id The ID of the profile to delete
     * @throws ProfileNotFoundException if profile is not found
     */
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Profile not found with id: " + id);
        }
        profileRepository.deleteById(id);
    }
}
