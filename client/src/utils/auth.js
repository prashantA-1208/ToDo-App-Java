export const isLoggedIn = () => !!localStorage.getItem('token');
export const signout = () => localStorage.removeItem('token');