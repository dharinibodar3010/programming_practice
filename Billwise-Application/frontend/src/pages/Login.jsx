import { useState } from 'react';
import api from '../services/api';

function Login({ setIsAuthenticated }) {
  const [username, setUsername] = useState('admin');
  const [password, setPassword] = useState('admin123');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/auth/login', { username, password });
      if (response.data.success) {
        localStorage.setItem('auth', 'true');
        setIsAuthenticated(true);
      }
    } catch (err) {
      setError('Invalid username or password');
    }
  };

  return (
    <div className="row justify-content-center mt-5">
      <div className="col-md-4">
        <div className="card shadow-sm">
          <div className="card-body p-4">
            <h3 className="text-center mb-4">BillWise Login</h3>
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleLogin}>
              <div className="mb-3">
                <label>Username</label>
                <input 
                  type="text" 
                  className="form-control" 
                  value={username}
                  onChange={e => setUsername(e.target.value)}
                  required 
                />
              </div>
              <div className="mb-4">
                <label>Password</label>
                <input 
                  type="password" 
                  className="form-control"
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                  required 
                />
              </div>
              <button type="submit" className="btn btn-primary w-100">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
