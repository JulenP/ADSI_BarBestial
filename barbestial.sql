-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-01-2019 a las 20:27:24
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
-- Estructura de tabla para la tabla `bar`
--

CREATE TABLE `bar` (
  `nombrePartida` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carta`
--

CREATE TABLE `carta` (
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carta`
--

INSERT INTO `carta` (`especie`, `color`) VALUES
('Camaleon', 'Azul'),
('Camaleon', 'Verde'),
('Canguro', 'Azul'),
('Canguro', 'Verde'),
('Cebra', 'Azul'),
('Cebra', 'Verde'),
('Cocodrilo', 'Azul'),
('Cocodrilo', 'Verde'),
('Foca', 'Azul'),
('Foca', 'Verde'),
('Hipopotamo', 'Azul'),
('Hipopotamo', 'Verde'),
('Jirafa', 'Azul'),
('Jirafa', 'Verde'),
('Leon', 'Azul'),
('Leon', 'Verde'),
('Loro', 'Azul'),
('Loro', 'Verde'),
('Mofeta', 'Azul'),
('Mofeta', 'Verde'),
('Mono', 'Azul'),
('Mono', 'Verde'),
('Serpiente', 'Azul'),
('Serpiente', 'Verde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fila`
--

CREATE TABLE `fila` (
  `nombre` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `posicion` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `manojug`
--

CREATE TABLE `manojug` (
  `nombrePartida` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `manoord`
--

CREATE TABLE `manoord` (
  `nombrePartida` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mazojug`
--

CREATE TABLE `mazojug` (
  `nombrePartida` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `posicion` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mazoord`
--

CREATE TABLE `mazoord` (
  `nombrePartida` varchar(255) NOT NULL,
  `especie` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `posicion` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `nombrePartida` varchar(255) NOT NULL,
  `fechaHora` varchar(255) NOT NULL,
  `emailUsuario` varchar(255) NOT NULL,
  `ayudasUsadas` int(10) NOT NULL,
  `nombrePersonalizacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personalizacion`
--

CREATE TABLE `personalizacion` (
  `emailUsuario` varchar(255) NOT NULL,
  `nombrePersonalizacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE `ranking` (
  `id` int(11) NOT NULL,
  `fecha` text NOT NULL,
  `emailUsuario` varchar(100) NOT NULL,
  `puntosJug` double NOT NULL,
  `puntosCPU` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `ranking`
--

INSERT INTO `ranking` (`id`, `fecha`, `emailUsuario`, `puntosJug`, `puntosCPU`) VALUES
(1, '2018-12-27', 'a@a.com', 2, 1),
(2, '2018-12-27', 'a@a.com', 3, 2),
(3, '2018-12-27', 'b@b.com', 5, 3),
(4, '2018-12-28', 'c@c.com', 7, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `puntosAyuda` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`email`, `nombre`, `clave`, `puntosAyuda`) VALUES
('a@a.com', 'a', '1234', 0),
('edurne@gmail.com', 'edurne', 'edurne', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bar`
--
ALTER TABLE `bar`
  ADD PRIMARY KEY (`nombrePartida`,`especie`,`color`);

--
-- Indices de la tabla `carta`
--
ALTER TABLE `carta`
  ADD PRIMARY KEY (`especie`,`color`);

--
-- Indices de la tabla `fila`
--
ALTER TABLE `fila`
  ADD PRIMARY KEY (`nombre`,`especie`,`color`);

--
-- Indices de la tabla `manojug`
--
ALTER TABLE `manojug`
  ADD PRIMARY KEY (`nombrePartida`,`especie`,`color`);

--
-- Indices de la tabla `manoord`
--
ALTER TABLE `manoord`
  ADD PRIMARY KEY (`nombrePartida`,`especie`,`color`);

--
-- Indices de la tabla `mazojug`
--
ALTER TABLE `mazojug`
  ADD PRIMARY KEY (`nombrePartida`,`especie`,`color`);

--
-- Indices de la tabla `mazoord`
--
ALTER TABLE `mazoord`
  ADD PRIMARY KEY (`nombrePartida`,`especie`,`color`);

--
-- Indices de la tabla `partida`
--
ALTER TABLE `partida`
  ADD PRIMARY KEY (`nombrePartida`);

--
-- Indices de la tabla `personalizacion`
--
ALTER TABLE `personalizacion`
  ADD PRIMARY KEY (`emailUsuario`,`nombrePersonalizacion`);

--
-- Indices de la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ranking`
--
ALTER TABLE `ranking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
