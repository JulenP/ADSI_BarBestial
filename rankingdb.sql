-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-01-2019 a las 12:28:09
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `barbestial`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rankingdb`
--

CREATE TABLE `rankingdb` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `emailUsuario` varchar(100) NOT NULL,
  `puntosJug` double NOT NULL,
  `puntosCPU` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rankingdb`
--

INSERT INTO `rankingdb` (`id`, `fecha`, `emailUsuario`, `puntosJug`, `puntosCPU`) VALUES
(1, '2018-12-27', 'a@a.com', 2, 1),
(2, '2018-12-27', 'a@a.com', 3, 2),
(3, '2018-12-27', 'b@b.com', 5, 3),
(4, '2018-12-28', 'c@c.com', 7, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `rankingdb`
--
ALTER TABLE `rankingdb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `rankingdb`
--
ALTER TABLE `rankingdb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
