import { NavLink } from "react-router-dom"

export const NavBar = () => {
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
                        <li className="nav-item">
                            <NavLink to='/perfil' className="nav-link" href="#">Perfil</NavLink>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}
