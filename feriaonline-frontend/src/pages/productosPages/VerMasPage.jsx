import React from 'react';
import { useLocation } from 'react-router-dom';
import { ProductoVerMas } from '../../components/producto/ProductoVerMas';

export const VerMasPage = () => {
  
  const location = useLocation();
  const producto = location.state; 
      // Si alguien entra a la URL directamente sin datos, redirigir a /productos
    if (!producto) {
        return <h1>Producto no encontrado</h1>;
    }
  
  return (
    <>
    <div className='container mt-4'>
        <ProductoVerMas 
          producto = {producto}
        >
        </ProductoVerMas>
        <br />
        {/* En un futuro modificar y mostrar boton comprar solo cuando hay una sesion) */}
        <button className='btn btn-primary'>Comprar</button>
    </div>
    </>
  )
}
