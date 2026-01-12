CREATE DATABASE stok_barang;

USE stok_barang;

CREATE TABLE barang (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kode VARCHAR(50),
    nama VARCHAR(100),
    stok INT,
    harga DOUBLE
);
