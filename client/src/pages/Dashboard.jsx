import { useState, useEffect } from 'react';
import { getUser } from '../api/tasks';
import TaskList from '../components/TaskList';
import CreateTaskForm from '../components/CreateTaskForm';

export default function Dashboard() {

  const [user, setUser] = useState([]);

  const loadUser = async () => {
    try {
      const res = await getUser();
      setUser(res.data || []); // fallback to empty array
    } catch (error) {
      console.error('Failed to load user:', error);
      setUser([]); // fallback to avoid crash
    }
  };
  

  useEffect(() => {
    loadUser();
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-xl mb-4">{user.username ? `${user.username}'s Task` : `Please Login To See Your Task` }</h1>
      <CreateTaskForm  />
      <TaskList />
    </div>
  );
}