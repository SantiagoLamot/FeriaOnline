import { Card } from "../components/Card"
import { Ordenar } from "../components/Ordenar"

export const ProductosPage = () => {
    return (
        <div className="container mt-4 text-center">
            <h1>Productos</h1>
            <hr />
            <Ordenar></Ordenar>
            <div className="row">
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
