import React, { useEffect } from 'react';
import './App.css';
import UsernamePasswordForm from './pages/UsernamePasswordForm';
import MainPage from './pages/MainPage';
import AddActivity from './pages/AddActivity';
import Navbar from './components/Navbar';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
  useLocation,
  useNavigate
} from "react-router-dom";

function App() {
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const isAuthenticated = !!sessionStorage.getItem('token');
    if (!isAuthenticated && location.pathname !== '/authenticate' && location.pathname !== '/register') {
      navigate('/authenticate');
    }
  }, [location.pathname]);

  return (
    <Routes>
      <Route path="/" element={
        <div>  
          <Navbar/>
          <div className="w-100 min-h-screen bg-white">
            <div className="container mx-auto">
              <h1 className="lg:text-5xl text-3xl font-poppins text-black leading-10 text-center py-10 font-semibold drop-shadow">
                  {(window.location.pathname !== "/authenticate" && window.location.pathname !== "/register" ) && "Welcome " + sessionStorage.getItem('username')} 
              </h1>
            </div>
            <Outlet/>
          </div>
        </div>
      }>
        <Route path="authenticate" element={<UsernamePasswordForm isLogin={true}/>}/>
        <Route path="register" element={<UsernamePasswordForm isLogin={false}/>}/>
        <Route path="main" element={<MainPage/>}/>
        <Route path="addActivity" element={<AddActivity/>}/>
      </Route>
    </Routes>
  );
}

export default App;
