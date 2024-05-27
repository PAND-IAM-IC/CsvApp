import React from 'react';
import { Pagination, PaginationItem, PaginationLink } from 'reactstrap';

const CustomPagination = ({ currentPage, totalPages, onPageChange }) => {
  const renderPaginationItems = () => {
    const items = [];
    for (let i = 1; i <= totalPages; i++) {
      items.push(
        <PaginationItem key={i} active={currentPage === i}>
          <PaginationLink onClick={() => onPageChange(i)}>{i}</PaginationLink>
        </PaginationItem>
      );
    }
    return items;
  };

  return (
    <Pagination>
      <PaginationItem disabled={currentPage === 1}>
        <PaginationLink previous onClick={() => onPageChange(currentPage - 1)} />
      </PaginationItem>
      {renderPaginationItems()}
      <PaginationItem disabled={currentPage === totalPages}>
        <PaginationLink next onClick={() => onPageChange(currentPage + 1)} />
      </PaginationItem>
    </Pagination>
  );
};

export default CustomPagination;
