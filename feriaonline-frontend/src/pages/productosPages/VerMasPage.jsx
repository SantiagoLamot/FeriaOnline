import React from 'react'
import { ProductoVerMas } from '../../components/producto/ProductoVerMas'

export const VerMasPage = () => {
  return (
    <>
    <div className='container'>
        <h1><b>Informacion del producto</b></h1>
        <hr />
        <ProductoVerMas></ProductoVerMas>
        <br />
        {/* En un futuro modificar y mostrar boton comprar solo cuando hay una sesion iniciada) */}
        <button className='btn btn-primary'>Comprar</button>
    </div>

    </>
  )
}
