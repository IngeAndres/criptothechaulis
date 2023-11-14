-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2023 a las 02:45:26
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `criptothechaulis`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codigoCliente` int(11) NOT NULL,
  `codigoTipoDocumento` int(11) NOT NULL,
  `docuClie` varchar(45) NOT NULL,
  `appaClie` varchar(45) NOT NULL,
  `apmaClie` varchar(45) NOT NULL,
  `nombClie` varchar(45) NOT NULL,
  `telfClie` varchar(45) NOT NULL,
  `direClie` varchar(45) NOT NULL,
  `codigoEstado` int(11) NOT NULL,
  `codigoDistrito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigoCliente`, `codigoTipoDocumento`, `docuClie`, `appaClie`, `apmaClie`, `nombClie`, `telfClie`, `direClie`, `codigoEstado`, `codigoDistrito`) VALUES
(1, 1, '72847211', 'Caurino', 'Collantes', 'Anthony Javier', '925216367', 'Av. Miguel Grau N° 450 ', 1, 1),
(3, 1, '72755161', 'Gómez', 'Salvo', 'Andrés Eduardo', '906213755', 'Av. Las Palmeras S/N', 1, 1),
(4, 1, '74999126', 'Conejo', 'Sánchez', 'Jeanpierre Eduardo', '917476705', 'Psj. Bellavista S/N', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `codigoCuenta` int(11) NOT NULL,
  `codigoCliente` int(11) NOT NULL,
  `numeCuenta` varchar(45) NOT NULL,
  `codigoTipoCuenta` int(11) NOT NULL,
  `saldoCuenta` double NOT NULL,
  `fechApertCuenta` date NOT NULL,
  `codigoEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`codigoCuenta`, `codigoCliente`, `numeCuenta`, `codigoTipoCuenta`, `saldoCuenta`, `fechApertCuenta`, `codigoEstado`) VALUES
