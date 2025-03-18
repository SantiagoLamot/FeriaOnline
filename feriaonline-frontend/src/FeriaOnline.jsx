import { Navigate, Route, Routes } from "react-router-dom"
import { NavBar } from "./components/Generales/NavBar"
import { PerfilPage } from "./pages/PerfilPage"
import { DescripcionPage } from "./pages/PerfilPages/DescripcionPage"
import { MisComprasPage } from "./pages/PerfilPages/MisComprasPage"
import { MisPublicacionesPage } from "./pages/PerfilPages/MisPublicacionesPage"
import { MisVentasPage } from "./pages/PerfilPages/MisVentasPage"
import { ProductosPage } from "./pages/ProductosPage"
import { VenderPage } from "./pages/VenderPage"

export const FeriaOnline = () => {
  return (
    <>
      <NavBar></NavBar>
      <Routes>
        <Route path="/" element={<ProductosPage />} />
        <Route path="/productos" element={<ProductosPage />}></Route>
        <Route path="/vender" element={<VenderPage />}></Route>
        <Route path="/perfil" element={<PerfilPage />}>
            <Route path="descripcion" element={<DescripcionPage />} />
            <Route path="misPublicaciones" element={<MisPublicacionesPage />} />
            <Route path="misCompras" element={<MisComprasPage />} />
            <Route path="misVentas" element={<MisVentasPage />} />
            <Route path="" element={<Navigate to="descripcion" />} />
        </Route>
        <Route path="/*" element={<Navigate to='/'></Navigate>}></Route>
      </Routes>
    </>
  )
}