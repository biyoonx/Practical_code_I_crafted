import React from 'react';

// export default function Button({ content, id, onClick }) {
export default function Button({ content, onClick }) {
  const handleClick = () => {
    // onClick(id);
    onClick();
  };

  return <button onClick={handleClick}>{content}</button>;
}
