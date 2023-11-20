-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2023 a las 20:46:11
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
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `IdCuenta` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `IdTipoCuenta` int(11) NOT NULL,
  `NumbCuenta` char(16) NOT NULL,
  `CCI` char(20) NOT NULL,
  `SaldoDisponible` double NOT NULL,
  `SaldoContable` double NOT NULL,
  `EstadoCuenta` varchar(25) NOT NULL,
  `FechaApertura` datetime NOT NULL,
  `FechaCierre` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datospersonales`
--

CREATE TABLE `datospersonales` (
  `IdPersona` int(11) NOT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `DocuPersona` varchar(12) NOT NULL,
  `RUC` char(11) NOT NULL,
  `NombPersona` varchar(80) NOT NULL,
  `ApPaPersona` varchar(50) NOT NULL,
  `ApMaPersona` varchar(50) NOT NULL,
  `GenePersona` char(1) NOT NULL,
  `FechPersona` date NOT NULL,
  `DirePersona` varchar(120) NOT NULL,
  `CeluPersona` varchar(9) NOT NULL,
  `EmailPersona` varchar(250) NOT NULL,
  `IdDistrito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `datospersonales`
--

INSERT INTO `datospersonales` (`IdPersona`, `IdTipoDocumento`, `DocuPersona`, `RUC`, `NombPersona`, `ApPaPersona`, `ApMaPersona`, `GenePersona`, `FechPersona`, `DirePersona`, `CeluPersona`, `EmailPersona`, `IdDistrito`) VALUES
(1, 1, '70891324', '20547896301', 'Juan', 'Pérez', 'López', 'M', '1990-05-15', 'Av. Principal 123', '987654321', 'jperez@gmail.com', 4),
(2, 2, '59823467', '17459680201', 'María', 'González', 'Martínez', 'F', '1985-08-22', 'Jr. Lima 456', '951234567', 'mgonzalez@gmail.com', 12),
(3, 1, '45678901', '10293847561', 'Luis', 'Rodríguez', 'Sánchez', 'M', '1998-02-10', 'Calle 5 de Mayo 789', '976543210', 'lrodriguez@gmail.com', 21),
(4, 1, '82345690', '30564789231', 'Ana', 'Hernández', 'Ramírez', 'F', '1980-11-28', 'Ovalo Los Pinos 234', '945678901', 'ahernandez@gmail.com', 5),
(5, 2, '38901245', '17856324012', 'Carlos', 'García', 'Torres', 'M', '1995-07-03', 'Urb. Santa Rosa Mz. 12', '910123456', 'cgarcia@gmail.com', 16),
(6, 1, '67890543', '25019483756', 'Laura', 'Martínez', 'Díaz', 'F', '1987-04-18', 'Callejón Los Pájaros 567', '965432109', 'lmartinez@gmail.com', 7),
(7, 2, '12456789', '36521870943', 'Pedro', 'López', 'Vargas', 'M', '1992-09-05', 'Av. Primavera 890', '932109876', 'plopez@gmail.com', 10),
(8, 1, '89012456', '51023746829', 'Elena', 'Sánchez', 'Gómez', 'F', '1983-12-12', 'Jr. Libertad 123', '998765432', 'esanchez@gmail.com', 2),
(9, 1, '56389012', '67894510237', 'Javier', 'Ramírez', 'Pérez', 'M', '1993-06-20', 'Av. Los Laureles 456', '927654321', 'jramirez@gmail.com', 14),
(10, 2, '34012567', '12345098761', 'Silvia', 'Díaz', 'Flores', 'F', '1989-03-25', 'Calle Principal 789', '983210987', 'sdiaz@gmail.com', 26),
(11, 1, '72755161', '19382746501', 'Andrés', 'Gómez', 'Salvo', 'M', '2001-01-08', 'Av. América 456', '906213755', 'agomez@gmail.com', 12),
(12, 1, '72847211', '87654321012', 'Anthony', 'Caurino', 'Collantes', 'M', '2004-07-10', 'Calle Los Olivos 789', '925216367', 'acaurino@gmail.com', 12),
(13, 1, '74999126', '45321967804', 'Jeanpierre', 'Conejo', 'Sánchez', 'M', '1995-09-06', 'Jr. Puno 234', '917476705', 'jconejo@gmail.com', 5),
(14, 1, '73594630', '12983750465', 'Carlos', 'Sipan', 'Lozano', 'M', '2003-08-23', 'Urb. San Isidro Mz. 12', '980303113', 'csipan@gmail.com', 13),
(15, 1, '75146976', '90876543210', 'Denver', 'Chiroque', 'Paulino', 'M', '2003-10-26', 'Av. Los Robles 890', '928840886', 'dchiroque@gmail.com', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleprestamo`
--

CREATE TABLE `detalleprestamo` (
  `IdDetallePrestamo` int(11) NOT NULL,
  `IdTipoInformacionBien` int(11) NOT NULL,
  `CuotasAdicionales` char(1) NOT NULL,
  `FechaPago` date NOT NULL,
  `Monto` double NOT NULL,
  `Moneda` varchar(25) NOT NULL,
  `Tasa` double NOT NULL,
  `Tiempo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distrito`
--

CREATE TABLE `distrito` (
  `IdDistrito` int(11) NOT NULL,
  `DenoDistrito` varchar(50) NOT NULL,
  `ProvDistrito` varchar(50) NOT NULL,
  `DepaDistrito` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `distrito`
--

INSERT INTO `distrito` (`IdDistrito`, `DenoDistrito`, `ProvDistrito`, `DepaDistrito`) VALUES
(1, 'Ámbar', 'Huaura', 'Lima'),
(2, 'Caleta de Carquín', 'Huaura', 'Lima'),
(3, 'Checras', 'Huaura', 'Lima'),
(4, 'Huacho', 'Huaura', 'Lima'),
(5, 'Hualmay', 'Huaura', 'Lima'),
(6, 'Huaura', 'Huaura', 'Lima'),
(7, 'Leoncio Prado', 'Huaura', 'Lima'),
(8, 'Paccho', 'Huaura', 'Lima'),
(9, 'Santa Leonor', 'Huaura', 'Lima'),
(10, 'Santa María', 'Huaura', 'Lima'),
(11, 'Sayán', 'Huaura', 'Lima'),
(12, 'Végueta', 'Huaura', 'Lima'),
(13, 'Barranca', 'Barranca', 'Lima'),
(14, 'Paramonga', 'Barranca', 'Lima'),
(15, 'Pativilca', 'Barranca', 'Lima'),
(16, 'Supe', 'Barranca', 'Lima'),
(17, 'Supe Puerto', 'Barranca', 'Lima'),
(18, 'Atavillos Alto', 'Huaral', 'Lima'),
(19, 'Atavillos Bajo', 'Huaral', 'Lima'),
(20, 'Aucallama', 'Huaral', 'Lima'),
(21, 'Chancay', 'Huaral', 'Lima'),
(22, 'Huaral', 'Huaral', 'Lima'),
(23, 'Ihuarí', 'Huaral', 'Lima'),
(24, 'Lampían', 'Huaral', 'Lima'),
(25, 'Pacaraos', 'Huaral', 'Lima'),
(26, 'San Miguel de Acos', 'Huaral', 'Lima'),
(27, 'Santa Cruz de Andamarca', 'Huaral', 'Lima'),
(28, 'Sumbilca', 'Huaral', 'Lima'),
(29, 'Veintisiete de Noviembre', 'Huaral', 'Lima');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacionescuentaspropias`
--

CREATE TABLE `operacionescuentaspropias` (
  `IdOperacionCP` int(11) NOT NULL,
  `IdTipoOperacion` int(11) NOT NULL,
  `IdCuenta` int(11) NOT NULL,
  `MontoOperacionCP` double NOT NULL,
  `MonedaOperacionCP` varchar(20) NOT NULL,
  `FechaOperacionCP` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacionesotrascuentas`
--

CREATE TABLE `operacionesotrascuentas` (
  `IdOperacionOC` int(11) NOT NULL,
  `IdTipoOperacion` int(11) NOT NULL,
  `IdCuentaOrigen` int(11) NOT NULL,
  `IdCuentaDestino` int(11) NOT NULL,
  `MontoOperacionOC` double NOT NULL,
  `MonedaOperacionOC` varchar(20) NOT NULL,
  `FechaOperacionOC` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `IdPrestamo` int(11) NOT NULL,
  `IdTipoPrestamo` int(11) NOT NULL,
  `IdCuenta` int(11) NOT NULL,
  `IdTipoComprobante` int(11) NOT NULL,
  `IdDetallePrestamo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocomprobante`
--

CREATE TABLE `tipocomprobante` (
  `IdTipoComprobante` int(11) NOT NULL,
  `DenoTipoComprobante` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipocomprobante`
--

INSERT INTO `tipocomprobante` (`IdTipoComprobante`, `DenoTipoComprobante`) VALUES
(1, 'Virtual'),
(2, 'Físico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocuenta`
--

CREATE TABLE `tipocuenta` (
  `IdTipoCuenta` int(11) NOT NULL,
  `DenoTipoCuenta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipocuenta`
--

INSERT INTO `tipocuenta` (`IdTipoCuenta`, `DenoTipoCuenta`) VALUES
(1, 'Cuenta Corriente'),
(2, 'Cuenta de Ahorro'),
(3, 'Cuenta de Plazo Fijo'),
(4, 'Cuenta Sueldo'),
(5, 'Cuenta CTS'),
(6, 'Cuenta Independencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE `tipodocumento` (
  `IdTipoDocumento` int(11) NOT NULL,
  `DenoTipoDocumento` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`IdTipoDocumento`, `DenoTipoDocumento`) VALUES
(1, 'DNI'),
(2, 'Carnet de Extranjería');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoinformacionbien`
--

CREATE TABLE `tipoinformacionbien` (
  `IdTipoInformacionBien` int(11) NOT NULL,
  `DenoTipoInformacionBien` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipoinformacionbien`
--

INSERT INTO `tipoinformacionbien` (`IdTipoInformacionBien`, `DenoTipoInformacionBien`) VALUES
(1, 'Sin Seguro'),
(2, 'Inmueble Casa'),
(3, 'Inmueble Departamento');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipooperacion`
--

CREATE TABLE `tipooperacion` (
  `IdTipoOperacion` int(11) NOT NULL,
  `DenoTipoOperacion` varchar(60) NOT NULL,
  `CategoriaTipoOperacion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipooperacion`
--

INSERT INTO `tipooperacion` (`IdTipoOperacion`, `DenoTipoOperacion`, `CategoriaTipoOperacion`) VALUES
(1, 'Depósito', 'Cuentas Propias'),
(2, 'Retiro', 'Cuentas Propias'),
(3, 'Transferencia', 'Otras Cuentas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoprestamo`
--

CREATE TABLE `tipoprestamo` (
  `IdTipoPrestamo` int(11) NOT NULL,
  `DenoTipoPrestamo` varchar(60) NOT NULL,
  `CategoriaTipoPrestamo` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipoprestamo`
--

INSERT INTO `tipoprestamo` (`IdTipoPrestamo`, `DenoTipoPrestamo`, `CategoriaTipoPrestamo`) VALUES
(1, 'Préstamo Libre Disponibilidad', 'Personal'),
(2, 'Préstamo Auto Seminuevo', 'Personal'),
(3, 'Préstamo de Estudio', 'Personal'),
(4, 'Préstamo Convenio', 'Personal'),
(5, 'Préstamo Mi Vivienda', 'Mi Vivienda'),
(6, 'Préstamo Mi Vivienda Sostenible', 'Mi Vivienda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `IdTipoUsuario` int(11) NOT NULL,
  `DenoTipoUsuario` varchar(50) NOT NULL,
  `NivelTipoUsuario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`IdTipoUsuario`, `DenoTipoUsuario`, `NivelTipoUsuario`) VALUES
(1, 'Empleado', 'Administrador'),
(2, 'Empleado', 'Empleado'),
(3, 'Usuario', 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL,
  `IdTipoUsuario` int(11) NOT NULL,
  `IdPersona` int(11) NOT NULL,
  `DenoUsuario` varchar(50) NOT NULL,
  `PassUsuario` varchar(64) NOT NULL,
  `Autenticacion` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IdUsuario`, `IdTipoUsuario`, `IdPersona`, `DenoUsuario`, `PassUsuario`, `Autenticacion`) VALUES
(16, 1, 11, 'Inge', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '5UXQLRVTRDQQ2UNY'),
(17, 1, 12, 'ZeNktr4k', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL),
(18, 1, 13, 'jconejo', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL),
(19, 1, 14, 'caldito', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '45PBYTVDGRJUMKPQ'),
(20, 1, 15, 'Zamber', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`IdCuenta`),
  ADD KEY `IdUsuario` (`IdUsuario`),
  ADD KEY `IdTipoCuenta` (`IdTipoCuenta`);

--
-- Indices de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD PRIMARY KEY (`IdPersona`),
  ADD KEY `IdTipoDocumento` (`IdTipoDocumento`),
  ADD KEY `IdDistrito` (`IdDistrito`);

--
-- Indices de la tabla `detalleprestamo`
--
ALTER TABLE `detalleprestamo`
  ADD PRIMARY KEY (`IdDetallePrestamo`),
  ADD KEY `IdTipoInformacionBien` (`IdTipoInformacionBien`);

--
-- Indices de la tabla `distrito`
--
ALTER TABLE `distrito`
  ADD PRIMARY KEY (`IdDistrito`);

--
-- Indices de la tabla `operacionescuentaspropias`
--
ALTER TABLE `operacionescuentaspropias`
  ADD PRIMARY KEY (`IdOperacionCP`),
  ADD KEY `IdTipoOperacion` (`IdTipoOperacion`),
  ADD KEY `IdCuenta` (`IdCuenta`);

--
-- Indices de la tabla `operacionesotrascuentas`
--
ALTER TABLE `operacionesotrascuentas`
  ADD PRIMARY KEY (`IdOperacionOC`),
  ADD KEY `IdTipoOperacion` (`IdTipoOperacion`),
  ADD KEY `IdCuentaOrigen` (`IdCuentaOrigen`),
  ADD KEY `IdCuentaDestino` (`IdCuentaDestino`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`IdPrestamo`),
  ADD KEY `IdTipoPrestamo` (`IdTipoPrestamo`),
  ADD KEY `IdCuenta` (`IdCuenta`),
  ADD KEY `IdTipoComprobante` (`IdTipoComprobante`),
  ADD KEY `IdDetallePrestamo` (`IdDetallePrestamo`);

--
-- Indices de la tabla `tipocomprobante`
--
ALTER TABLE `tipocomprobante`
  ADD PRIMARY KEY (`IdTipoComprobante`);

--
-- Indices de la tabla `tipocuenta`
--
ALTER TABLE `tipocuenta`
  ADD PRIMARY KEY (`IdTipoCuenta`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`IdTipoDocumento`);

--
-- Indices de la tabla `tipoinformacionbien`
--
ALTER TABLE `tipoinformacionbien`
  ADD PRIMARY KEY (`IdTipoInformacionBien`);

--
-- Indices de la tabla `tipooperacion`
--
ALTER TABLE `tipooperacion`
  ADD PRIMARY KEY (`IdTipoOperacion`);

--
-- Indices de la tabla `tipoprestamo`
--
ALTER TABLE `tipoprestamo`
  ADD PRIMARY KEY (`IdTipoPrestamo`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`IdTipoUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IdUsuario`),
  ADD KEY `IdTipoUsuario` (`IdTipoUsuario`),
  ADD KEY `IdPersona` (`IdPersona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `IdCuenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  MODIFY `IdPersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `detalleprestamo`
--
ALTER TABLE `detalleprestamo`
  MODIFY `IdDetallePrestamo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `distrito`
--
ALTER TABLE `distrito`
  MODIFY `IdDistrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `operacionescuentaspropias`
--
ALTER TABLE `operacionescuentaspropias`
  MODIFY `IdOperacionCP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `operacionesotrascuentas`
--
ALTER TABLE `operacionesotrascuentas`
  MODIFY `IdOperacionOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `IdPrestamo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipocomprobante`
--
ALTER TABLE `tipocomprobante`
  MODIFY `IdTipoComprobante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipocuenta`
--
ALTER TABLE `tipocuenta`
  MODIFY `IdTipoCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `IdTipoDocumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipoinformacionbien`
--
ALTER TABLE `tipoinformacionbien`
  MODIFY `IdTipoInformacionBien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipooperacion`
--
ALTER TABLE `tipooperacion`
  MODIFY `IdTipoOperacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipoprestamo`
--
ALTER TABLE `tipoprestamo`
  MODIFY `IdTipoPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `IdTipoUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`),
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`IdTipoCuenta`) REFERENCES `tipocuenta` (`IdTipoCuenta`);

--
-- Filtros para la tabla `datospersonales`
--
ALTER TABLE `datospersonales`
  ADD CONSTRAINT `datospersonales_ibfk_1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`IdTipoDocumento`),
  ADD CONSTRAINT `datospersonales_ibfk_2` FOREIGN KEY (`IdDistrito`) REFERENCES `distrito` (`IdDistrito`);

--
-- Filtros para la tabla `detalleprestamo`
--
ALTER TABLE `detalleprestamo`
  ADD CONSTRAINT `detalleprestamo_ibfk_1` FOREIGN KEY (`IdTipoInformacionBien`) REFERENCES `tipoinformacionbien` (`IdTipoInformacionBien`);

--
-- Filtros para la tabla `operacionescuentaspropias`
--
ALTER TABLE `operacionescuentaspropias`
  ADD CONSTRAINT `operacionescuentaspropias_ibfk_1` FOREIGN KEY (`IdTipoOperacion`) REFERENCES `tipooperacion` (`IdTipoOperacion`),
  ADD CONSTRAINT `operacionescuentaspropias_ibfk_2` FOREIGN KEY (`IdCuenta`) REFERENCES `cuenta` (`IdCuenta`);

--
-- Filtros para la tabla `operacionesotrascuentas`
--
ALTER TABLE `operacionesotrascuentas`
  ADD CONSTRAINT `operacionesotrascuentas_ibfk_1` FOREIGN KEY (`IdTipoOperacion`) REFERENCES `tipooperacion` (`IdTipoOperacion`),
  ADD CONSTRAINT `operacionesotrascuentas_ibfk_2` FOREIGN KEY (`IdCuentaOrigen`) REFERENCES `cuenta` (`IdCuenta`),
  ADD CONSTRAINT `operacionesotrascuentas_ibfk_3` FOREIGN KEY (`IdCuentaDestino`) REFERENCES `cuenta` (`IdCuenta`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`IdTipoPrestamo`) REFERENCES `tipoprestamo` (`IdTipoPrestamo`),
  ADD CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`IdCuenta`) REFERENCES `cuenta` (`IdCuenta`),
  ADD CONSTRAINT `prestamo_ibfk_3` FOREIGN KEY (`IdTipoComprobante`) REFERENCES `tipocomprobante` (`IdTipoComprobante`),
  ADD CONSTRAINT `prestamo_ibfk_4` FOREIGN KEY (`IdDetallePrestamo`) REFERENCES `detalleprestamo` (`IdDetallePrestamo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`IdTipoUsuario`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`IdPersona`) REFERENCES `datospersonales` (`IdPersona`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
