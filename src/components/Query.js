import React,{useState,useEffect} from 'react';
import "react-datetime/css/react-datetime.css";
import Datetime from "react-datetime";
import Select from 'react-select';
// import { w3cwebsocket as W3CWebSocket } from "websocket";

// const client = new W3CWebSocket('http://localhost:9000/');

const options = [
    { value: 'IMSI', label: 'IMSI' },
    { value: 'TAI', label: 'TAI' },
  ];

export default function Query({func}) {

    // const webSocket = new WebSocket('wss://localhost:3000/');


    const [selectedStartDate, setSelectedStartDate] = useState(null);
    const [selectedEndDate,setSelectedEndDate] = useState(null);
    const [selectedDropdownOption, setSelectedDropdownOption] = useState(null);
    const [selectedTextOption, setSelectedTextOption] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        const queryData = {
            startTimeStamp : selectedStartDate._d,
            endTimeStamp: selectedEndDate._d,
            keyType: selectedDropdownOption,
        };

        if(selectedDropdownOption != null){
          queryData.number =  selectedTextOption.target.value;
        }else{
          queryData.number = null;
        }
        func(queryData);
        // client.send()
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
