import axios from './axios';

export const login = (data) => axios.post('/login', data);
export const signup = (data) => axios.post('/signup', data);