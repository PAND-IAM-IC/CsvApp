import React, { useState, useEffect } from 'react';
import { Table } from 'reactstrap';
import axios from 'axios';
import Pagination from './Pagination';

const CsvDataDisplay = () => {
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [currentData, setCurrentData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:3002/api/csv/records');
        setData(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    setCurrentData(data.slice((currentPage - 1) * 10, currentPage * 10));
  }, [data, currentPage]);

  return (
    <div>
      <Table striped>
        <thead>
          <tr>
            <th>ID</th>
            <th>Credit Score</th>
            <th>Credit Lines</th>
          </tr>
        </thead>
        <tbody>
          {currentData.map((record, index) => (
            <tr key={index}>
              <td>{record.id}</td>
              <td>{record.creditScore}</td>
              <td>{record.creditLines}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Pagination currentPage={currentPage} totalPages={totalPages} onPageChange={setCurrentPage} />
    </div>
  );
};

export default CsvDataDisplay;
