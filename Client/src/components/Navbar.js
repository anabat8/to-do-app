import { useNavigate, Link } from "react-router-dom";

const Navbar = () => {
    const navigate = useNavigate();

    const handleLogout = async (e) => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('username');
        navigate('/authenticate');
        navigate(0);
    };

    return(
        <nav className=" bg-secondary-800 p-6">
            <div className="flex container items-center justify-between mx-auto">
                <div className="flex items-center flex-shrink-0 text-white mr-6">
                    <span className="font-semibold text-xl text-white tracking-tight">To Do List App</span>
                </div>
                <div className="flex align-middle justify-center">
                    <div className="text-lg">
                        <Link className="block text-secondary-300 hover:text-white mr-4"
                            onClick={handleLogout}>
                            Log out
                        </Link>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;