import { useState, useEffect } from 'react';
import api from '../services/api';

function InvoiceList() {
  const [invoices, setInvoices] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showNewCustomerForm, setShowNewCustomerForm] = useState(false);
  const [newCustomer, setNewCustomer] = useState({ name: '', email: '', phone: '' });

  const [newInvoice, setNewInvoice] = useState({
    invoiceNumber: '',
    customer: { id: '' },
    invoiceDate: '',
    dueDate: '',
    amount: '',
    status: 'NEW',
    description: '',
    reference: ''
  });

  useEffect(() => {
    fetchInvoices();
    fetchCustomers();
  }, []);

  const fetchInvoices = async () => {
    try {
      const response = await api.get('/invoices');
      setInvoices(Array.isArray(response.data) ? response.data : []);
    } catch (err) {
      console.error(err);
      setInvoices([]);
    }
  };

  const fetchCustomers = async () => {
    try {
      const response = await api.get('/customers');
      setCustomers(Array.isArray(response.data) ? response.data : []);
    } catch (err) {
      console.error(err);
      setCustomers([]);
    }
  };

  const changeStatus = async (id, newStatus) => {
    try {
      await api.patch(`/invoices/${id}/status`, { status: newStatus });
      fetchInvoices();
    } catch (err) {
      console.error(err);
    }
  };

  const markPaid = async (id) => {
    try {
      await api.patch(`/invoices/${id}/mark-paid`);
      fetchInvoices();
    } catch (err) {
      console.error(err);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      let finalCustomerId = newInvoice.customer.id;
      
      if (showNewCustomerForm) {
        const custRes = await api.post('/customers', newCustomer);
        finalCustomerId = custRes.data.id;
        fetchCustomers();
      }

      await api.post('/invoices', { ...newInvoice, customer: { id: finalCustomerId } });
      setShowModal(false);
      setShowNewCustomerForm(false);
      fetchInvoices();
      // Reset form
      setNewInvoice({
        invoiceNumber: '',
        customer: { id: '' },
        invoiceDate: '',
        dueDate: '',
        amount: '',
        status: 'NEW',
        description: '',
        reference: ''
      });
      setNewCustomer({ name: '', email: '', phone: '' });
    } catch (err) {
      alert("Error creating invoice: " + (err.response?.data?.error || err.message));
    }
  };

  return (
    <div>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>Invoices</h2>
        <button className="btn btn-primary" onClick={() => setShowModal(!showModal)}>
          + Create Invoice
        </button>
      </div>

      {showModal && (
        <div className="card mb-4 shadow-sm border-primary">
          <div className="card-header bg-primary text-white">Create New Invoice</div>
          <div className="card-body">
            <form onSubmit={handleCreate}>
              <div className="row g-3">
                <div className="col-md-3">
                  <label className="form-label">Invoice Number</label>
                  <input type="text" className="form-control" required 
                         value={newInvoice.invoiceNumber} 
                         onChange={e => setNewInvoice({...newInvoice, invoiceNumber: e.target.value})} />
                </div>
                
                {/* Customer Section */}
                <div className="col-md-6">
                  <div className="d-flex justify-content-between">
                    <label className="form-label">Customer</label>
                    <a href="#" className="text-decoration-none small" onClick={(e) => { e.preventDefault(); setShowNewCustomerForm(!showNewCustomerForm); }}>
                      {showNewCustomerForm ? 'Use Existing Customer' : '+ New Customer'}
                    </a>
                  </div>
                  
                  {!showNewCustomerForm ? (
                    <select className="form-select" required
                            value={newInvoice.customer.id} 
                            onChange={e => setNewInvoice({...newInvoice, customer: {id: e.target.value}})}>
                      <option value="">Select Customer...</option>
                      {customers && customers.map(c => (
                        <option key={c.id} value={c.id}>{c.name}</option>
                      ))}
                    </select>
                  ) : (
                    <div className="row g-2">
                      <div className="col-12"><input type="text" className="form-control form-control-sm" placeholder="Customer Name" required value={newCustomer.name} onChange={e => setNewCustomer({...newCustomer, name: e.target.value})} /></div>
                      <div className="col-6"><input type="email" className="form-control form-control-sm" placeholder="Email" required value={newCustomer.email} onChange={e => setNewCustomer({...newCustomer, email: e.target.value})} /></div>
                      <div className="col-6"><input type="text" className="form-control form-control-sm" placeholder="Phone" value={newCustomer.phone} onChange={e => setNewCustomer({...newCustomer, phone: e.target.value})} /></div>
                    </div>
                  )}
                </div>

                <div className="col-md-3">
                  <label className="form-label">Amount ($)</label>
                  <input type="number" step="0.01" className="form-control" required
                         value={newInvoice.amount} 
                         onChange={e => setNewInvoice({...newInvoice, amount: e.target.value})} />
                </div>
                
                <div className="col-md-3">
                  <label className="form-label">Status</label>
                  <select className="form-select" required
                          value={newInvoice.status} 
                          onChange={e => setNewInvoice({...newInvoice, status: e.target.value})}>
                    <option value="NEW">NEW</option>
                    <option value="PENDING">PENDING</option>
                  </select>
                </div>
                <div className="col-md-3">
                  <label className="form-label">Invoice Date</label>
                  <input type="date" className="form-control" required
                         value={newInvoice.invoiceDate} 
                         onChange={e => setNewInvoice({...newInvoice, invoiceDate: e.target.value})} />
                </div>
                <div className="col-md-3">
                  <label className="form-label">Due Date</label>
                  <input type="date" className="form-control" required
                         value={newInvoice.dueDate} 
                         onChange={e => setNewInvoice({...newInvoice, dueDate: e.target.value})} />
                </div>
                <div className="col-md-3">
                  <label className="form-label">Reference</label>
                  <input type="text" className="form-control" 
                         value={newInvoice.reference} 
                         onChange={e => setNewInvoice({...newInvoice, reference: e.target.value})} />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Description</label>
                  <input type="text" className="form-control" 
                         value={newInvoice.description} 
                         onChange={e => setNewInvoice({...newInvoice, description: e.target.value})} />
                </div>
                <div className="col-12 mt-3">
                  <button type="submit" className="btn btn-success me-2">Save Invoice</button>
                  <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>Cancel</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      )}

      <div className="card">
        <div className="card-body p-0">
          <table className="table table-hover mb-0">
            <thead className="table-light">
              <tr>
                <th>Invoice #</th>
                <th>Customer</th>
                <th>Amount</th>
                <th>Due Date</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {invoices && invoices.map(invoice => (
                <tr key={invoice.id}>
                  <td>{invoice.invoiceNumber}</td>
                  <td>{invoice.customer?.name}</td>
                  <td>${invoice.amount}</td>
                  <td>{invoice.dueDate}</td>
                  <td>
                    <span className={`badge bg-${invoice.status === 'PAID' ? 'success' : invoice.status === 'OVERDUE' ? 'danger' : 'warning'}`}>
                      {invoice.status}
                    </span>
                  </td>
                  <td>
                    {invoice.status !== 'PAID' && (
                      <button className="btn btn-sm btn-success me-2" onClick={() => markPaid(invoice.id)}>Mark Paid</button>
                    )}
                    <select 
                      className="form-select form-select-sm d-inline-block w-auto"
                      value={invoice.status}
                      onChange={(e) => changeStatus(invoice.id, e.target.value)}
                    >
                      <option value="NEW">NEW</option>
                      <option value="PENDING">PENDING</option>
                      <option value="DUE">DUE</option>
                      <option value="OVERDUE">OVERDUE</option>
                      <option value="PAID">PAID</option>
                    </select>
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

export default InvoiceList;
