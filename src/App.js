import './App.css';
import Header from './components/Header';
import Query from './components/Query';
import {BasicTable} from './components/Table';

function App() {
  return (
    <div className="App">
      <Header/>
      <Query/>
      <BasicTable/>
    </div>
  );
}

export default App;
