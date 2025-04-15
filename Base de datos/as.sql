-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: feriaonlinetpq_db
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `imagenesxpublicacion_tb`
--

DROP TABLE IF EXISTS `imagenesxpublicacion_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagenesxpublicacion_tb` (
  `ID_Imagenes` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Publicacion` int(11) NOT NULL,
  `Imagen` text NOT NULL,
  PRIMARY KEY (`ID_Imagenes`),
  KEY `ID_Publicacion` (`ID_Publicacion`),
  CONSTRAINT `imagenesxpublicacion_tb_ibfk_1` FOREIGN KEY (`ID_Publicacion`) REFERENCES `publicaciones_tb` (`ID_Publicacion`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenesxpublicacion_tb`
--

LOCK TABLES `imagenesxpublicacion_tb` WRITE;
/*!40000 ALTER TABLE `imagenesxpublicacion_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenesxpublicacion_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones_tb`
--

DROP TABLE IF EXISTS `publicaciones_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicaciones_tb` (
  `ID_Publicacion` int(11) NOT NULL AUTO_INCREMENT,
  `NombreProducto` varchar(100) NOT NULL,
  `Descripcion` text,
  `Precio` decimal(10,2) NOT NULL,
  `Estado` enum('Activo','Inactivo') NOT NULL,
  `ID_UsuarioVendedor` int(11) NOT NULL,
  PRIMARY KEY (`ID_Publicacion`),
  KEY `ID_UsuarioVendedor` (`ID_UsuarioVendedor`),
  CONSTRAINT `publicaciones_tb_ibfk_1` FOREIGN KEY (`ID_UsuarioVendedor`) REFERENCES `usuarios_tb` (`ID_Usuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones_tb`
--

LOCK TABLES `publicaciones_tb` WRITE;
/*!40000 ALTER TABLE `publicaciones_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicaciones_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacciones_tb`
--

DROP TABLE IF EXISTS `transacciones_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transacciones_tb` (
  `ID_Orden` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Publicacion` int(11) NOT NULL,
  `ID_UsuarioComprador` int(11) NOT NULL,
  `MetodoDePago` varchar(50) NOT NULL,
  `Fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Estado` enum('Pendiente','Completado','Cancelado') NOT NULL,
  PRIMARY KEY (`ID_Orden`),
  KEY `ID_Publicacion` (`ID_Publicacion`),
  KEY `ID_UsuarioComprador` (`ID_UsuarioComprador`),
  CONSTRAINT `transacciones_tb_ibfk_1` FOREIGN KEY (`ID_Publicacion`) REFERENCES `publicaciones_tb` (`ID_Publicacion`) ON DELETE CASCADE,
  CONSTRAINT `transacciones_tb_ibfk_2` FOREIGN KEY (`ID_UsuarioComprador`) REFERENCES `usuarios_tb` (`ID_Usuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacciones_tb`
--

LOCK TABLES `transacciones_tb` WRITE;
/*!40000 ALTER TABLE `transacciones_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `transacciones_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_tb`
--

DROP TABLE IF EXISTS `usuarios_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_tb` (
  `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT,
  `NombreDeUsuario` varchar(50) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contrasena` varchar(255) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Apellido` varchar(50) DEFAULT NULL,
  `FotoDePerfil` text,
  PRIMARY KEY (`ID_Usuario`),
  UNIQUE KEY `NombreDeUsuario` (`NombreDeUsuario`),
  UNIQUE KEY `Correo` (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_tb`
--

LOCK TABLES `usuarios_tb` WRITE;
/*!40000 ALTER TABLE `usuarios_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15 16:23:43
