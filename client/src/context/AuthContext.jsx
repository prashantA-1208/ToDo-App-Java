import React, { createContext, useContext, useState, useEffect } from 'react';
import { getUser } from '../api/tasks';
import { isLoggedIn, signout } from '../utils/auth';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authenticated, setAuthenticated] = useState(isLoggedIn());
  const [user, setUser] = useState([]);

  // Fetch user on login state change
  useEffect(() => {
    const fetchUser = async () => {
      if (authenticated) {
        try {
          const res = await getUser();
          setUser(res.data);
        } catch (err) {
          console.error('Error fetching user:', err);
          setUser([]);
        }
      } else {
        setUser([]);
      }
    };

    fetchUser();
  }, [authenticated]);

  const login = () => {
    setAuthenticated(true);
  };

  const logout = () => {
    signout(); // <- clear token from storage
    setAuthenticated(false);
    setUser([]);
  };

  return (
    <AuthContext.Provider value={{ user, authenticated, setAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to use auth
export const useAuth = () => useContext(AuthContext);
