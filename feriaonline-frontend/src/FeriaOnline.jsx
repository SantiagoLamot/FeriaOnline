import { Route, Routes } from "react-router-dom"
import { NavBar } from "./components/generales/NavBar"
import { ProductosPage } from "./pages/ProductosPage"

export const FeriaOnline = () => {
  return (
    <>
    <NavBar></NavBar>
    <Routes>
        <Route path="/" element={<ProductosPage/>}/>
    </Routes>
    </>
  )
}