(1, 1, '874596325410265', 1, 5500, '2023-10-10', 1),
(3, 3, '825885461831713', 1, 7500, '2023-11-06', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `deposito`
--

CREATE TABLE `deposito` (
  `codigoDeposito` int(11) NOT NULL,
  `codigoCuenta` int(11) NOT NULL,
  `montoDeposito` double NOT NULL,
  `fechDeposito` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distrito`
--

CREATE TABLE `distrito` (
  `codigoDistrito` int(11) NOT NULL,
  `nombreDistrito` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `distrito`
--

INSERT INTO `distrito` (`codigoDistrito`, `nombreDistrito`) VALUES
(1, 'Vegueta'),
(2, 'Hualmay'),
(3, 'Barranca'),
(4, 'Huacho'),
(5, 'Huaura'),
(6, 'Aucallama'),
(7, 'Carquin'),
(8, 'Santa Maria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `codigoEstado` int(11) NOT NULL,
  `nombEsta` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`codigoEstado`, `nombEsta`) VALUES
(1, 'Activo'),
(2, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `codigoPrestamo` int(11) NOT NULL,
  `codigoCliente` int(11) NOT NULL,
  `plazoPrestamo` varchar(45) NOT NULL,
  `tasasPrestamo` double NOT NULL,
  `totalPedidoPrestamo` double NOT NULL,
  `totalPagarPrestamo` double NOT NULL,
  `fechPrestamo` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`codigoPrestamo`, `codigoCliente`, `plazoPrestamo`, `tasasPrestamo`, `totalPedidoPrestamo`, `totalPagarPrestamo`, `fechPrestamo`) VALUES
(1, 1, '100', 10, 11000, 10000, '2023-11-06 14:56:39');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `retiro`
--

CREATE TABLE `retiro` (
  `codigoRetiro` int(11) NOT NULL,
  `codigoCuenta` int(11) NOT NULL,
  `montoRetiro` double NOT NULL,
  `fechRetiro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocuenta`
--

CREATE TABLE `tipocuenta` (
  `codigoTipoCuenta` int(11) NOT NULL,
  `nombTipoCuenta` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipocuenta`
--

INSERT INTO `tipocuenta` (`codigoTipoCuenta`, `nombTipoCuenta`) VALUES
(1, 'Cuenta Corriente'),
(2, 'Cuenta Ahorro'),
(3, 'Cuenta Inversion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE `tipodocumento` (
  `codigoTipoDocumento` int(11) NOT NULL,
  `nombTipoDocumento` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`codigoTipoDocumento`, `nombTipoDocumento`) VALUES
(1, 'DNI'),
(2, 'Carnet De Extranjeria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transferencia`
--

CREATE TABLE `transferencia` (
  `codigoTransferencia` int(11) NOT NULL,
  `codigoCuentaOrigen` int(11) NOT NULL,
  `montoTransferencia` double NOT NULL,
  `codigoCuentaDestino` int(11) NOT NULL,
  `fechTransferencia` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transferencia`
--

INSERT INTO `transferencia` (`codigoTransferencia`, `codigoCuentaOrigen`, `montoTransferencia`, `codigoCuentaDestino`, `fechTransferencia`) VALUES
(1, 1, 1000, 3, '2023-11-06 16:20:35'),
(2, 1, 500, 3, '2023-11-06 16:21:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `codiUsua` char(8) NOT NULL,
  `logiUsua` varchar(45) NOT NULL,
  `passUsua` varchar(64) NOT NULL,
  `authUsua` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codiUsua`, `logiUsua`, `passUsua`, `authUsua`) VALUES
('40801418', 'kike', 'c3de533e9b7fe63b79f648687a30d2861edd92fe7c3cd1f2c485e0a605367624', 'N6SLSEWL44JEFGCC'),
('72755161', 'andres', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '62HIIDOAKBBSS3KP'),
('72847211', 'anthony', '502913bfdd49eab564282dff101e6d167321237eeec66eedb2a438ed80fdeaa0', 'ITFXWVF57BXI4HLI'),
('73594630', 'carlos', '7b85175b455060e3237e925f023053ca9515e8682a83c8b09911c724a1f8b75f', ''),
('74999126', 'jeanpierre', 'fbcc8fa66b9a007dc3649ee989950d38f12ed91b9f18a3d215b04ff77546dca0', 'ADT23WC2ZIEWVVHK'),
('75149676', 'denver', '4c6eb87b502e3e019acbd4b1e579bd1566104abc0914f5186df63e4833c993c2', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigoCliente`),
  ADD KEY `codigoTipoDocumento` (`codigoTipoDocumento`),
  ADD KEY `codigoEstado` (`codigoEstado`),
  ADD KEY `codigoDistrito` (`codigoDistrito`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`codigoCuenta`),
  ADD KEY `codigoCliente` (`codigoCliente`),
  ADD KEY `codigoTipoCuenta` (`codigoTipoCuenta`),
  ADD KEY `codigoEstado` (`codigoEstado`);

--
-- Indices de la tabla `deposito`
--
ALTER TABLE `deposito`
  ADD PRIMARY KEY (`codigoDeposito`),
  ADD KEY `codigoCuenta` (`codigoCuenta`);

--
-- Indices de la tabla `distrito`
--
ALTER TABLE `distrito`
  ADD PRIMARY KEY (`codigoDistrito`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`codigoEstado`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`codigoPrestamo`),
  ADD KEY `codigoCliente` (`codigoCliente`);

--
-- Indices de la tabla `retiro`
--
ALTER TABLE `retiro`
  ADD PRIMARY KEY (`codigoRetiro`),
  ADD KEY `codigoCuenta` (`codigoCuenta`);

--
-- Indices de la tabla `tipocuenta`
--
ALTER TABLE `tipocuenta`
  ADD PRIMARY KEY (`codigoTipoCuenta`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`codigoTipoDocumento`);

--
-- Indices de la tabla `transferencia`
--
ALTER TABLE `transferencia`
  ADD PRIMARY KEY (`codigoTransferencia`),
  ADD KEY `codigoCuentaOrigen` (`codigoCuentaOrigen`),
  ADD KEY `codigoCuentaDestino` (`codigoCuentaDestino`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codiUsua`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigoCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `codigoCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `deposito`
--
ALTER TABLE `deposito`
  MODIFY `codigoDeposito` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `distrito`
--
ALTER TABLE `distrito`
  MODIFY `codigoDistrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `codigoPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `retiro`
--
ALTER TABLE `retiro`
  MODIFY `codigoRetiro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipocuenta`
--
ALTER TABLE `tipocuenta`
  MODIFY `codigoTipoCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `codigoTipoDocumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `transferencia`
--
ALTER TABLE `transferencia`
  MODIFY `codigoTransferencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`codigoTipoDocumento`) REFERENCES `tipodocumento` (`codigoTipoDocumento`),
  ADD CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`codigoEstado`) REFERENCES `estado` (`codigoEstado`),
  ADD CONSTRAINT `cliente_ibfk_3` FOREIGN KEY (`codigoDistrito`) REFERENCES `distrito` (`codigoDistrito`);

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`codigoCliente`) REFERENCES `cliente` (`codigoCliente`),
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`codigoTipoCuenta`) REFERENCES `tipocuenta` (`codigoTipoCuenta`),
  ADD CONSTRAINT `cuenta_ibfk_3` FOREIGN KEY (`codigoEstado`) REFERENCES `estado` (`codigoEstado`);

--
-- Filtros para la tabla `deposito`
--
ALTER TABLE `deposito`
  ADD CONSTRAINT `deposito_ibfk_1` FOREIGN KEY (`codigoCuenta`) REFERENCES `cuenta` (`codigoCuenta`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `codigoCliente` FOREIGN KEY (`codigoCliente`) REFERENCES `cliente` (`codigoCliente`);

--
-- Filtros para la tabla `retiro`
--
ALTER TABLE `retiro`
  ADD CONSTRAINT `retiro_ibfk_1` FOREIGN KEY (`codigoCuenta`) REFERENCES `cuenta` (`codigoCuenta`);

--
-- Filtros para la tabla `transferencia`
--
ALTER TABLE `transferencia`
  ADD CONSTRAINT `transferencia_ibfk_1` FOREIGN KEY (`codigoCuentaOrigen`) REFERENCES `cuenta` (`codigoCuenta`),
  ADD CONSTRAINT `transferencia_ibfk_2` FOREIGN KEY (`codigoCuentaDestino`) REFERENCES `cuenta` (`codigoCuenta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
