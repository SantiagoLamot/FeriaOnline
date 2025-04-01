import { useState } from "react";

const estados = ["Producto Entregado", "Compra Cancelada", "Entrega Pendiente"];


export const ProductoVenta = () => {

    const [estado, setEstado] = useState(estados[Math.floor(Math.random() * estados.length)]);

  return (
    <a href="#" className="list-group-item list-group-item-action text-center">
            <small className="d-block text-end">Fecha Compra</small>
            <h5 className="mb-1 d-block">Titulo Producto</h5>
            <p className="mb-1">Precio del producto</p>
            <span className={`badge ${estado === "Producto Entregado" ? "bg-success" : estado === "Compra Cancelada" ? "bg-danger" : "bg-warning"}`}>
                {estado}
            </span>
            <br />
            <small>Click para ver datos de la venta</small>
        </a>
  )
}
