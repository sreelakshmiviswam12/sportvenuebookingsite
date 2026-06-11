import { useState } from "react";
import { getBookingsByCustomer } from "../services/api";

export default function MyBookings() {
    const [customerId, setCustomerId] = useState("");
    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const loadBookings = async () => {
        if (!customerId) {
            setError("Please enter your customer ID!");
            return;
        }
        try {
            setLoading(true);
            const res = await getBookingsByCustomer(customerId);
            setBookings(res.data);
            setError("");
        } catch (err) {
            setError("❌ Failed to load bookings. Check your ID.");
        } finally {
            setLoading(false);
        }
    };

    const getStatusColor = (status) => {
        if (status === "CONFIRMED") return "green";
        if (status === "CANCELLED") return "red";
        return "orange";
    };

    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h2 style={styles.title}>📋 My Bookings</h2>

                <label style={styles.label}>Enter Customer ID</label>
                <input
                    style={styles.input}
                    type="number"
                    value={customerId}
                    onChange={(e) => setCustomerId(e.target.value)}
                    placeholder="Enter your user ID"
                />

                <button style={styles.button} onClick={loadBookings}>
                    View Bookings
                </button>

                {error && <p style={styles.error}>{error}</p>}

                {loading && <p>Loading...</p>}

                <div style={{ marginTop: "20px" }}>
                    {bookings.length === 0 && !loading && !error && (
                        <p>No bookings found.</p>
                    )}
                    {bookings.map((b) => (
                        <div key={b.id} style={styles.bookingCard}>
                            <p><strong>Booking ID:</strong> {b.id}</p>
                            <p><strong>Venue:</strong> {b.venueName}</p>
                            <p><strong>Location:</strong> {b.venueLocation}</p>
                            <p><strong>Date:</strong> {b.date}</p>
                            <p><strong>Time:</strong> {b.startTime} - {b.endTime}</p>
                            <p><strong>Duration:</strong> {b.duration} hour(s)</p>
                            <p><strong>Total Price:</strong> ₹{b.totalPrice}</p>
                            <p>
                                <strong>Status: </strong>
                                <span style={{ color: getStatusColor(b.status) }}>
                  {b.status}
                </span>
                            </p>
                        </div>
                    ))}
                </div>
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
        maxWidth: "600px",
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
    error: {
        background: "#fdecea",
        color: "#c0392b",
        padding: "12px",
        borderRadius: "6px",
        marginTop: "15px",
    },
    bookingCard: {
        border: "1px solid #ddd",
        padding: "15px",
        borderRadius: "8px",
        marginBottom: "10px",
    },
};