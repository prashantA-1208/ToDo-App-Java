import React from "react";

const Footer = () => {
  return (
    <footer >
      <div className="footer-div">
        <p>© {new Date().getFullYear()} ToDo App. All rights reserved.</p>
        <p>
          Built with ❤️ by <span className="font-medium">Prashant</span>
        </p>
      </div>
    </footer>
  );
};

export default Footer;
