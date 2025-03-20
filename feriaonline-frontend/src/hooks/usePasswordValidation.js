import { useState } from "react";

export const usePasswordValidation = () => {
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
        validatePassword(e.target.value, confirmPassword);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
        validatePassword(password, e.target.value);
    };

    const validatePassword = (pass, confirmPass) => {
        if (confirmPass && pass !== confirmPass) {
            setError("Las contraseñas no coinciden");
        } else {
            setError("");
        }
    };

    return {
        password,
        confirmPassword,
        error,
        handlePasswordChange,
        handleConfirmPasswordChange
    };
};
