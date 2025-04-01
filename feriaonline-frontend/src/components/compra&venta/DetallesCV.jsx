export const DetallesCV = ({ compra }) => {  // <-- Acepta la prop compra
    return (
        <div className="container mt-4">
            <div className="card shadow-sm">
                <div className="row g-0">
                    <div className="col-md-4 d-flex align-items-center justify-content-center">
                        <img src={compra.imagen} alt="Producto" className="img-fluid rounded-start" />
                    </div>

                    <div className="col-md-8">
                        <div className="card-body">
                            <h5 className="card-title">Compra #{compra.numeroCompra}</h5>
                            <p className="card-text"><strong>Comprador:</strong> {compra.nombreComprador}</p>
                            <p className="card-text"><strong>Vendedor:</strong> {compra.nombreVendedor}</p>
                            <p className="card-text"><strong>Importe:</strong> ${compra.importe}</p>
                            <p className="card-text"><strong>Método de Pago:</strong> {compra.metodoPago}</p>
                            <p className="card-text"><strong>Fecha de Compra:</strong> {compra.fechaCompra}</p>
                            <p className="card-text"><strong>Fecha de Entrega:</strong> {compra.fechaEntrega}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
