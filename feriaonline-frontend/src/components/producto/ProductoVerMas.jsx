import React from 'react'

export const ProductoVerMas = ({ producto }) => {
  return (
    <>
      <div className='container'>
        <h4>{producto.titulo}</h4>
        <br />
        <img src={producto.foto} alt={producto.titulo}
          className="img-fluid mx-auto d-block"
          style={{ maxWidth: "300px", height: "auto" }} />
        <h2>$ {producto.precio}</h2>
        <h3>Detalles:</h3>
        <p>{producto.descripcion}</p>
        <br />
        <h5>Vendedor: </h5>
        <h5>Metodo de pago: </h5>
      </div>
    </>
  )
}
