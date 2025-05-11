import { useAuth } from '../context/AuthContext';
import { Link, useLocation  } from 'react-router-dom';


const Header = () => {
  const { user, logout } = useAuth();
  const location = useLocation(); // 👈 Get current route

  return (
    <header >
      <h1 className="header-h1">📝 ToDo App {user.username ? `Hello ${user.username}` : `Please Login To See Your Task` }!</h1>

      <div className="header-button">
        {user.username ? (
          <button
            onClick={logout}
            className="link-button"
          >
            Logout
          </button>
        ) : (
          <>
            {location.pathname !== '/login' && (
              <Link to="/login" className="link-button">
                Login
              </Link>
            )}
            
            {location.pathname !== '/signup' && (
              <Link to="/signup" className="link-button">
                SignUp
              </Link>
            )}
          </>
        )}
      </div>
    </header>
  );
};

export default Header;


