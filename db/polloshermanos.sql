-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 04-01-2026 a las 05:16:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `polloshermanos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conexion_item_variante`
--

CREATE TABLE `conexion_item_variante` (
  `ID_conexion_item_variante` int(11) NOT NULL,
  `ID_item` int(11) NOT NULL,
  `ID_variante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion`
--

CREATE TABLE `cotizacion` (
  `ID_cotizacion` int(11) NOT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cotizacion`
--

INSERT INTO `cotizacion` (`ID_cotizacion`, `total`) VALUES
(35, 0),
(36, 0),
(37, 250000),
(38, 250000),
(39, 250000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE `item` (
  `ID_item` int(11) NOT NULL,
  `ID_mueble` int(11) NOT NULL,
  `ID_cotizacion` int(11) DEFAULT NULL,
  `precio_unitario` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `item`
--

INSERT INTO `item` (`ID_item`, `ID_mueble`, `ID_cotizacion`, `precio_unitario`, `cantidad`) VALUES
(70, 86, 35, 0, 2),
(71, 86, 36, 0, 5),
(72, 86, 37, 50000, 5),
(73, 86, 38, 50000, 5),
(74, 86, 39, 50000, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mueble`
--

CREATE TABLE `mueble` (
  `ID_mueble` int(11) NOT NULL,
  `nombre_mueble` varchar(30) DEFAULT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  `precio_base` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `tamano` varchar(30) DEFAULT NULL,
  `material` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mueble`
--

INSERT INTO `mueble` (`ID_mueble`, `nombre_mueble`, `tipo`, `precio_base`, `stock`, `estado`, `tamano`, `material`) VALUES
(66, 'Silla de plata', 'Silla', 80000, 5, 1, 'Mediano', 'Plata'),
(86, 'Mesa de vidrio', 'Mesa', 50000, 1, 1, 'Grande', 'Vidrio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `variante`
--

CREATE TABLE `variante` (
  `ID_variante` int(11) NOT NULL,
  `nombre_variante` varchar(30) DEFAULT NULL,
  `descripcion` varchar(90) DEFAULT NULL,
  `precio_extra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `variante`
--

INSERT INTO `variante` (`ID_variante`, `nombre_variante`, `descripcion`, `precio_extra`) VALUES
(21, 'Ruedas', 'Pues tiene ruedas ¿Qué más podría ser?', 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `ID_venta` int(11) NOT NULL,
  `ID_cotizacion` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`ID_venta`, `ID_cotizacion`, `fecha`) VALUES
(5, 35, '2026-01-04 03:58:59'),
(6, 35, '2026-01-04 04:04:42'),
(7, 35, '2026-01-04 04:12:29'),
(8, 39, '2026-01-04 04:13:56');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conexion_item_variante`
--
ALTER TABLE `conexion_item_variante`
  ADD PRIMARY KEY (`ID_conexion_item_variante`),
  ADD KEY `ID_item` (`ID_item`),
  ADD KEY `ID_variante` (`ID_variante`);

--
-- Indices de la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD PRIMARY KEY (`ID_cotizacion`);

--
-- Indices de la tabla `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`ID_item`),
  ADD KEY `ID_mueble` (`ID_mueble`),
  ADD KEY `ID_cotizacion` (`ID_cotizacion`);

--
-- Indices de la tabla `mueble`
--
ALTER TABLE `mueble`
  ADD PRIMARY KEY (`ID_mueble`);

--
-- Indices de la tabla `variante`
--
ALTER TABLE `variante`
  ADD PRIMARY KEY (`ID_variante`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`ID_venta`),
  ADD KEY `ID_cotizacion` (`ID_cotizacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `conexion_item_variante`
--
ALTER TABLE `conexion_item_variante`
  MODIFY `ID_conexion_item_variante` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  MODIFY `ID_cotizacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `item`
--
ALTER TABLE `item`
  MODIFY `ID_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT de la tabla `mueble`
--
ALTER TABLE `mueble`
  MODIFY `ID_mueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de la tabla `variante`
--
ALTER TABLE `variante`
  MODIFY `ID_variante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `ID_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `conexion_item_variante`
--
ALTER TABLE `conexion_item_variante`
  ADD CONSTRAINT `conexion_item_variante_ibfk_1` FOREIGN KEY (`ID_item`) REFERENCES `item` (`ID_item`),
  ADD CONSTRAINT `conexion_item_variante_ibfk_2` FOREIGN KEY (`ID_variante`) REFERENCES `variante` (`ID_variante`);

--
-- Filtros para la tabla `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`ID_mueble`) REFERENCES `mueble` (`ID_mueble`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`ID_cotizacion`) REFERENCES `cotizacion` (`ID_cotizacion`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`ID_cotizacion`) REFERENCES `cotizacion` (`ID_cotizacion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
