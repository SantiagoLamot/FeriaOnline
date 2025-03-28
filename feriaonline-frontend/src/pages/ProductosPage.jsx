import { useContext } from "react";
import { Outlet } from "react-router-dom";
import { Card } from "../components/Inicio/Card";
import { Ordenar } from "../components/inicio/Ordenar";
import { ProductosContext } from "../context/ProductosContext";

export const ProductosPage = () => {
    const {productos} = useContext(ProductosContext)

    return (
        <div className="container mt-4 text-center">
            <h1>Productos</h1>
            <hr />
            <Ordenar />
            <div className="row d-flex justify-content-center">
                {productos.map(p => (
                    <Card 
                    key = {p.id}
                    titulo= {p.title}
                    foto = {p.image}
                    precio = {p.price}
                    descripcion = {p.description}
                    />
                ))}
            </div>

            {/* Aquí se renderizará la subruta (VerMasPage, por ejemplo) */}
            <Outlet />
        </div>
    );
};
