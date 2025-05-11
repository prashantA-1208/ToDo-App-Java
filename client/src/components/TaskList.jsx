import { useEffect, useState } from 'react';
import { getTasks, deleteTask, updateTask } from '../api/tasks';

export default function TaskList() {
  const [tasks, setTasks] = useState([]);

  const loadTasks = async () => {
    try {
      const res = await getTasks();
      setTasks(res.data || []); // fallback to empty array
    } catch (error) {
      console.error('Failed to load tasks:', error);
      setTasks([]); // fallback to avoid crash
    }
  };
  

  useEffect(() => {
    loadTasks();
  }, []);

  const handleDelete = async (id) => {
    await deleteTask(id);
    loadTasks();
  };

  const handleToggle = async (task) => {
    await updateTask(task.taskId, { ...task, completed: !task.completed });
    loadTasks();
  };

  return (
    <ul className='container'>
      {tasks.map((task) => (
        <li key={task.taskId} >
          <span className={task.completed ? 'line-through' : ''}>{task.title}</span>
          <div className="flex gap-2">
            <button onClick={() => handleToggle(task)} className={task.completed ? 'button-Completed' : 'button-Pending'}> {task.completed ? 'Completed' : 'Pending'}</button>
            <button onClick={() => handleDelete(task.taskId)} className="text-sm text-red-600">Delete</button>
          </div>
        </li>
      ))}
    </ul>
  );
}
