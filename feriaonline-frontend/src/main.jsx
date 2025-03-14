import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import { FeriaOnline } from './FeriaOnline'
import { ProductosPage } from './pages/ProductosPage'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
  <StrictMode>
    <FeriaOnline />
    <ProductosPage/>
  </StrictMode>
  </BrowserRouter>
)
