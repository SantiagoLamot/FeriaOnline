import { Navigate, Route, Routes } from "react-router-dom"
import { NavBar } from "./components/Generales/NavBar"
import { PerfilPage } from "./pages/PerfilPage"
import { ProductosPage } from "./pages/ProductosPage"
import { VenderPage } from "./pages/VenderPage"

export const FeriaOnline = () => {
  return (
    <>
    <NavBar></NavBar>
    <Routes>
        <Route path="/" element={<ProductosPage/>}/>
        <Route path="/productos" element={<ProductosPage/>}></Route>
        <Route path="/vender" element={<VenderPage/>}></Route>
        <Route path="/perfil" element={<PerfilPage/>}></Route>
        <Route path="/*" element={<Navigate to='/'></Navigate>}></Route>
    </Routes>
    </>
  )
}