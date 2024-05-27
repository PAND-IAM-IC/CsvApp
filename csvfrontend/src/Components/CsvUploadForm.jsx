import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Alert } from 'reactstrap';
import axios from 'axios';

const CsvUploadForm = ({ onUploadSuccess }) => {
  const [file, setFile] = useState(null);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadError, setUploadError] = useState('');
  const [uploadSuccess, setUploadSuccess] = useState(false);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setUploadError('');
    setUploadSuccess(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) {
      setUploadError('Please select a file to upload.');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:3002/api/csv/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        onUploadProgress: (progressEvent) => {
          setUploadProgress(Math.round((progressEvent.loaded * 100) / progressEvent.total));
        },
      });

      setUploadSuccess(true);
      setUploadError('');
      onUploadSuccess(response.data);
    } catch (error) {
      setUploadError('Error uploading file. Please try again.');
      setUploadSuccess(false);
      console.error('Error uploading file:', error);
    }
  };

  return (
    <div>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for="csvFile">Upload CSV File</Label>
          <Input type="file" name="file" id="csvFile" onChange={handleFileChange} />
        </FormGroup>
        {uploadProgress > 0 && <div>Upload Progress: {uploadProgress}%</div>}
        {uploadError && <Alert color="danger">{uploadError}</Alert>}
        {uploadSuccess && <Alert color="success">File uploaded successfully!</Alert>}
        <Button type="submit" color="primary">Upload</Button>
      </Form>
    </div>
  );
};

export default CsvUploadForm;
