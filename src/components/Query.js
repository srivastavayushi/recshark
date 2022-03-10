import React,{useState} from 'react';
import "react-datetime/css/react-datetime.css";
import Datetime from "react-datetime";
import Select from 'react-select';

const options = [
    { value: 'IMSI', label: 'IMSI' },
    { value: 'TAI', label: 'TAI' },
  ];

export default function Query() {

    const [selectedStartDate, setSelectedStartDate] = useState(null);
    const [selectedEndDate,setSelectedEndDate] = useState(null);
    const [selectedDropdownOption, setSelectedDropdownOption] = useState(null);
    const [selectedTextOption, setSelectedTextOption] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        if(selectedStartDate._d>selectedEndDate._d){
          alert('Start Time cannot be after EndTime');
        }
        const queryData = {
            startTimeStamp : selectedStartDate._d,
            endTimeStamp: selectedEndDate._d,
            keyType: selectedDropdownOption,
            number:selectedTextOption.target.value,
        }
        console.log(queryData);
    }
    

  return (
  <form className="Query" onSubmit={handleSubmit}>
      <div className="query-text">Start Time : </div>
       <Datetime 
        defaultValue={selectedStartDate}
        onChange={setSelectedStartDate}
        />
       <div className="query-text">End Time : </div>
       <Datetime 
       defaultValue={selectedEndDate}
       onChange={setSelectedEndDate}
       />
       <Select
        defaultValue={selectedDropdownOption}
        onChange={setSelectedDropdownOption}
        options={options}
        className='dropdown'
      />
      {selectedDropdownOption && (<div>
        <input 
            type="number" 
            name="name"
            defaultValue={selectedTextOption}
            onChange={setSelectedTextOption}
         />
      </div>)}
       <button type="submit">Submit</button>
  </form>
  );
}
