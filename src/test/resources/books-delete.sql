INSERT INTO books (id, title, author, description) VALUES
    (1, 'Deleted Book', 'Deleted Author', 'Description for deleted book');

DELETE FROM books WHERE id = 1;
