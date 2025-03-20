import { usePasswordValidation } from "../../hooks/usePasswordValidation";

export const RegistroForm = () => {
    const { password, confirmPassword, error, handlePasswordChange, handleConfirmPasswordChange } = usePasswordValidation();

    return (
        <form className="row g-3">
            <div className="col-md-6">
                <label htmlFor="inputName" className="form-label">Nombre</label>
                <input type="text" className="form-control" id="inputName" required />
            </div>
            <div className="col-md-6">
                <label htmlFor="inputLastName" className="form-label">Apellido</label>
                <input type="text" className="form-control" id="inputLastName" required/>
            </div>
            <div className="col-md-6">
                <label htmlFor="inputUser" className="form-label">Nombre de usuario</label>
                <input type="text" className="form-control" id="inputUser" minLength={4} maxLength={15} required/>
                <small className="form-text text-muted">Este será su username público</small>
            </div>
            <div className="col-md-6">
                <label htmlFor="inputEmail" className="form-label">Email</label>
                <input type="email" className="form-control" id="inputEmail" required/>
            </div>
            <div className="col-12">
                <label htmlFor="inputPassword" className="form-label">Ingrese su contraseña</label>
                <input 
                    type="password" 
                    className="form-control" 
                    id="inputPassword" 
                    value={password}
                    onChange={handlePasswordChange}
                    required
                />
                <small className="form-text text-muted">Debe tener al menos 8 caracteres</small>
            </div>
            <div className="col-12">
                <label htmlFor="inputPasswordRepeat" className="form-label">Repita su contraseña</label>
                <input 
                    type="password" 
                    className="form-control" 
                    id="inputPasswordRepeat" 
                    value={confirmPassword}
                    onChange={handleConfirmPasswordChange}
                    required
                />
                {error && <small className="form-text text-danger">{error}</small>}
            </div>
            <div className="col-md-6">
                <label htmlFor="inputCity" className="form-label">Ciudad</label>
                <input type="text" className="form-control" id="inputCity" required/>
            </div>
            <div className="col-12">
                <button 
                    type="submit" 
                    className="btn btn-primary"
                    disabled={!password || !confirmPassword || error}
                >
                    Registrarme
                </button>
            </div>
        </form>
    );
};
