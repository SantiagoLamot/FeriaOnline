import React from 'react'
import EditableProfile from "../../components/profile/editProfile"

export const EditarPerfil = () => {
    return (
        <>
            <div className='container'>
            <h1 className="text-center mt-5">Edicion de Perfil</h1>
            <EditableProfile />
            </div>
        </>
    )
}
