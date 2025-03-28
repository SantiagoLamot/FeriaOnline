import { useNavigate } from "react-router-dom";

export const Card = ({id,titulo, foto, precio, descripcion}) => {
    const navigate = useNavigate();
    const handleVerMas = () => {
        navigate("./productos/descripcionProducto", { state: { id, titulo, foto, precio, descripcion} })
    }
    return (
        <>
            <div className="card m-3" style={{ width: "18rem" }}>
                <img src={foto} className="card-img-top" alt={titulo} />
                <div className="card-body">
                    <h5 className="card-title">{titulo}</h5>
                    <p className="card-text">${precio}</p>
                    <button onClick={handleVerMas} className="btn btn-primary">
                        Ver más
                    </button>
                </div>
            </div>
        </>
    )
}
