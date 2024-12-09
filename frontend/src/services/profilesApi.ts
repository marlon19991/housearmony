import { Profile } from '@/types/profile';

const API_BASE_URL = 'http://localhost:8080';

export async function getProfiles(): Promise<Profile[]> {
    const response = await fetch(`${API_BASE_URL}/api/profiles`);
    if (!response.ok) {
        throw new Error('Failed to fetch profiles');
    }
    return response.json();
}

export async function createProfile(profile: Omit<Profile, 'id'>): Promise<Profile> {
    const response = await fetch(`${API_BASE_URL}/api/profiles`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(profile),
    });
    if (!response.ok) {
        throw new Error('Failed to create profile');
    }
    return response.json();
}

export async function updateProfile(id: number, profile: Omit<Profile, 'id'>): Promise<Profile> {
    const response = await fetch(`${API_BASE_URL}/api/profiles/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(profile),
    });
    if (!response.ok) {
        throw new Error('Failed to update profile');
    }
    return response.json();
}

export async function deleteProfile(id: number): Promise<void> {
    const response = await fetch(`${API_BASE_URL}/api/profiles/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error('Failed to delete profile');
    }
}
