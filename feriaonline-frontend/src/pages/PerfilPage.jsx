import { NavLink, Outlet } from "react-router-dom"

export const PerfilPage = () => {
    return (
        <div>
            <nav className="navbar navbar-light bg-light">
                <form className="container-fluid justify-content-start">
                    <NavLink to="/perfil/descripcion" className="btn btn-outline-success me-2">Datos</NavLink>
                    <NavLink to="/perfil/misPublicaciones" className="btn btn-outline-secondary">Mis Publicaciones</NavLink>
                    <NavLink to="/perfil/misCompras" className="btn btn-outline-secondary">Mis Compras</NavLink>
                    <NavLink to="/perfil/misVentas" className="btn btn-outline-secondary">Mis Ventas</NavLink>
                </form>
            </nav>
            <div className="container mt-3">
                <Outlet /> {/* Aquí se renderizarán las subpáginas */}
            </div>
        </div>
    )
}
