package com.meyb.househarmony.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meyb.househarmony.entity.Profile;
import com.meyb.househarmony.service.ProfileService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing Profile entities.
 * Provides endpoints for CRUD operations on profiles.
 */
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ProfileController {
    
    private final ProfileService profileService;
    
    /**
     * GET /api/profiles : Get all profiles
     * @return List of profiles with status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.listAllProfiles();
        return ResponseEntity.ok(profiles);
    }
    
    /**
     * GET /api/profiles/{id} : Get profile by id
     * @param id the id of the profile to retrieve
     * @return the ResponseEntity with status 200 (OK) and the profile, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }
    
    /**
     * POST /api/profiles : Create a new profile
     * @param profile the profile to create
     * @return the ResponseEntity with status 201 (Created) and the new profile
     */
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile newProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
    }
    
    /**
     * PUT /api/profiles/{id} : Update an existing profile
     * @param id the id of the profile to update
     * @param profile the profile to update
     * @return the ResponseEntity with status 200 (OK) and the updated profile
     */
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updatedProfile = profileService.updateProfile(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }
    
    /**
     * DELETE /api/profiles/{id} : Delete a profile
     * @param id the id of the profile to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
