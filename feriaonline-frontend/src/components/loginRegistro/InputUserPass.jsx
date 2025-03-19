import { NavLink } from "react-router-dom"

export const InputUserPass = () => {
    return (
        <div className="container mt-4 text-center">
            <form>
                <div className="mb-3">
                    <label for="exampleInputEmail1" className="form-label">Usuario</label>
                    <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" />
                </div>
                <div className="mb-3">
                    <label for="exampleInputPassword1" className="form-label">Contraseña</label>
                    <input type="password" className="form-control" id="exampleInputPassword1" />
                </div>
                <button type="submit" className="btn btn-primary">Ingresar</button>
            </form>
            <NavLink to='/registro' class="btn btn-secondary" href="#" role="button">Registrarse</NavLink>
        </div>
    )
}
