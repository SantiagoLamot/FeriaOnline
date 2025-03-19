import { NavLink, Outlet } from "react-router-dom"

export const PerfilPage = () => {
    return (
        <div className="container mt-5 text-center" >
            <nav className="navbar navbar-light bg-light">
                <form className="container-fluid justify-content-start">
                    <div className="d-flex justify-content-center w-100">

                    <NavLink to="/perfil/descripcion" className="btn btn-outline-secondary me-2">Mi Perfil</NavLink>
                    <NavLink to="/perfil/misPublicaciones" className="btn btn-outline-secondary me-2">Mis Publicaciones</NavLink>
                    <NavLink to="/perfil/misCompras" className="btn btn-outline-secondary me-2">Mis Compras</NavLink>
                    <NavLink to="/perfil/misVentas" className="btn btn-outline-secondary me-2">Mis Ventas</NavLink>
                    </div>
                </form>
            </nav>
            <div className="container mt-3">
                <Outlet /> {/* Aquí se renderizarán las subpáginas */}
            </div>
        </div>
    )
}
