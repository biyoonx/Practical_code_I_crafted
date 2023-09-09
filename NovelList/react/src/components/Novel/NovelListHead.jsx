import React from 'react';

export default function NovelListHead({ content }) {
  const key = Array(content.length)
    .fill(0)
    .map((a, b) => a + b);

  return (
    <>
      {content.map((th, i) => (
        <th key={key[i]}>{th}</th>
      ))}
    </>
  );
}