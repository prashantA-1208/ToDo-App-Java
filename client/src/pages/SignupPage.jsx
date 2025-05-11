import { useState } from 'react';
import { signup } from '../api/auth';
import { useNavigate } from 'react-router-dom';
import SignupForm from '../components/SignupForm';

export default function SignupPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [username, setUsername] = useState('');
  const navigate = useNavigate();
  const [error, setError] = useState(''); // for error message


  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
      if(!username){
        setError("Username can't be empty")
        return;
      }
      if(!email){
        setError("Email can't be empty")
        return;
      }
      if(!password){
        setError("Password can't be empty")
        return;
      }
      await signup({ username, email, password });
      navigate('/login');
    }catch(err){
      if (err.response && err.response.status === 400) {
        setError('Email already exists');
      } else {
        setError('Something went wrong. Please try again later.');
      }
    }

    
    
  };

  return (
    <SignupForm
      username={username}
      email={email}
      password={password}
      onUsernameChange={setUsername}
      onEmailChange={setEmail}
      onPasswordChange={setPassword}
      onSubmit={handleSubmit}
      error={error}
    />
  );
}
