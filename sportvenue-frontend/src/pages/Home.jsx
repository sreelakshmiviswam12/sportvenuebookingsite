import { useState, useEffect } from "react";
import { getActiveVenues, getVenueImages } from "../services/api";

export default function Home() {
    const [venues, setVenues] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadVenues();
    }, []);

    const loadVenues = async () => {
        try {
            const res = await getActiveVenues();
            setVenues(res.data);
        } catch (err) {
            console.error("Error loading venues", err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.title}>🏟️ Available Venues</h1>
            {loading ? (
                <p>Loading venues...</p>
            ) : venues.length === 0 ? (
                <p>No active venues found.</p>
            ) : (
                <div style={styles.grid}>
                    {venues.map((venue) => (
                        <div key={venue.id} style={styles.card}>
                            <h2 style={styles.venueName}>{venue.name}</h2>
                            <p>📍 {venue.location}</p>
                            <p>💰 ₹{venue.price}/hour</p>
                            <p>👤 Owner: {venue.owner?.name}</p>
                            <span style={venue.bookingStatus ?
                                styles.available : styles.unavailable}>
                {venue.bookingStatus ? "✅ Available" : "❌ Unavailable"}
              </span>
                            <p style={styles.venueId}>Venue ID: {venue.id}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

const styles = {
    container: { padding: "30px" },
    title: { marginBottom: "20px", color: "#1a73e8" },
    grid: {
        display: "grid",
        gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
        gap: "20px",
    },
    card: {
        background: "white",
        padding: "20px",
        borderRadius: "10px",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
    },
    venueName: { color: "#1a73e8", marginBottom: "10px" },
    available: {
        background: "#e6f4ea",
        color: "#2d6a4f",
        padding: "3px 10px",
        borderRadius: "12px",
        fontSize: "12px",
    },
    unavailable: {
        background: "#fdecea",
        color: "#c0392b",
        padding: "3px 10px",
        borderRadius: "12px",
        fontSize: "12px",
    },
    venueId: { color: "#999", fontSize: "12px", marginTop: "8px" },
};