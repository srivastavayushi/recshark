import { ColumnFilter } from "./ColumnFilter";

export const COLUMNS = [
    {
      Header: 'Id',
      Footer: 'Id',
      accessor: 'id',
      Filter:ColumnFilter,
    },
    {
      Header: 'Hex Dom',
      Footer: 'Hex Dom',
      accessor: 'hexdom',
      Filter:ColumnFilter,
    },
    {
      Header: 'Message Type',
      Footer: 'Message Type',
      accessor: 'message_type',
      Filter:ColumnFilter,
    },
    {
      Header: 'IMSI',
      Footer: 'IMSI',
      accessor: 'IMSI',
      Filter:ColumnFilter,
    },
    {
      Header: 'Time',
      Footer: 'Time',
      accessor: 'request_timestamp',
      Filter:ColumnFilter,
    },
    {
      Header: 'MEI',
      Footer: 'MEI',
      accessor: 'MEI',
      Filter:ColumnFilter,
    },
    {
      Header: 'TAI',
      Footer: 'TAI',
      accessor: 'TAI',
      Filter:ColumnFilter,
    },
  ]