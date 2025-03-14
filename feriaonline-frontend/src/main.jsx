import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import { FeriaOnline } from './FeriaOnline'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
  <StrictMode>
    <FeriaOnline />
  </StrictMode>
  </BrowserRouter>
)
