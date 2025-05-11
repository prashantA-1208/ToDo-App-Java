import axios from './axios'; // Assuming this wraps baseURL and token

export const getTasks = () => axios.get('/tasks');

export const createTask = (task) => axios.post('/task', task);
export const updateTask = (id, task) => axios.put(`/task/${id}`, task, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    }
  });

export const deleteTask = (id) => axios.delete(`/task/${id}`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    }
  });
export const getUser = () => axios.get('/username');