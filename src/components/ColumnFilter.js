import React from 'react'

export const ColumnFilter = ({ column }) => {
  const { filterValue, setFilter } = column
  return (
    <span className="search-span">
      <span>
      Search:{' '}
      </span>
      <input
        value={filterValue || ''}
        onChange={e => setFilter(e.target.value)}
      />
    </span>
  )
}