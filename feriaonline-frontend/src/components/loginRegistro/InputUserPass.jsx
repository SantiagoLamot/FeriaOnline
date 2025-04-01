import { NavLink } from "react-router-dom"

export const InputUserPass = () => {
    return (
        <div className="container mt-4 text-center">
            <form>
                <div className="mb-3">
                    <label htmlFor="exampleInputEmail1" className="form-label"><b>Usuario</b></label>
                    <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" />
                </div>
                <div className="mb-3">
                    <label htmlFor="exampleInputPassword1" className="form-label"><b>Contraseña</b></label>
                    <input type="password" className="form-control" id="exampleInputPassword1" />
                </div>
                <button type="submit" className="btn btn-primary">Ingresar</button>
            </form>
            <br />
            <h6>No tienes cuenta? Presiona aqui para registrate.</h6>
            <NavLink to='/registro' className="btn btn-link" href="#" role="button">Registrarse</NavLink>
        </div>
    )
}
