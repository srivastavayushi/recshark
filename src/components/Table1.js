import React,{useState,useEffect} from 'react'
import axios from "axios";

export default function Table() {

    const [users, setUsers] = useState({ hits: [] });

    useEffect(() => {
      const fetchData = async () => {
        const { data } = await axios(
          "https://jsonplaceholder.typicode.com/users"
        );
        console.log(data);
        setUsers({ hits: data });
      };
      fetchData();
    }, [setUsers]);

  return (
    <div>
              <ul>
        {users.hits &&
          users.hits.map(item => (
            <li key={item.id}>
              <span>{item.name}</span>
            </li>
          ))}
      </ul>

    </div>
  )
}
