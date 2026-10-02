import { useState, useEffect } from 'react';
import api from '../services/api';

function ScheduledJobs() {
  const [jobs, setJobs] = useState([]);
  const [loadingAction, setLoadingAction] = useState(null);
  const [message, setMessage] = useState(null);

  useEffect(() => {
    fetchJobs();
  }, []);

  const fetchJobs = async () => {
    try {
      const response = await api.get('/jobs');
      setJobs(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  const toggleJob = async (id, currentEnabled) => {
    try {
      if (currentEnabled) {
        await api.patch(`/jobs/${id}/disable`);
      } else {
        await api.patch(`/jobs/${id}/enable`);
      }
      fetchJobs();
    } catch (err) {
      console.error(err);
    }
  };

  const runNow = async (id) => {
    setLoadingAction(id);
    setMessage(null);
    try {
      const res = await api.post(`/jobs/${id}/run`);
      setMessage({ type: 'success', text: `Job executed successfully. Status: ${res.data.status}` });
      fetchJobs();
    } catch (err) {
      setMessage({ type: 'danger', text: 'Error executing job.' });
    } finally {
      setLoadingAction(null);
    }
  };

  return (
    <div>
      <h2 className="mb-4">Scheduled Jobs</h2>
      
      {message && (
        <div className={`alert alert-${message.type} alert-dismissible fade show`} role="alert">
          {message.text}
          <button type="button" className="btn-close" onClick={() => setMessage(null)}></button>
        </div>
      )}

      <div className="card">
        <div className="card-body p-0">
          <table className="table table-hover mb-0">
            <thead className="table-light">
              <tr>
                <th>Job Name</th>
                <th>Cron Expression</th>
                <th>Status</th>
                <th>Last Run</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {jobs.map(job => (
                <tr key={job.id}>
                  <td>{job.jobName}</td>
                  <td>{job.cronExpression}</td>
                  <td>
                    <span className={`badge bg-${job.enabled ? 'success' : 'secondary'}`}>
                      {job.enabled ? 'ENABLED' : 'DISABLED'}
                    </span>
                  </td>
                  <td>{job.lastRunAt ? new Date(job.lastRunAt).toLocaleString() : 'Never'}</td>
                  <td>
                    <button 
                      className={`btn btn-sm ${job.enabled ? 'btn-outline-danger' : 'btn-outline-success'} me-2`}
                      onClick={() => toggleJob(job.id, job.enabled)}
                    >
                      {job.enabled ? 'Disable' : 'Enable'}
                    </button>
                    <button 
                      className="btn btn-sm btn-primary"
                      onClick={() => runNow(job.id)}
                      disabled={loadingAction === job.id}
                    >
                      {loadingAction === job.id ? 'Running...' : 'Run Now'}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ScheduledJobs;
