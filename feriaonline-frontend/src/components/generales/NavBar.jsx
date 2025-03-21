import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";

export const NavBar = () => {

    // Estado para simular si hay un usuario registrado
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const navigate = useNavigate()

    // Función para simular el inicio de sesión
    const handleLogin = () => {
        setIsLoggedIn(true)
        navigate("/login")
    }

    // Función para simular el cierre de sesión
    const handleLogout = () => {
        setIsLoggedIn(false)
        navigate("/productos") //A futuro se puede redirigir al inicio de la pagina.
    }

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <NavLink to='/' className="navbar-brand">Feria Online</NavLink>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav me-auto">
                        <li className="nav-item">
                            <NavLink to='/productos' className="nav-link active" aria-current="page">Productos</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to='/vender' className="nav-link">Vender</NavLink>
                        </li>
                    </ul>
                    {/* Alineación a la derecha con "ms-auto" */}
                    <div className="d-flex">
                        {isLoggedIn ? (
                            <>
                                <NavLink to='/perfil' className="nav-link d-flex align-items-center fs-4 text-dark">
                                    <i className="bi bi-person-circle me-2 fs-3"></i> Perfil
                                </NavLink>
                                <button className="btn btn-danger ms-3" onClick={handleLogout}>Cerrar Sesión</button>

                            </>
                        ) : (
                            <button className="btn btn-success ms-2" onClick={handleLogin}>Iniciar sesión</button>
                        )}
                    </div>
                </div>
            </div>
        </nav>

    )
}
