import { useState } from "react";
import { getBookingsByCustomer, updateBookingStatus } from "../services/api";
import axios from "axios";

export default function OwnerDashboard() {
    const [venueId, setVenueId] = useState("");
    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [message, setMessage] = useState("");

    const loadBookings = async () => {
        if (!venueId) {
            setError("Please enter venue ID!");
            return;
        }
        try {
            setLoading(true);
            const res = await axios.get(
                `http://localhost:8080/api/bookings/venue/${venueId}`
            );
            setBookings(res.data);
            setError("");
        } catch (err) {
            setError("❌ Failed to load bookings.");
        } finally {
            setLoading(false);
        }
    };

    const handleStatus = async (bookingId, status) => {
        try {
            await updateBookingStatus(bookingId, status);
            setMessage(`✅ Booking ${bookingId} ${status}!`);
            loadBookings();
        } catch (err) {
            setError("❌ Failed to update status.");
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
                <h2 style={styles.title}>👑 Owner Dashboard</h2>

                <label style={styles.label}>Enter Venue ID</label>
                <input
                    style={styles.input}
                    type="number"
                    value={venueId}
                    onChange={(e) => setVenueId(e.target.value)}
                    placeholder="Enter your venue ID"
                />

                <button style={styles.button} onClick={loadBookings}>
                    View Bookings
                </button>

                {error && <p style={styles.error}>{error}</p>}
                {message && <p style={styles.success}>{message}</p>}
                {loading && <p>Loading...</p>}
            </div>

            {bookings.length > 0 && (
                <div style={{ marginTop: "20px" }}>
                    <h3 style={{ marginBottom: "15px" }}>
                        📋 Bookings for Venue {venueId}
                    </h3>
                    {bookings.map((b) => (
                        <div key={b.id} style={styles.bookingCard}>
                            <div style={styles.bookingInfo}>
                                <p><strong>Booking ID:</strong> {b.id}</p>
                                <p><strong>Customer:</strong> {b.customerName}</p>
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
                            <div style={styles.actions}>
                                {b.status === "PENDING" && (
                                    <>
                                        <button
                                            style={styles.confirmBtn}
                                            onClick={() => handleStatus(b.id, "CONFIRMED")}
                                        >
                                            ✅ Confirm
                                        </button>
                                        <button
                                            style={styles.cancelBtn}
                                            onClick={() => handleStatus(b.id, "CANCELLED")}
                                        >
                                            ❌ Cancel
                                        </button>
                                    </>
                                )}
                                {b.status === "CONFIRMED" && (
                                    <button
                                        style={styles.cancelBtn}
                                        onClick={() => handleStatus(b.id, "CANCELLED")}
                                    >
                                        ❌ Cancel
                                    </button>
                                )}
                                {b.status === "CANCELLED" && (
                                    <span style={styles.cancelled}>Booking Cancelled</span>
                                )}
                            </div>
                        </div>
                    ))}
                </div>
            )}

            {bookings.length === 0 && !loading && !error && venueId && (
                <p style={{ marginTop: "20px" }}>No bookings found for this venue.</p>
            )}
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
    bookingCard: {
        background: "white",
        border: "1px solid #ddd",
        padding: "15px",
        borderRadius: "8px",
        marginBottom: "10px",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
    },
    bookingInfo: { flex: 1 },
    actions: {
        display: "flex",
        flexDirection: "column",
        gap: "10px",
        marginLeft: "20px",
    },
    confirmBtn: {
        background: "#2d6a4f",
        color: "white",
        padding: "8px 15px",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer",
        fontSize: "14px",
    },
    cancelBtn: {
        background: "#c0392b",
        color: "white",
        padding: "8px 15px",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer",
        fontSize: "14px",
    },
    cancelled: {
        color: "red",
        fontWeight: "bold",
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