/**
 * API Configuration
 * Centralized configuration for API endpoints
 */

// Get API base URL from environment variable
// Falls back to localhost for development
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';

export const API_ENDPOINTS = {
  profiles: '/api/profiles',
  // Add more endpoints as your API grows
  // tasks: '/api/tasks',
  // bills: '/api/bills',
  // etc.
} as const;
