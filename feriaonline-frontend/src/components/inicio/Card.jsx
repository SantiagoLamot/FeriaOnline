import { Link } from "react-router-dom";

export const Card = () => {
    return (
        <>
            <div className="card m-3" style={{ width: "18rem" }}>
                <img src="..." className="card-img-top" alt="Foto del producto" />
                <div className="card-body">
                    <h5 className="card-title">Titulo del producto</h5>
                    <p className="card-text">Precio:</p>
                    <Link to="/productos/descripcionProducto" className="btn btn-primary">
                        Ver más
                    </Link>
                </div>
            </div>
        </>
    )
}
