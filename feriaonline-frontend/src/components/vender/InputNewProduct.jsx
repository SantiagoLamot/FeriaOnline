export const InputNewProduct = () => {
    return (
        <>
            <div className="input-group mb-3">
                <span className="input-group-text" id="basic-addon1">Titulo</span>
                <input type="text" className="form-control" placeholder="Ingrese titulo del producto" />
            </div>
            <div className="input-group mb-3">
                <span className="input-group-text">Descripcion</span>
                <textarea className="form-control" placeholder="Ingrese descripcion del producto"></textarea>
            </div>
            <div className="input-group mb-3">
                <span className="input-group-text">Precio</span>
                <span className="input-group-text">$</span>
                <input type="number" className="form-control" placeholder="Ingrese precio del producto" min="0" step="0.01" />
            </div>
            <div className="d-flex flex-column mb-3">
                <span className="input-group-text mb-3">Formas de entrega</span>
                <div className="form-check form-switch mb-2 text-start">
                    <input className="form-check-input" type="checkbox" />
                    <label className="form-check-label" >Aceptar envios al domicilio del comprador</label>
                </div>
                <div className="form-check form-switch text-start">
                    <input className="form-check-input" type="checkbox" />
                    <label className="form-check-label" >Aceptar retiros en el domicilio del vendedor</label>
                </div>
            </div>
            <div className="input-group mb-3">
                <label className="input-group-text">Fotos del producto</label>
                <input type="file" className="form-control" />
            </div>
            <button type="submit" className="btn btn-primary">Publicar</button>
        </>
    )
}
