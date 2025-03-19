import { Card } from "../components/Inicio/Card"
import { Ordenar } from "../components/inicio/Ordenar"

export const ProductosPage = () => {
    return (
        <div className="container mt-4 text-center">
            <h1>Productos</h1>
            <hr />
            <Ordenar></Ordenar>
            <div className="row d-flex justify-content-center">
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
            </div>
        </div>
    )
}
