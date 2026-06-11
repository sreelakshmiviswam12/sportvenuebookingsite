import { useState } from "react";
import { registerUser } from "../services/api";

export default function Register() {
    const [form, setForm] = useState({
        name: "",
        password: "",
        phoneNo: "",
        role: "CUSTOMER",
    });
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        try {
            const res = await registerUser(form);
            setMessage(`✅ Registered! Your ID is: ${res.data.id}. Save this!`);
            setError("");
            setForm({ name: "", password: "", phoneNo: "", role: "CUSTOMER" });
        } catch (err) {
            setError("❌ Registration failed. Try again.");
            setMessage("");
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h2 style={styles.title}>👤 Register Account</h2>

                <label style={styles.label}>Full Name</label>
                <input
                    style={styles.input}
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                    placeholder="Enter your name"
                />

                <label style={styles.label}>Password</label>
                <input
                    style={styles.input}
                    name="password"
                    type="password"
                    value={form.password}
                    onChange={handleChange}
                    placeholder="Enter password"
                />

                <label style={styles.label}>Phone Number</label>
                <input
                    style={styles.input}
                    name="phoneNo"
                    value={form.phoneNo}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                />

                <label style={styles.label}>Role</label>
                <select
                    style={styles.input}
                    name="role"
                    value={form.role}
                    onChange={handleChange}
                >
                    <option value="CUSTOMER">Customer</option>
                    <option value="OWNER">Owner</option>
                    <option value="ADMIN">Admin</option>
                </select>

                <button style={styles.button} onClick={handleSubmit}>
                    Register
                </button>

                {message && <p style={styles.success}>{message}</p>}
                {error && <p style={styles.error}>{error}</p>}
            </div>
        </div>
    );
}

const styles = {
    container: { padding: "30px" },
    card: {
        background: "white",
        padding: "25px",
        borderRadius: "10px",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
        maxWidth: "500px",
    },
    title: { marginBottom: "20px", color: "#333" },
    label: { display: "block", marginBottom: "5px", fontWeight: "bold" },
    input: {
        width: "100%",
        padding: "10px",
        margin: "0 0 15px 0",
        border: "1px solid #ddd",
        borderRadius: "6px",
        fontSize: "14px",
    },
    button: {
        background: "#1a73e8",
        color: "white",
        padding: "10px 25px",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer",
        fontSize: "15px",
        width: "100%",
    },
    success: {
        background: "#e6f4ea",
        color: "#2d6a4f",
        padding: "12px",
        borderRadius: "6px",
        marginTop: "15px",
    },
    error: {
        background: "#fdecea",
        color: "#c0392b",
        padding: "12px",
        borderRadius: "6px",
        marginTop: "15px",
    },
};