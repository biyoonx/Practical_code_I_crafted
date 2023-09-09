import React from 'react';
import { Outlet } from 'react-router-dom';
import MainNav from '../components/Nav/MainNav';

export default function RootLayout() {
  return (
    <>
      <MainNav />
      <main>
        <Outlet />
      </main>
    </>
  );
}
