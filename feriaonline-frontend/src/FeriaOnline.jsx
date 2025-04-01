import { Navigate, Route, Routes } from "react-router-dom"
import { NavBar } from "./components/Generales/NavBar"
import { ProductosProvider } from "./context/ProductosProvider"
import { LoginPage } from "./pages/loginRegistroPages/LoginPage"
import { RegistroPage } from "./pages/loginRegistroPages/RegistroPage"
import { PerfilPage } from "./pages/PerfilPage"
import { DescripcionPage } from "./pages/PerfilPages/DescripcionPage"
import { EditarPerfil } from "./pages/PerfilPages/EditarPerfil"
import { MisComprasPage } from "./pages/PerfilPages/MisComprasPage"
import { MisPublicacionesPage } from "./pages/PerfilPages/MisPublicacionesPage"
import { MisVentasPage } from "./pages/PerfilPages/MisVentasPage"
import { ProductosPage } from "./pages/ProductosPage"
import { VerMasPage } from "./pages/productosPages/verMasPage"
import { VenderPage } from "./pages/VenderPage"
import { DetallesCompraVentaPage } from "./pages/transaccionesPages/DetallesCompraVentaPage"


export const FeriaOnline = () => {
  return (
    <ProductosProvider>
      <NavBar></NavBar>
      <Routes>
        <Route path="/" element={<ProductosPage />} />
        <Route path="/productos" element={<ProductosPage />} />
        <Route path="/productos/descripcionProducto" element={<VerMasPage />} />
        <Route path="/detallesCompraVenta" element={<DetallesCompraVentaPage />} />
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
    </ProductosProvider>

  )
}