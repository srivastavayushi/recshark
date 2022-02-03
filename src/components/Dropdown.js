import React, { useState } from 'react';
import Select from 'react-select';

const options = [
  { value: 'IMSI', label: 'IMSI' },
  { value: 'TAI', label: 'TAI' },
];

export default function Dropdown() {

    const [selectedOption, setSelectedOption] = useState(null);

  return (
  <>
    <Select
        defaultValue={selectedOption}
        onChange={setSelectedOption}
        options={options}
        className='dropdown'
      />
      {/* {selectedOption && (<div>
        <input type="text" name="name" />
      </div>)} */}
  </>
  );
}
