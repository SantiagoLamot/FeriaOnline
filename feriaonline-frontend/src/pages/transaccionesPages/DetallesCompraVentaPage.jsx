import React from 'react'
import { DetallesCV } from '../../components/compra&venta/DetallesCV'

export const DetallesCompraVentaPage = () => {

  const compraEjemplo = {
    numeroCompra: "123456",
    nombreComprador: "Facundo Orona",
    nombreVendedor: "Santiago Lamot",
    importe: "150.000",
    metodoPago: "Tarjeta de Crédito",
    fechaCompra: "2025-03-30",
    fechaEntrega: "2025-04-05",
    imagen: "https://via.placeholder.com/200"
};

  return (
    <>
      <div className="text-center mt-3">
        <h1>Detalles de la Compra/Venta</h1>
        <hr />
      </div>
      <DetallesCV compra={compraEjemplo}></DetallesCV>
    </>
  )
}
