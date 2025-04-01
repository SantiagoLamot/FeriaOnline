import { ProductoVenta } from "../../components/producto/ProductoVenta"

export const MisVentasPage = () => {
    return (
        <div className="container mt-4 text-center">
                    <h1>Productos Comprados</h1>
                    <div className="list-group ">
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                        <ProductoVenta></ProductoVenta>
                    </div>
                </div>
    )
}
