import { useState } from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import BookVenue from "./pages/BookVenue";
import MyBookings from "./pages/MyBookings";
import OwnerDashboard from "./pages/OwnerDashboard";

export default function App() {
  const [activePage, setActivePage] = useState("home");

  const renderPage = () => {
    switch (activePage) {
      case "home": return <Home />;
      case "register": return <Register />;
      case "book": return <BookVenue />;
      case "mybookings": return <MyBookings />;
      case "owner": return <OwnerDashboard />;
      default: return <Home />;
    }
  };

  return (
      <div style={styles.app}>
        {/* Navbar */}
        <div style={styles.navbar}>
          <span style={styles.navTitle}>🏟️ Sport Venue Booking Site</span>
        </div>

        {/* Tabs */}
        <div style={styles.tabs}>
          <button
              style={activePage === "home" ? styles.activeTab : styles.tab}
              onClick={() => setActivePage("home")}
          >
            🏟️ Venues
          </button>
          <button
              style={activePage === "register" ? styles.activeTab : styles.tab}
              onClick={() => setActivePage("register")}
          >
            👤 Register
          </button>
          <button
              style={activePage === "book" ? styles.activeTab : styles.tab}
              onClick={() => setActivePage("book")}
          >
            📅 Book Venue
          </button>
          <button
              style={activePage === "mybookings" ? styles.activeTab : styles.tab}
              onClick={() => setActivePage("mybookings")}
          >
            📋 My Bookings
          </button>
          <button
              style={activePage === "owner" ? styles.activeTab : styles.tab}
              onClick={() => setActivePage("owner")}
          >
            👑 Owner Dashboard
          </button>
        </div>

        {/* Page Content */}
        <div style={styles.content}>
          {renderPage()}
        </div>
      </div>
  );
}

const styles = {
  app: {
    fontFamily: "Arial, sans-serif",
    background: "#f0f2f5",
    minHeight: "100vh",
  },
  navbar: {
    background: "#1a73e8",
    color: "white",
    padding: "15px 30px",
    textAlign: "center",
  },
  navTitle: {
    fontSize: "22px",
    fontWeight: "bold",
  },
  tabs: {
    display: "flex",
    background: "white",
    borderBottom: "2px solid #ddd",
    padding: "0 30px",
  },
  tab: {
    padding: "15px 25px",
    cursor: "pointer",
    fontWeight: "bold",
    color: "#555",
    background: "none",
    border: "none",
    fontSize: "14px",
  },
  activeTab: {
    padding: "15px 25px",
    cursor: "pointer",
    fontWeight: "bold",
    color: "#1a73e8",
    background: "none",
    border: "none",
    borderBottom: "3px solid #1a73e8",
    fontSize: "14px",
  },
  content: {
    padding: "20px",
  },
};