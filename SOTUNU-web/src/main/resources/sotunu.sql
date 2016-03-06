-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-03-2016 a las 07:54:27
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sotunu`
--
CREATE DATABASE IF NOT EXISTS `sotunu` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sotunu`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cicloacademico`
--

CREATE TABLE `cicloacademico` (
  `idCicloAcademico` int(11) UNSIGNED NOT NULL,
  `año` char(4) NOT NULL,
  `periodo` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cicloacademico`
--

INSERT INTO `cicloacademico` (`idCicloAcademico`, `año`, `periodo`) VALUES
(1, '2015', '1'),
(2, '2015', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo`
--

CREATE TABLE `correo` (
  `idCorreo` int(11) UNSIGNED NOT NULL,
  `idUsuario` int(11) UNSIGNED NOT NULL,
  `email` varchar(50) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `apellido` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuelaprofesional`
--

CREATE TABLE `escuelaprofesional` (
  `idEscuelaProfesional` int(11) UNSIGNED NOT NULL,
  `idFacultad` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `especialidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `escuelaprofesional`
--

INSERT INTO `escuelaprofesional` (`idEscuelaProfesional`, `idFacultad`, `nombre`, `especialidad`) VALUES
(1, 1, 'Ingeniería de sistemas', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facultad`
--

CREATE TABLE `facultad` (
  `idFacultad` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `facultad`
--

INSERT INTO `facultad` (`idFacultad`, `nombre`) VALUES
(1, 'Ingeniería de sistemas e ingeniería civil');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `idPersonal` int(11) UNSIGNED NOT NULL,
  `idEscuelaProfesional` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `cargo` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`idPersonal`, `idEscuelaProfesional`, `nombre`, `apellido`, `direccion`, `email`, `telefono`, `celular`, `cargo`) VALUES
(1, 1, 'Arturo', 'Yupanqui Villanueva', '', NULL, NULL, NULL, 'director');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programacion`
--

CREATE TABLE `programacion` (
  `idProgramacion` int(11) UNSIGNED NOT NULL,
  `idUsuario` int(11) UNSIGNED NOT NULL,
  `idCicloAcademico` int(11) UNSIGNED NOT NULL,
  `idEscuelaProfesional` int(11) UNSIGNED NOT NULL,
  `ciclo` varchar(3) NOT NULL,
  `salon` varchar(3) NOT NULL,
  `pabellon` varchar(3) NOT NULL,
  `nroEstudiantes` int(3) NOT NULL,
  `delegado` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `programacion`
--

INSERT INTO `programacion` (`idProgramacion`, `idUsuario`, `idCicloAcademico`, `idEscuelaProfesional`, `ciclo`, `salon`, `pabellon`, `nroEstudiantes`, `delegado`) VALUES
(1, 1, 1, 1, '5', '12', '2', 13, 'Cante Torres Ramirez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutorado`
--

CREATE TABLE `tutorado` (
  `idTutorado` int(11) UNSIGNED NOT NULL,
  `idEscuelaProfesional` int(11) UNSIGNED NOT NULL,
  `idCicloAcademico` int(11) UNSIGNED NOT NULL COMMENT 'Para medir indicadores',
  `nombre` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `apellido` varchar(200) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `RPM` varchar(20) DEFAULT NULL,
  `telefonoEmergencia` varchar(20) DEFAULT NULL,
  `Email` varchar(60) DEFAULT NULL,
  `caracteristicaParticular` varchar(200) DEFAULT NULL,
  `areasProblema` varchar(300) DEFAULT NULL,
  `observaciones` varchar(400) DEFAULT NULL,
  `estado` char(8) DEFAULT NULL,
  `nivelTutoria` varchar(20) DEFAULT NULL COMMENT 'primeros ciclos, intermedio o tutoria'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutoria`
--

CREATE TABLE `tutoria` (
  `idTutoria` int(11) UNSIGNED NOT NULL,
  `idUsuario` int(11) UNSIGNED NOT NULL,
  `idProgramacion` int(11) UNSIGNED NOT NULL,
  `fecha` date NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `tema` varchar(50) DEFAULT NULL,
  `atencion` char(10) DEFAULT NULL,
  `refDocente` varchar(80) DEFAULT NULL COMMENT 'Referencia del docente que asistio a la tutoria',
  `refAsignatura` varchar(30) DEFAULT NULL,
  `refDato` varchar(30) DEFAULT NULL,
  `notas` varchar(250) DEFAULT NULL,
  `asistencia` varchar(250) DEFAULT NULL,
  `observaciones` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) UNSIGNED NOT NULL,
  `idPersonal` int(11) UNSIGNED DEFAULT NULL,
  `usuario` varchar(40) NOT NULL,
  `clave` varchar(40) NOT NULL,
  `rol` varchar(13) DEFAULT NULL,
  `estado` char(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `idPersonal`, `usuario`, `clave`, `rol`, `estado`) VALUES
(1, 1, 'yupanqui', 'yupanqui', 'director', 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cicloacademico`
--
ALTER TABLE `cicloacademico`
  ADD PRIMARY KEY (`idCicloAcademico`);

--
-- Indices de la tabla `correo`
--
ALTER TABLE `correo`
  ADD PRIMARY KEY (`idCorreo`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `escuelaprofesional`
--
ALTER TABLE `escuelaprofesional`
  ADD PRIMARY KEY (`idEscuelaProfesional`),
  ADD KEY `idFacultad` (`idFacultad`);

--
-- Indices de la tabla `facultad`
--
ALTER TABLE `facultad`
  ADD PRIMARY KEY (`idFacultad`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`idPersonal`),
  ADD KEY `idEscuelaProfesional` (`idEscuelaProfesional`);

--
-- Indices de la tabla `programacion`
--
ALTER TABLE `programacion`
  ADD PRIMARY KEY (`idProgramacion`),
  ADD KEY `idUsuario` (`idUsuario`),
  ADD KEY `idCicloAcademico` (`idCicloAcademico`),
  ADD KEY `idEscuelaProfesional` (`idEscuelaProfesional`);

--
-- Indices de la tabla `tutorado`
--
ALTER TABLE `tutorado`
  ADD PRIMARY KEY (`idTutorado`),
  ADD KEY `idEscuelaProfesional` (`idEscuelaProfesional`),
  ADD KEY `idCicloAcademico` (`idCicloAcademico`);

--
-- Indices de la tabla `tutoria`
--
ALTER TABLE `tutoria`
  ADD PRIMARY KEY (`idTutoria`),
  ADD KEY `idUsuario` (`idUsuario`),
  ADD KEY `idProgramacion` (`idProgramacion`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `idPersonal` (`idPersonal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cicloacademico`
--
ALTER TABLE `cicloacademico`
  MODIFY `idCicloAcademico` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `correo`
--
ALTER TABLE `correo`
  MODIFY `idCorreo` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `escuelaprofesional`
--
ALTER TABLE `escuelaprofesional`
  MODIFY `idEscuelaProfesional` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `facultad`
--
ALTER TABLE `facultad`
  MODIFY `idFacultad` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `idPersonal` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `programacion`
--
ALTER TABLE `programacion`
  MODIFY `idProgramacion` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tutorado`
--
ALTER TABLE `tutorado`
  MODIFY `idTutorado` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tutoria`
--
ALTER TABLE `tutoria`
  MODIFY `idTutoria` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `correo`
--
ALTER TABLE `correo`
  ADD CONSTRAINT `correo_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `escuelaprofesional`
--
ALTER TABLE `escuelaprofesional`
  ADD CONSTRAINT `escuelaprofesional_ibfk_1` FOREIGN KEY (`idFacultad`) REFERENCES `facultad` (`idFacultad`);

--
-- Filtros para la tabla `personal`
--
ALTER TABLE `personal`
  ADD CONSTRAINT `personal_ibfk_1` FOREIGN KEY (`idEscuelaProfesional`) REFERENCES `escuelaprofesional` (`idEscuelaProfesional`);

--
-- Filtros para la tabla `programacion`
--
ALTER TABLE `programacion`
  ADD CONSTRAINT `programacion_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  ADD CONSTRAINT `programacion_ibfk_2` FOREIGN KEY (`idCicloAcademico`) REFERENCES `cicloacademico` (`idCicloAcademico`),
  ADD CONSTRAINT `programacion_ibfk_3` FOREIGN KEY (`idEscuelaProfesional`) REFERENCES `escuelaprofesional` (`idEscuelaProfesional`);

--
-- Filtros para la tabla `tutorado`
--
ALTER TABLE `tutorado`
  ADD CONSTRAINT `tutorado_ibfk_1` FOREIGN KEY (`idEscuelaProfesional`) REFERENCES `escuelaprofesional` (`idEscuelaProfesional`),
  ADD CONSTRAINT `tutorado_ibfk_2` FOREIGN KEY (`idCicloAcademico`) REFERENCES `cicloacademico` (`idCicloAcademico`);

--
-- Filtros para la tabla `tutoria`
--
ALTER TABLE `tutoria`
  ADD CONSTRAINT `tutoria_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  ADD CONSTRAINT `tutoria_ibfk_2` FOREIGN KEY (`idProgramacion`) REFERENCES `programacion` (`idProgramacion`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`idPersonal`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
