import { useState } from 'react';
import './App.css';
import Header from './components/Header';
import Query from './components/Query';
import {BasicTable} from './components/Table';

function App() {

  const [apiCall, setApiCall] = useState(null);

  const pull_data = (data) => {
    setApiCall(data);
}

  console.log(apiCall);

  return (
    <div className="App">
      <Header/>
      <Query func={pull_data} />
      <BasicTable/>
    </div>
  );
}

export default App;
