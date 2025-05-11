import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import Dashboard from './pages/Dashboard';
import Header from './components/Header';
import Footer from './components/Footer';
import { useAuth } from './context/AuthContext';

export default function App() {

  const { authenticated } = useAuth();

  return (
    <BrowserRouter>
    <div className="app-container">
    <Header />
    <main>
      <Routes>
        <Route path="/" element={<Navigate to={authenticated ? '/dashboard' : '/login'} />} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/dashboard" element={authenticated ? <Dashboard /> : <Navigate to="/login" />} />
      </Routes>
      </main>
    <Footer/>
    </div>
    </BrowserRouter>
  );
}