import React, { useState } from 'react';
import Button from '../UI/Button';

export default function NovelItem({
  id,
  title,
  genre,
  releaseDate,
  action,
  // onDelete,
}) {
  const [show, setShow] = useState(true);
  const handleShow = () => {
    setShow((isShow) => !isShow);
  };
  /* const handleDelete = (id) => {
    onDelete(id);
  }; */

  return (
    <>
      {show && (
        <tr>
          <td>{id}</td>
          <td>{title}</td>
          <td>{genre}</td>
          <td>{toCustomDateString(releaseDate)}</td>
          <td>
            {/* <Button content={action} onClick={handleDelete} id={id}></Button> */}
            <Button content={action} onClick={handleShow} id={id}></Button>
          </td>
        </tr>
      )}
    </>
  );
}

function toCustomDateString(date) {
  const year = date.getFullYear();
  const month = date.toLocaleString('en-US', { month: '2-digit' });
  const day = date.toLocaleString('en-US', { day: '2-digit' });
  return `${year}-${month}-${day}`;
}