import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': 'application/json'
    }
});

// User APIs
export const registerUser = (data) => api.post('/users/register', data);
export const getAllUsers = () => api.get('/users');

// Venue APIs
export const getAllVenues = () => api.get('/venues');
export const getActiveVenues = () => api.get('/venues/active');
export const createVenue = (data) => api.post('/venues', data);
export const updateVenue = (id, data) => api.put(`/venues/${id}`, data);
export const deleteVenue = (id) => api.delete(`/venues/${id}`);

// Booking APIs
export const createBooking = (data) => api.post('/bookings', data);
export const getAllBookings = () => api.get('/bookings');
export const getBookingsByCustomer = (id) => api.get(`/bookings/customer/${id}`);
export const updateBookingStatus = (id, status) =>
    api.put(`/bookings/${id}/status?status=${status}`);

// Venue Images APIs
export const addVenueImage = (data) => api.post('/venue-images', data);
export const getVenueImages = (venueId) => api.get(`/venue-images/venue/${venueId}`);

// Regulations APIs
export const addRegulation = (data) => api.post('/regulations', data);
export const getRegulations = (venueId) => api.get(`/regulations/venue/${venueId}`);

// Exception Rules APIs
export const addExceptionRule = (data) => api.post('/exceptions', data);
export const getExceptionRules = (venueId) => api.get(`/exceptions/venue/${venueId}`);

export default api;