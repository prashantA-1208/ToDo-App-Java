import { useState } from 'react';
import { createTask } from '../api/tasks';

export default function CreateTaskForm (){
  
  const [title, setTitle] = useState('');
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      await createTask({ title, completed: false });
      setTitle('');
      onTaskCreated();
    };

    const onTaskCreated = () =>{
      window.location.reload()
    }
  return (
      <div className="container">
        <form onSubmit={handleSubmit} className="">
      <input
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Add task"
      />
      <button type="submit" className="bg-blue-500 text-white px-4 py-1 rounded">Add</button>
    </form>
    </div>
    );
}