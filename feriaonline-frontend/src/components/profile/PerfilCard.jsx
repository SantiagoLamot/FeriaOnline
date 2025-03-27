import React from "react";

const PerfilCard = ({ nombre, usuario, biografia, ciudad, foto }) => {
    return (
        <div className="card shadow-lg p-4">
            <div className="row">
                {/* Información del usuario a la izquierda */}
                <div className="col-md-8 d-flex flex-column justify-content-center">
                    <h2 className="fw-bold">{nombre}</h2>
                    <p className="text-muted">@{usuario}</p>
                    <p className="mt-3">
                        <strong>Biografía:</strong> {biografia}
                    </p>
                    <p>
                        <strong>Ciudad:</strong> {ciudad}
                    </p>
                </div>

                {/* Foto de perfil a la derecha */}
                <div className="col-md-4 text-center">
                    <img 
                        src={foto} 
                        alt="Foto de perfil" 
                        className="rounded-circle img-fluid border"
                        style={{ width: "150px", height: "150px", objectFit: "cover" }} 
                    />
                </div>
            </div>
        </div>
    );
};

export default PerfilCard;
