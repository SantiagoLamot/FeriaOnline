import { Card } from "../components/Inicio/Card";
import { Ordenar } from "../components/inicio/Ordenar";
import { Outlet } from "react-router-dom";

export const ProductosPage = () => {
    return (
        <div className="container mt-4 text-center">
            <h1>Productos</h1>
            <hr />
            <Ordenar />
            <div className="row d-flex justify-content-center">
                <Card />
                <Card />
                <Card />
                <Card />
                <Card />
                <Card />
            </div>

            {/* Aquí se renderizará la subruta (VerMasPage, por ejemplo) */}
            <Outlet />
        </div>
    );
};
