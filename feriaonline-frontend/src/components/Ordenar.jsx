export const Ordenar = () => {
    return (
        <div className="mb-4">
            <label htmlFor="orden" className="form-label">Ordenar por:</label>
            <select>
                <option value="recientes">Más Recientes</option>
                <option value="precioAsc">Precio: Menor a Mayor</option>
                <option value="precioDesc">Precio: Mayor a Menor</option>
            </select>
        </div>
    )
}
