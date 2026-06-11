import { useState } from "react";
import { createBooking } from "../services/api";

export default function BookVenue() {
    const [form, setForm] = useState({
        customerId: "",
        venueId: "",
        duration: "",
        date: "",
        startTime: "",
        endTime: "",
    });
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        try {
            const bookingData = {
                customer: { id: parseInt(form.customerId) },
                venue: { id: parseInt(form.venueId) },
                duration: parseInt(form.duration),
                date: form.date,
                startTime: form.startTime,
                endTime: form.endTime,
            };
            const res = await createBooking(bookingData);
            setMessage(
                `✅ Booking confirmed! ID: ${res.data.id} | 
         Status: ${res.data.status} | 
         Total: ₹${res.data.totalPrice}`
            );
            setError("");
        } catch (err) {
            setError(`❌ ${err.response?.data?.error || "Booking failed!"}`);
            setMessage("");
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h2 style={styles.title}>📅 Book a Venue</h2>

                <label style={styles.label}>Customer ID</label>
                <input
                    style={styles.input}
                    name="customerId"
                    type="number"
                    value={form.customerId}
                    onChange={handleChange}
                    placeholder="Enter your user ID"
                />

                <label style={styles.label}>Venue ID</label>
                <input
                    style={styles.input}
                    name="venueId"
                    type="number"
                    value={form.venueId}
                    onChange={handleChange}
                    placeholder="Enter venue ID"
                />

                <label style={styles.label}>Duration (hours)</label>
                <input
                    style={styles.input}
                    name="duration"
                    type="number"
                    value={form.duration}
                    onChange={handleChange}
                    placeholder="e.g. 2"
                />

                <label style={styles.label}>Date</label>
                <input
                    style={styles.input}
                    name="date"
                    type="date"
                    value={form.date}
                    onChange={handleChange}
                />

                <label style={styles.label}>Start Time</label>
                <input
                    style={styles.input}
                    name="startTime"
                    type="time"
                    value={form.startTime}
                    onChange={handleChange}
                />

                <label style={styles.label}>End Time</label>
                <input
                    style={styles.input}
                    name="endTime"
                    type="time"
                    value={form.endTime}
                    onChange={handleChange}
                />

                <button style={styles.button} onClick={handleSubmit}>
                    Book Now
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