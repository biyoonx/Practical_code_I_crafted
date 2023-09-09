import React from 'react';
import { Link } from 'react-router-dom';

export default function MainNav() {
  return (
    <header className="mainNav">
      <ul>
        <li>
          <Link to="/">List</Link>
        </li>
        <li>
          <Link to="/create">Add New Novel</Link>
        </li>
      </ul>
    </header>
  );
}