export default function SignupForm({ username, email, password, onUsernameChange, onEmailChange, onPasswordChange, onSubmit, error }) {
    return (
      <div className="container">
        <h3 className="form-name">SignUp Please</h3>
      <form onSubmit={onSubmit} className="p-4">
        <input placeholder="Username" value={username} onChange={(e) => onUsernameChange(e.target.value)} className="block mb-2 border px-2 py-1" />
        <input placeholder="Email" value={email} onChange={(e) => onEmailChange(e.target.value)} className="block mb-2 border px-2 py-1" />
        <input placeholder="Password" type="password" value={password} onChange={(e) => onPasswordChange(e.target.value)} className="block mb-2 border px-2 py-1" />
        <button type="submit" className="bg-blue-500 text-white px-4 py-1">Sign Up</button>
        {error && (
        <p style={{ color: 'red', marginTop: '0.5rem' }}>{error}</p>
      )}
      </form>
      </div>
    );
  }