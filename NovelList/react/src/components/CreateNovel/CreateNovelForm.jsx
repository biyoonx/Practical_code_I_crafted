import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// import { novelListData } from '../../novelData';

export default function CreateNovelForm({ id, title, genre, releaseDate }) {
  const navigate = useNavigate();
  const [enteredId, setEnteredId] = useState('');
  const [enteredTitle, setEnteredTitle] = useState('');
  const [enteredGenre, setEnteredGenre] = useState('');
  const [enteredReleaseDate, setEnteredReleaseDate] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();

    /* let dupTestResult = novelListData.find(
      (movie) => event.target.id === novel.id
    );
    if (dupTestResult) {
      return;
    } */

    const newNovel = {
      id: enteredId,
      title: enteredTitle,
      genre: enteredGenre,
      releaseDate: new Date(enteredReleaseDate),
      action: 'Delete',
    };

    navigate('/', { state: { ...newNovel } });
    setEnteredId('');
    setEnteredTitle('');
    setEnteredGenre('');
    setEnteredReleaseDate('');
  };
  const handleChange = (event, setter) => {
    setter(event.target.value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <ul className="createNovelForm">
        <li>
          <input
            type="number"
            placeholder={id}
            required
            onChange={(e) => handleChange(e, setEnteredId)}
          />
        </li>
        <li>
          <input
            type="text"
            placeholder={title}
            required
            onChange={(e) => handleChange(e, setEnteredTitle)}
          />
        </li>
        <li>
          <input
            type="text"
            placeholder={genre}
            required
            onChange={(e) => handleChange(e, setEnteredGenre)}
          />
        </li>
        <li>
          <label htmlFor="releaseDate">{releaseDate} : </label>
          <input
            type="date"
            id="releaseDate"
            required
            onChange={(e) => handleChange(e, setEnteredReleaseDate)}
          />
        </li>
        <li>
          <button type="submit">Novel</button>
        </li>
      </ul>
    </form>
  );
}