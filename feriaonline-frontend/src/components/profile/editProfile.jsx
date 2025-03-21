import React, { useState } from 'react';

const EditableProfile = () => {
    const [name, setName] = useState('Facundo Orona');
    const [city, setCity] = useState('Tapalque');
    const [bio, setBio] = useState('Vendo ropa Y2K, a precio accesible. $$$');

    //API PARA IR PROBANDO VISTA
    const [profilePic, setProfilePic] = useState('https://via.placeholder.com/150');
    const handleNameChange = (e) => setName(e.target.value);
    const handleCityChange = (e) => setCity(e.target.value);
    const handleBioChange = (e) => setBio(e.target.value);
    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setProfilePic(reader.result);
            };
            reader.readAsDataURL(file);
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card" style={{ width: '60%', maxWidth: '600px' }}>
                <input
                    type="file"
                    accept="image/*"
                    onChange={handleFileChange}
                    className="mb-3 mx-auto d-block"
                    style={{ width: '80%' }}
                />
                <img
                    src={profilePic}
                    className="card-img-top mx-auto d-block"
                    alt="Profile"
                    style={{
                        width: '250px',       // Controlamos el tamaño de la imagen
                        height: '250px',      // Igualamos la altura y el ancho para hacerlo circular
                        borderRadius: '100%'   // Borde redondeado al 50% para hacerlo completamente redondo
                    }}
                />
                <div className="card-body">
                    <input
                        type="text"
                        className="form-control mb-3"
                        value={name}
                        onChange={handleNameChange}
                        placeholder="Nombre completo"
                        style={{ fontSize: '1.1rem' }}
                    />
                    <input
                        type="text"
                        className="form-control mb-3"
                        value={city}
                        onChange={handleCityChange}
                        placeholder="Ciudad"
                        style={{ fontSize: '1.1rem' }}
                    />
                    <textarea
                        className="form-control mb-3"
                        value={bio}
                        onChange={handleBioChange}
                        placeholder="Biografía"
                        rows="5"
                        style={{ fontSize: '1.1rem' }}
                    />
                </div>
            </div>
        </div>
    );
};

export default EditableProfile;
