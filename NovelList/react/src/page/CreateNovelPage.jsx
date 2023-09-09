import React from 'react';
import { createPageTitle, inputPlaceholderText } from '../createMoviePage';
import CreateNovelForm from '../components/CreateNovel/CreateNovelForm';

export default function CreateNovelPage() {
  const { id, title, genre, releaseDate } = inputPlaceholderText;
  // const handleAddNovel = (newNovel) => {
  //   addNovel(newNovel);
  // };

  return (
    <div>
      <h1>{createPageTitle}</h1>
      <CreateNovelForm
        id={id}
        title={title}
        genre={genre}
        releaseDate={releaseDate}
        // onAddNovel={handleAddNovel}
      />
    </div>
  );
}