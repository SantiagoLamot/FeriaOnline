export const Card = () => {
    return (
        <div className="card m-3" style={{width: "18rem"}}>
            <img src="..." className="card-img-top" alt="Foto del producto" />
            <div className="card-body">
                <h5 className="card-title">Titulo del producto</h5>
                <p className="card-text">Precio:</p>
                <a href="#" className="btn btn-primary">Ver mas</a>
            </div>
        </div>
    )
}
