import { useState } from "react";
import { NavLink } from "react-router-dom";

export const NavBar = () => {

    // Estado para simular si hay un usuario registrado
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // Función para simular el inicio de sesión
    const handleLogin = () => {
        setIsLoggedIn(true);
    };

    // Función para simular el cierre de sesión
    const handleLogout = () => {
        setIsLoggedIn(false);
    };


    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <NavLink to='/' className="navbar-brand" href="#">Feria Online</NavLink>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <NavLink to='/productos' className="nav-link active" aria-current="page" href="#">Productos</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to='/vender' className="nav-link" href="#">Vender</NavLink>
                        </li>
                        {/* Renderizado condicional */}
                        {isLoggedIn ? (
                            <>
                                <NavLink to='/perfil' className="nav-link" href="#">Perfil</NavLink>
                                <button onClick={handleLogout}>Cerrar Sesión</button>
                            </>
                        ) : (
                            <>
                            <NavLink to='/login' className="nav-link" href="#">Iniciar Sesión</NavLink>
                            <button onClick={handleLogin}>test Iniciar sesion</button>
                            </>
                        )}
                        <li className="nav-item">
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}
