import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { FeriaOnline } from './FeriaOnline'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <FeriaOnline />
  </StrictMode>,
)
