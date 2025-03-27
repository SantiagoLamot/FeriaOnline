import PerfilCard from "../../components/profile/PerfilCard"

export const DescripcionPage = () => {
    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <PerfilCard
                        nombre="Lionel Messi"
                        usuario="leomessi10"
                        biografia="Jugador de futbol retirado que vende las camisetas porque no tiene mas plata, a causa de que anotnela se quedo con todo"
                        ciudad="Buenos Aires, Argentina"
                        foto="../messiProfile.webp"
                    />
                </div>
            </div>
        </div>
    );
};
