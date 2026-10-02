import { useState, useEffect } from 'react';
import api from '../services/api';

function Dashboard() {
  const [stats, setStats] = useState(null);
  const [recentExecutions, setRecentExecutions] = useState([]);

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const statsRes = await api.get('/dashboard');
      setStats(statsRes.data);

      const execRes = await api.get('/jobs/executions');
      setRecentExecutions(Array.isArray(execRes.data) ? execRes.data : []);
    } catch (err) {
      console.error(err);
      setRecentExecutions([]);
    }
  };

  if (!stats) return <div>Loading dashboard...</div>;

  return (
    <div>
      <h2 className="mb-4">Dashboard</h2>
      
      <div className="row mb-4">
        <div className="col-md-3">
          <div className="card text-white bg-primary mb-3">
            <div className="card-body">
              <h5 className="card-title">Total Invoices</h5>
              <p className="card-text display-4">{stats.totalInvoices}</p>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-warning mb-3">
            <div className="card-body">
              <h5 className="card-title">Due Today</h5>
              <p className="card-text display-4">{stats.dueToday}</p>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-danger mb-3">
            <div className="card-body">
              <h5 className="card-title">Overdue</h5>
              <p className="card-text display-4">{stats.overdue}</p>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-success mb-3">
            <div className="card-body">
              <h5 className="card-title">Paid</h5>
              <p className="card-text display-4">{stats.paid}</p>
            </div>
          </div>
        </div>
      </div>

      <h4>Recent Job Executions</h4>
      <div className="card">
        <div className="card-body p-0">
          <table className="table table-hover mb-0">
            <thead className="table-light">
              <tr>
                <th>ID</th>
                <th>Job Name</th>
                <th>Status</th>
                <th>Started</th>
                <th>Processed</th>
                <th>Success</th>
                <th>Failed</th>
              </tr>
            </thead>
            <tbody>
              {recentExecutions && recentExecutions.map(exec => (
                <tr key={exec.id}>
                  <td>{exec.id}</td>
                  <td>{exec.scheduledJob?.jobName}</td>
                  <td>
                    <span className={`badge bg-${exec.status === 'SUCCESS' ? 'success' : exec.status === 'FAILED' ? 'danger' : 'primary'}`}>
                      {exec.status}
                    </span>
                  </td>
                  <td>{new Date(exec.startedAt).toLocaleString()}</td>
                  <td>{exec.processedCount}</td>
                  <td>{exec.successCount}</td>
                  <td>{exec.failedCount}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
