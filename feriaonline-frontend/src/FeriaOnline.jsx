import { Navigate, Route, Routes } from "react-router-dom"
import { NavBar } from "./components/Generales/NavBar"
import { PerfilPage } from "./pages/PerfilPage"
import { DescripcionPage } from "./pages/PerfilPages/DescripcionPage"
import { MisComprasPage } from "./pages/PerfilPages/MisComprasPage"
import { MisPublicacionesPage } from "./pages/PerfilPages/MisPublicacionesPage"
import { MisVentasPage } from "./pages/PerfilPages/MisVentasPage"
import { ProductosPage } from "./pages/ProductosPage"
import { VenderPage } from "./pages/VenderPage"
import { LoginPage } from "./pages/loginRegistroPages/LoginPage"
import { RegistroPage } from "./pages/loginRegistroPages/RegistroPage"
import { EditarPerfil } from "./pages/PerfilPages/EditarPerfil"
import { VerMasPage } from "./pages/productosPages/verMasPage"

export const FeriaOnline = () => {
  return (
    <>
      <NavBar></NavBar>
      <Routes>
        <Route path="/" element={<ProductosPage />} />
        <Route path="/productos" element={<ProductosPage />} />
        <Route path="/productos/descripcionProducto" element={<VerMasPage />} /> 
        <Route path="/vender" element={<VenderPage />} />
        <Route path="/perfil" element={<PerfilPage />}>
          <Route path="descripcion" element={<DescripcionPage />} />
          <Route path="editarPerfil" element={<EditarPerfil />} />
          <Route path="misPublicaciones" element={<MisPublicacionesPage />} />
          <Route path="misCompras" element={<MisComprasPage />} />
          <Route path="misVentas" element={<MisVentasPage />} />
          <Route path="" element={<Navigate to="descripcion" />} />
        </Route>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/registro" element={<RegistroPage />} />
        <Route path="/*" element={<Navigate to='/' />} />
      </Routes>

    </>
  )
}