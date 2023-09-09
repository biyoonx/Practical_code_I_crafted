import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import NovelListHead from '../components/Novel/NovelListHead';
import NovelList from '../components/Novel/NovelList';
import { novelsPageTitle, novelListThead } from '../novelData';

export default function NovelPage() {
  const location = useLocation();
  const [novels, setNovels] = useState([]);
  const newNovel = location?.state ? { ...location.state } : false;

  useEffect(() => {
    fetch('novels.json')
      .then((res) => res.json())
      .then((data) => {
        const novels = [...data].map((novel) => ({
          ...novel,
          releaseDate: new Date(novel.releaseDate),
        }));
        newNovel ? setNovels([...novels, newNovel]) : setNovels([...novels]);
      });
  }, []);

  /* const handleDeleteNovel = (id) => {
    setNovels((prev) => prev.filter((novel) => movie.id !== +id));
  }; */

  return (
    <div>
      <h1>{novelsPageTitle}</h1>
      <table>
        <thead>
          <NovelListHead content={novelListThead} />
        </thead>
        <tbody>
          {/* <NovelList items={novels} onDelete={handleDeleteNovel} /> */}
          <NovelList items={novels} />
        </tbody>
      </table>
    </div>
  );
}