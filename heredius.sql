-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-11-2023 a las 00:48:08
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `heredius`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_Cliente` int(11) NOT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `DNI` int(11) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `Nombre` varchar(30) DEFAULT NULL,
  `Direccion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `ID_Factura` int(11) NOT NULL,
  `ID_OdV` int(11) DEFAULT NULL,
  `ID_OdD` int(11) DEFAULT NULL,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Nombre_Cliente` varchar(30) DEFAULT NULL,
  `Fecha` varchar(10) DEFAULT NULL,
  `Monto_total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordendedigitalizacion`
--

CREATE TABLE `ordendedigitalizacion` (
  `ID_OdD` int(11) NOT NULL,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Fecha` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordendeventa`
--

CREATE TABLE `ordendeventa` (
  `ID_OdV` int(11) NOT NULL,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Fecha` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sistema`
--

CREATE TABLE `sistema` (
  `ID_Sistema` int(11) NOT NULL,
  `ID_OdV` int(11) DEFAULT NULL,
  `ID_Cliente` int(11) DEFAULT NULL,
  `Nombre` varchar(30) DEFAULT NULL,
  `Descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_Cliente`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`ID_Factura`),
  ADD KEY `ID_OdV` (`ID_OdV`),
  ADD KEY `ID_OdD` (`ID_OdD`),
  ADD KEY `ID_Cliente` (`ID_Cliente`);

--
-- Indices de la tabla `ordendedigitalizacion`
--
ALTER TABLE `ordendedigitalizacion`
  ADD PRIMARY KEY (`ID_OdD`),
  ADD KEY `ID_Cliente` (`ID_Cliente`);

--
-- Indices de la tabla `ordendeventa`
--
ALTER TABLE `ordendeventa`
  ADD PRIMARY KEY (`ID_OdV`),
  ADD KEY `ID_Cliente` (`ID_Cliente`);

--
-- Indices de la tabla `sistema`
--
ALTER TABLE `sistema`
  ADD PRIMARY KEY (`ID_Sistema`),
  ADD KEY `ID_OdV` (`ID_OdV`),
  ADD KEY `ID_Cliente` (`ID_Cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `ID_Factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ordendedigitalizacion`
--
ALTER TABLE `ordendedigitalizacion`
  MODIFY `ID_OdD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ordendeventa`
--
ALTER TABLE `ordendeventa`
  MODIFY `ID_OdV` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sistema`
--
ALTER TABLE `sistema`
  MODIFY `ID_Sistema` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`ID_OdV`) REFERENCES `ordendeventa` (`ID_OdV`),
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`ID_OdD`) REFERENCES `ordendedigitalizacion` (`ID_OdD`),
  ADD CONSTRAINT `factura_ibfk_3` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`);

--
-- Filtros para la tabla `ordendedigitalizacion`
--
ALTER TABLE `ordendedigitalizacion`
  ADD CONSTRAINT `ordendedigitalizacion_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`);

--
-- Filtros para la tabla `ordendeventa`
--
ALTER TABLE `ordendeventa`
  ADD CONSTRAINT `ordendeventa_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`);

--
-- Filtros para la tabla `sistema`
--
ALTER TABLE `sistema`
  ADD CONSTRAINT `sistema_ibfk_1` FOREIGN KEY (`ID_OdV`) REFERENCES `ordendeventa` (`ID_OdV`),
  ADD CONSTRAINT `sistema_ibfk_2` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
