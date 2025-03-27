import React from 'react'

export const ProductoVerMas = () => {
  return (
    <>
    <div className='container'>
        <h2>Nombre del producto: </h2>
        <br />
        <h3>Detalles:</h3>
        <h5>-Descripcion del producto</h5>
        <br />
        <img src="../remeraEjemplo.jpg" alt="Imagen del producto"
         className="img-fluid mx-auto d-block" 
         style={{ maxWidth: "300px", height: "auto" }} />
         <h5>Vendedor: </h5>
         <h5>Metodo de pago: </h5>
        <h2>$ Precio</h2>
    </div>
    </>
  )
}
