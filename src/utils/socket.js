import React from 'react';
import { w3cwebsocket as W3CWebSocket } from "websocket";

const client = new w3cwebsocket('http://localhost:9000/');

export default function socket() {
  return (
    <div>socket</div>
  )
}
