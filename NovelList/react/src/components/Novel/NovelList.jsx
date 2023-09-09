import React, { useState } from 'react';
import NovelItem from './NovelItem';

/* export default function NovelList({ items, onDelete }) {
  const handleDelete = (id) => {
    onDelete(id);
  }; */
export default function NovelList({ items }) {
  return (
    <>
      {items.map((item) => {
        const { id, title, genre, releaseDate, action } = item;
        return (
          <NovelItem
            key={id}
            id={id}
            title={title}
            genre={genre}
            releaseDate={releaseDate}
            action={action}
            // onDelete={handleDelete}
          />
        );
      })}
    </>
  );
}