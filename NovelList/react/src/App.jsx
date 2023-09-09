import './App.css';
import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import RootLayout from './page/RootLayout';
import ErrorPage from './page/ErrorPage';
import NovelPage from './page/NovelPage';
import CreateNovelPage from './page/CreateNovelPage';

// 라우터
const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: '/',
        element: <NovelPage />,
      },
      {
        path: '/create',
        element: <CreateNovelPage />,
      },
    ],
  },
]);

// 기본 App
export default function App() {
  return <RouterProvider router={router} />;
}