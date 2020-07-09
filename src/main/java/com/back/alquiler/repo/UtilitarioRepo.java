package com.back.alquiler.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.back.alquiler.models.Modulo;


public interface UtilitarioRepo extends JpaRepository<Modulo, Integer>{


	@Query(value="insert into perfiles_modulo (id_perfil, id_modulo) VALUES (1, 1),(1, 2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(3,9),(3,10),(3,11),(1,12);", nativeQuery = true)
	void insertarPerfilesModulo();
	
	@Query(value="insert into tipo_zona(id_tipo_zona, descripcion) VALUES (1, 'A.H. ASENTAMIENTO HUMANO'),"
			+ "(2, 'C.H. CONJUNTO HABITACIONAL'),"
			+ "(3, 'CAS. CASERÍO'),"
			+ "(4, 'COO. COOPERATIVA'),"
			+ "(5, 'FND. FUNDO'),"
			+ "(6, 'GRU. GRUPO'),"
			+ "(7, 'P.J. PUEBLO JOVEN'),"
			+ "(8, 'RES. RESIDENCIAL'),"
			+ "(9, 'U.V. UNIDAD VECINAL'),"
			+ "(10, 'URB. URBANIZACIÓN'),"
			+ "(11, 'Z.I. ZONA INDUSTRIAL'),"
			+ "(12, 'OTROS');", nativeQuery = true)
	void insertarTipoZona();
	
	
	@Query(value="insert into banco(id_banco, descripcion) VALUES (1, 'BANCO DE COMERCIO'),"
			+ "(2, 'BANCO DE CREDITO DEL PERU'),"
			+ "(3, 'BANCO PICHINCHA'),"
			+ "(4, 'BBVA CONTINENTAL'),"
			+ "(5, 'CITIBANK'),"
			+ "(6, 'INTERBANK'),"
			+ "(7, 'MIBANCO');", nativeQuery = true)
	void insertarBanco();
	
	
	@Query(value="insert into departamento(id_departamento, descripcion) VALUES (1, 'AMAZONAS'),"
			+ "(2, 'ANCASH'),"
			+ "(3, 'APURIMAC'),"
			+ "(4, 'AREQUIPA'),"
			+ "(5, 'AYACUCHO'),"
			+ "(6, 'CAJAMARCA'),"
			+ "(7, 'CUSCO'),"
			+ "(8, 'HUANCAVELICA'),"
			+ "(9, 'HUANUCO'),"
			+ "(10, 'ICA'),"
			+ "(11, 'JUNIN'),"
			+ "(12, 'LA LIBERTAD'),"
			+ "(13, 'LAMBAYEQUE'),"
			+ "(14, 'LIMA'),"
			+ "(15, 'LORETO'),"
			+ "(16, 'MADRE DE DIOS'),"
			+ "(17, 'MOQUEGUA'),"
			+ "(18, 'PASCO'),"
			+ "(19, 'PIURA'),"
			+ "(20, 'PUNO'),"
			+ "(21, 'SAN MARTIN'),"
			+ "(22, 'TACNA'),"
			+ "(23, 'TUMBES'),"
			+ "(24, 'UCAYALI'),"
			+ "(25, 'PROV. CONST. DEL CALLAO');", nativeQuery = true)
	void insertarDepartamento();
	
	
	@Query(value="insert into provincia(id_provincia, descripcion, id_departamento) VALUES (1, 'BAGUA', 1),"
			+ "(2, 'BONGARA', 1),"
			+ "(3, 'CHACHAPOYAS', 1),"
			+ "(4, 'CONDORCANQUI', 1),"
			+ "(5, 'LUYA', 1),"
			+ "(6, 'RODRIGUEZ DE MENDOZA', 1),"
			+ "(7, 'UTCUBAMBA', 1),"
			+ "(8, 'AIJA', 2),"
			+ "(9, 'ANTONIO RAIMONDI', 2),"
			+ "(10, 'ASUNCION', 2),"
			+ "(11, 'BOLOGNESI', 2),"
			+ "(12, 'CARHUAZ', 2),"
			+ "(13, 'CARLOS FERMIN FITZCARRALD', 2),"
			+ "(14, 'CASMA', 2),"
			+ "(15, 'CORONGO', 2),"
			+ "(16, 'HUARAZ', 2),"
			+ "(17, 'HUARI', 2),"
			+ "(18, 'HUARMEY', 2),"
			+ "(19, 'HUAYLAS', 2),"
			+ "(20, 'MARISCAL LUZURIAGA', 2),"
			+ "(21, 'OCROS', 2),"
			+ "(22, 'PALLASCA', 2),"
			+ "(23, 'POMABAMBA', 2),"
			+ "(24, 'RECUAY', 2),"
			+ "(25, 'SANTA', 2),"
			+ "	(26, 'SIHUAS', 2),"
			+ "(27, 'YUNGAY', 2),"
			+ "(28, 'ABANCAY', 3),"
			+ "(29, 'ANDAHUAYLAS', 3),"
			+ "(30, 'ANTABAMBA', 3),"
			+ "(31, 'AYMARAES', 3),"
			+ "(32, 'CHINCHEROS', 3),"
			+ "(33, 'COTABAMBAS', 3),"
			+ "(34, 'GRAU', 3),"
			+ "(35, 'AREQUIPA', 4),"
			+ "(36, 'CAMANA', 4),"
			+ "(37, 'CARAVELI', 4),"
			+ "(38, 'CASTILLA', 4),"
			+ "(39, 'CAYLLOMA', 4),"
			+ "(40, 'CONDESUYOS', 4),"
			+ "(41, 'ISLAY', 4),"
			+ "(42, 'LA UNION', 4),"
			+ "(43, 'CANGALLO', 5),"
			+ "(44, 'HUAMANGA', 5),"
			+ "(45, 'HUANCA SANCOS', 5),"
			+ "(46, 'HUANTA', 5),"
			+ "(47, 'LA MAR', 5),"
			+ "(48, 'LUCANAS', 5),"
			+ "(49, 'PARINACOCHAS', 5),"
			+ "(50, 'PAUCAR DEL SARA SARA', 5),"
			+ "(51, 'SUCRE', 5),"
			+ "(52, 'VICTOR FAJARDO', 5),"
			+ "(53, 'VILCAS HUAMAN', 5),"
			+ "(54, 'CAJABAMBA', 6),"
			+ "(55, 'CAJAMARCA', 6),"
			+ "(56, 'CELENDIN', 6),"
			+ "(57, 'CHOTA', 6),"
			+ "(58, 'CONTUMAZA', 6),"
			+ "(59, 'CUTERVO', 6),"
			+ "(60, 'HUALGAYOC', 6),"
			+ "(61, 'JAEN', 6),"
			+ "(62, 'SAN IGNACIO', 6),"
			+ "(63, 'SAN MARCOS', 6),"
			+ "(64, 'SAN MIGUEL', 6),"
			+ "(65, 'SAN PABLO', 6),"
			+ "(66, 'SANTA CRUZ', 6),"
			+ "(67, 'ACOMAYO', 7),"
			+ "(68, 'ANTA', 7),"
			+ "(69, 'CALCA', 7),"
			+ "(70, 'CANAS', 7),"
			+ "(71, 'CANCHIS', 7),"
			+ "(72, 'CHUMBIVILCAS', 7),"
			+ "(73, 'CUSCO', 7),"
			+ "(74, 'ESPINAR', 7),"
			+ "(75, 'LA CONVENCION', 7),"
			+ "(76, 'PARURO', 7),"
			+ "(77, 'PAUCARTAMBO', 7),"
			+ "(78, 'QUISPICANCHIS', 7),"
			+ "(79, 'URUBAMBA', 7),"
			+ "(80, 'ACOBAMBA', 8),"
			+ "(81, 'ANGARAES', 8),"
			+ "(82, 'CASTROVIRREYNA', 8),"
			+ "(83, 'CHURCAMPA', 8),"
			+ "(84, 'HUANCAVELICA', 8),"
			+ "(85, 'HUAYTARA', 8),"
			+ "(86, 'TAYACAJA', 8),"
			+ "(87, 'AMBO', 9),"
			+ "(88, 'DOS DE MAYO', 9),"
			+ "(89, 'HUACAYBAMBA', 9),"
			+ "(90, 'HUAMALIES', 9),"
			+ "(91, 'HUANUCO', 9),"
			+ "(92, 'LAURICOCHA', 9),"
			+ "(93, 'LEONCIO PRADO', 9),"
			+ "(94, 'MARANON', 9),"
			+ "(95, 'PACHITEA', 9),"
			+ "(96, 'PUERTO INCA', 9),"
			+ "(97, 'YAROWILCA', 9),"
			+ "(98, 'CHINCHA', 10),"
			+ "(99, 'ICA', 10),"
			+ "(100, 'NAZCA', 10),"
			+ "(101, 'PALPA', 10),"
			+ "(102, 'PISCO', 10),"
			+ "(103, 'CHANCHAMAYO', 11),"
			+ "(104, 'CHUPACA', 11),"
			+ "(105, 'CONCEPCION', 11),"
			+ "(106, 'HUANCAYO', 11),"
			+ "(107, 'JAUJA', 11),"
			+ "(108, 'JUNIN', 11),"
			+ "(109, 'SATIPO', 11),"
			+ "(110, 'TARMA', 11),"
			+ "(111, 'YAULI', 11),"
			+ "(112, 'ASCOPE', 12),"
			+ "(113, 'BOLIVAR', 12),"
			+ "(114, 'CHEPEN', 12),"
			+ "(115, 'GRAN CHIMU', 12),"
			+ "(116, 'JULCAN', 12),"
			+ "(117, 'OTUZCO', 12),"
			+ "(118, 'PACASMAYO', 12),"
			+ "(119, 'PATAZ', 12),"
			+ "(120, 'SANCHEZ CARRION', 12),"
			+ "(121, 'SANTIAGO DE CHUCO', 12),"
			+ "(122, 'TRUJILLO', 12),"
			+ "(123, 'VIRU', 12),"
			+ "(124, 'CHICLAYO', 13),"
			+ "(125, 'FERRENAFE', 13),"
			+ "(126, 'LAMBAYEQUE', 13),"
			+ "(127, 'BARRANCA', 14),"
			+ "(128, 'CAJATAMBO', 14),"
			+ "(129, 'CANETE', 14),"
			+ "(130, 'CANTA', 14),"
			+ "(131, 'HUARAL', 14),"
			+ "(132, 'HUAROCHIRI', 14),"
			+ "(133, 'HUAURA', 14),"
			+ "(134, 'LIMA', 14),"
			+ "(135, 'OYON', 14),"
			+ "(136, 'YAUYOS', 14),"
			+ "(137, 'ALTO AMAZONAS', 15),"
			+ "(138, 'DATEM DEL MARAÑON', 15),"
			+ "(139, 'LORETO', 15),"
			+ "(140, 'MARISCAL RAMON CASTILLA', 15),"
			+ "(141, 'MAYNAS', 15),"
			+ "(142, 'REQUENA', 15),"
			+ "(143, 'UCAYALI', 15),"
			+ "(144, 'MANU', 16),"
			+ "(145, 'TAHUAMANU', 16),"
			+ "(146, 'TAMBOPATA', 16),"
			+ "(147, 'GENERAL SANCHEZ CERRO', 17),"
			+ "(148, 'ILO', 17),"
			+ "(149, 'MARISCAL NIETO', 17),"
			+ "(150, 'DANIEL ALCIDES CARRION', 18),"
			+ "(151, 'OXAPAMPA', 18),"
			+ "(152, 'PASCO', 18),"
			+ "(153, 'AYABACA', 19),"
			+ "(154, 'HUANCABAMBA', 19),"
			+ "(155, 'MORROPON', 19),"
			+ "(156, 'PAITA', 19),"
			+ "(157, 'PIURA', 19),"
			+ "(158, 'SECHURA', 19),"
			+ "(159, 'SULLANA', 19),"
			+ "(160, 'TALARA', 19),"
			+ "(161, 'AZANGARO', 20),"
			+ "(162, 'CARABAYA', 20),"
			+ "(163, 'CHUCUITO', 20),"
			+ "(164, 'EL COLLAO', 20),"
			+ "(165, 'HUANCANE', 20),"
			+ "(166, 'LAMPA', 20),"
			+ "(167, 'MELGAR', 20),"
			+ "(168, 'MOHO', 20),"
			+ "(169, 'PUNO', 20),"
			+ "(170, 'SAN ANTONIO DE PUTINA', 20),"
			+ "(171, 'SAN ROMAN', 20),"
			+ "(172, 'SANDIA', 20),"
			+ "(173, 'YUNGUYO', 20),"
			+ "(174, 'BELLAVISTA', 21),"
			+ "(175, 'EL DORADO', 21),"
			+ "(176, 'HUALLAGA', 21),"
			+ "(177, 'LAMAS', 21),"
			+ "(178, 'MARISCAL CACERES', 21),"
			+ "(179, 'MOYOBAMBA', 21),"
			+ "(180, 'PICOTA', 21),"
			+ "(181, 'RIOJA', 21),"
			+ "(182, 'SAN MARTIN', 21),"
			+ "(183, 'TOCACHE', 21),"
			+ "(184, 'CANDARAVE', 22),"
			+ "(185, 'JORGE BASADRE', 22),"
			+ "(186, 'TACNA', 22),"
			+ "(187, 'TARATA', 22),"
			+ "(188, 'CONTRALMIRANTE VILLAR', 23),"
			+ "(189, 'TUMBES', 23),"
			+ "(190, 'ZARUMILLA', 23),"
			+ "(191, 'ATALAYA', 24),"
			+ "(192, 'CORONEL PORTILLO', 24),"
			+ "(193, 'PADRE ABAD', 24),"
			+ "(194, 'PURUS', 24),"
			+ "(195, 'CALLAO', 25);", nativeQuery = true)
	void insertarProvincia();
	
	
	@Query(value="insert into distrito(id_distrito, descripcion, id_provincia) VALUES (1,'ARAMANGO',1),"
			+"(2,'BAGUA',1),\r\n" + 
			"(3,'COPALLIN',1),\r\n" + 
			"(4,'EL PARCO',1),\r\n" + 
			"(5,'IMAZA',1),\r\n" + 
			"(6,'LA PECA',1),\r\n" + 
			"(7,'CHISQUILLA',2),\r\n" + 
			"(8,'CHURUJA',2),\r\n" + 
			"(9,'COROSHA',2),\r\n" + 
			"(10,'CUISPES',2),\r\n" + 
			"(11,'FLORIDA',2),\r\n" + 
			"(12,'JAZAN',2),\r\n" + 
			"(13,'JUMBILLA',2),\r\n" + 
			"(14,'RECTA',2),\r\n" + 
			"(15,'SAN CARLOS',2),\r\n" + 
			"(16,'SHIPASBAMBA',2),\r\n" + 
			"(17,'VALERA',2),\r\n" + 
			"(18,'YAMBRASBAMBA',2),\r\n" + 
			"(19,'ASUNCION',3),\r\n" + 
			"(20,'BALSAS',3),\r\n" + 
			"(21,'CHACHAPOYAS',3),\r\n" + 
			"(22,'CHETO',3),\r\n" + 
			"(23,'CHILIQUIN',3),\r\n" + 
			"(24,'CHUQUIBAMBA',3),\r\n" + 
			"(25,'GRANADA',3),\r\n" + 
			"(26,'HUANCAS',3),\r\n" + 
			"(27,'LA JALCA',3),\r\n" + 
			"(28,'LEIMEBAMBA',3),\r\n" + 
			"(29,'LEVANTO',3),\r\n" + 
			"(30,'MAGDALENA',3),\r\n" + 
			"(31,'MARISCAL CASTILLA',3),\r\n" + 
			"(32,'MOLINOPAMPA',3),\r\n" + 
			"(33,'MONTEVIDEO',3),\r\n" + 
			"(34,'OLLEROS',3),\r\n" + 
			"(35,'QUINJALCA',3),\r\n" + 
			"(36,'SAN FCO DE DAGUAS',3),\r\n" + 
			"(37,'SAN ISIDRO DE MAINO',3),\r\n" + 
			"(38,'SAN ISIDRO DE MAINO',3),\r\n" + 
			"(39,'SOLOCO',3),\r\n" + 
			"(40,'SONCHE',3),\r\n" + 
			"(41,'EL CENEPA',4),\r\n" + 
			"(42,'NIEVA',4),\r\n" + 
			"(43,'RIO SANTIAGO',4),\r\n" + 
			"(44,'CAMPORREDONDO',5),\r\n" + 
			"(45,'COCABAMBA',5),\r\n" + 
			"(46,'COLCAMAR',5),\r\n" + 
			"(47,'CONILA',5),\r\n" + 
			"(48,'INGUILPATA',5),\r\n" + 
			"(49,'LAMUD',5),\r\n" + 
			"(50,'LONGUITA',5),\r\n" + 
			"(51,'LONYA CHICO',5),\r\n" + 
			"(52,'LUYA',5),\r\n" + 
			"(53,'LUYA VIEJO',5),\r\n" + 
			"(54,'MARIA',5),\r\n" + 
			"(55,'OCALLI',5),\r\n" + 
			"(56,'OCUMAL',5),\r\n" + 
			"(57,'PISUQUIA',5),\r\n" + 
			"(58,'PROVIDENCIA',5),\r\n" + 
			"(59,'SAN CRISTOBAL',5),\r\n" + 
			"(60,'SAN FRANCISCO DE YESO',5),\r\n" + 
			"(61,'SAN JERONIMO',5),\r\n" + 
			"(62,'SAN JUAN DE LOPECANCHA',5),\r\n" + 
			"(63,'SANTA CATALINA',5),\r\n" + 
			"(64,'SANTO TOMAS',5),\r\n" + 
			"(65,'TINGO',5),\r\n" + 
			"(66,'TRITA',5),\r\n" + 
			"(67,'CHIRIMOTO',6),\r\n" + 
			"(68,'COCHAMAL',6),\r\n" + 
			"(69,'HUAMBO',6),\r\n" + 
			"(70,'LIMABAMBA',6),\r\n" + 
			"(71,'LONGAR',6),\r\n" + 
			"(72,'MCAL BENAVIDES',6),\r\n" + 
			"(73,'MILPUC',6),\r\n" + 
			"(74,'OMIA',6),\r\n" + 
			"(75,'SAN NICOLAS',6),\r\n" + 
			"(76,'SANTA ROSA',6),\r\n" + 
			"(77,'TOTORA',6),\r\n" + 
			"(78,'VISTA ALEGRE',6),\r\n" + 
			"(79,'BAGUA GRANDE',7),\r\n" + 
			"(80,'CAJARURO',7),\r\n" + 
			"(81,'CUMBA',7),\r\n" + 
			"(82,'EL MILAGRO',7),\r\n" + 
			"(83,'JAMALCA',7),\r\n" + 
			"(84,'LONYA GRANDE',7),\r\n" + 
			"(85,'YAMON',7),\r\n" + 
			"(86,'AIJA',8),\r\n" + 
			"(87,'CORIS',8),\r\n" + 
			"(88,'HUACLLAN',8),\r\n" + 
			"(89,'LA MERCED',8),\r\n" + 
			"(90,'SUCCHA',8),\r\n" + 
			"(91,'ACZO',9),\r\n" + 
			"(92,'CHACCHO',9),\r\n" + 
			"(93,'CHINGAS',9),\r\n" + 
			"(94,'LLAMELLIN',9),\r\n" + 
			"(95,'MIRGAS',9),\r\n" + 
			"(96,'SAN JUAN DE RONTOY',9),\r\n" + 
			"(97,'ACOCHACA',10),\r\n" + 
			"(98,'CHACAS',10),\r\n" + 
			"(99,'A PARDO LEZAMETA',11),\r\n" + 
			"(100,'ANTONIO RAIMONDI',11),\r\n" + 
			"(101,'AQUIA',11),\r\n" + 
			"(102,'CAJACAY',11),\r\n" + 
			"(103,'CANIS',11),\r\n" + 
			"(104,'CHIQUIAN',11),\r\n" + 
			"(105,'COLQUIOC',11),\r\n" + 
			"(106,'HUALLANCA',11),\r\n" + 
			"(107,'HUASTA',11),\r\n" + 
			"(108,'HUAYLLACAYAN',11),\r\n" + 
			"(109,'LA PRIMAVERA',11),\r\n" + 
			"(110,'MANGAS',11),\r\n" + 
			"(111,'PACLLON',11),\r\n" + 
			"(112,'SAN MIGUEL DE CORPANQUI',11),\r\n" + 
			"(113,'TICLLOS',11),\r\n" + 
			"(114,'ACOPAMPA',12),\r\n" + 
			"(115,'AMASHCA',12),\r\n" + 
			"(116,'ANTA',12),\r\n" + 
			"(117,'ATAQUERO',12),\r\n" + 
			"(118,'CARHUAZ',12),\r\n" + 
			"(119,'MARCARA',12),\r\n" + 
			"(120,'PARIAHUANCA',12),\r\n" + 
			"(121,'SAN MIGUEL DE ACO',12),\r\n" + 
			"(122,'SHILLA',12),\r\n" + 
			"(123,'TINCO',12),\r\n" + 
			"(124,'YUNGAR',12),\r\n" + 
			"(125,'SAN LUIS',13),\r\n" + 
			"(126,'SAN NICOLAS',13),\r\n" + 
			"(127,'YAUYA',13),\r\n" + 
			"(128,'BUENA VISTA ALTA',14),\r\n" + 
			"(129,'CASMA',14),\r\n" + 
			"(130,'COMANDANTE NOEL',14),\r\n" + 
			"(131,'YAUTAN',14),\r\n" + 
			"(132,'ACO',15),\r\n" + 
			"(133,'BAMBAS',15),\r\n" + 
			"(134,'CORONGO',15),\r\n" + 
			"(135,'CUSCA',15),\r\n" + 
			"(136,'LA PAMPA',15),\r\n" + 
			"(137,'YANAC',15),\r\n" + 
			"(138,'YUPAN',15),\r\n" + 
			"(139,'COCHABAMBA',16),\r\n" + 
			"(140,'COLCABAMBA',16),\r\n" + 
			"(141,'HUANCHAY',16),\r\n" + 
			"(142,'HUARAZ',16),\r\n" + 
			"(143,'INDEPENDENCIA',16),\r\n" + 
			"(144,'JANGAS',16),\r\n" + 
			"(145,'LA LIBERTAD',16),\r\n" + 
			"(146,'OLLEROS',16),\r\n" + 
			"(147,'PAMPAS',16),\r\n" + 
			"(148,'PARIACOTO',16),\r\n" + 
			"(149,'PIRA',16),\r\n" + 
			"(150,'TARICA',16),\r\n" + 
			"(151,'ANRA',17),\r\n" + 
			"(152,'CAJAY',17),\r\n" + 
			"(153,'CHAVIN DE HUANTAR',17),\r\n" + 
			"(154,'HUACACHI',17),\r\n" + 
			"(155,'HUACCHIS',17),\r\n" + 
			"(156,'HUACHIS',17),\r\n" + 
			"(157,'HUANTAR',17),\r\n" + 
			"(158,'HUARI',17),\r\n" + 
			"(159,'MASIN',17),\r\n" + 
			"(160,'PAUCAS',17),\r\n" + 
			"(161,'PONTO',17),\r\n" + 
			"(162,'RAHUAPAMPA',17),\r\n" + 
			"(163,'RAPAYAN',17),\r\n" + 
			"(164,'SAN MARCOS',17),\r\n" + 
			"(165,'SAN PEDRO DE CHANA',17),\r\n" + 
			"(166,'UCO',17),\r\n" + 
			"(167,'COCHAPETI',18),\r\n" + 
			"(168,'CULEBRAS',18),\r\n" + 
			"(169,'HUARMEY',18),\r\n" + 
			"(170,'HUAYAN',18),\r\n" + 
			"(171,'MALVAS',18),\r\n" + 
			"(172,'CARAZ',19),\r\n" + 
			"(173,'HUALLANCA',19),\r\n" + 
			"(174,'HUATA',19),\r\n" + 
			"(175,'HUAYLAS',19),\r\n" + 
			"(176,'MATO',19),\r\n" + 
			"(177,'PAMPAROMAS',19),\r\n" + 
			"(178,'PUEBLO LIBRE',19),\r\n" + 
			"(179,'SANTA CRUZ',19),\r\n" + 
			"(180,'SANTO TORIBIO',19),\r\n" + 
			"(181,'YURACMARCA',19),\r\n" + 
			"(182,'CASCA',20),\r\n" + 
			"(183,'ELEAZAR GUZMAN BARRON',20),\r\n" + 
			"(184,'FIDEL OLIVAS ESCUDERO',20),\r\n" + 
			"(185,'LLAMA',20),\r\n" + 
			"(186,'LLUMPA',20),\r\n" + 
			"(187,'LUCMA',20),\r\n" + 
			"(188,'MUSGA',20),\r\n" + 
			"(189,'PISCOBAMBA',20),\r\n" + 
			"(190,'ACAS',21),\r\n" + 
			"(191,'CAJAMARQUILLA',21),\r\n" + 
			"(192,'CARHUAPAMPA',21),\r\n" + 
			"(193,'COCHAS',21),\r\n" + 
			"(194,'CONGAS',21),\r\n" + 
			"(195,'LLIPA',21),\r\n" + 
			"(196,'OCROS',21),\r\n" + 
			"(197,'SAN CRISTOBAL DE RAJAN',21),\r\n" + 
			"(198,'SAN PEDRO',21),\r\n" + 
			"(199,'SANTIAGO DE CHILCAS',21),\r\n" + 
			"(200,'BOLOGNESI',22),\r\n" + 
			"(201,'CABANA',22),\r\n" + 
			"(202,'CONCHUCOS',22),\r\n" + 
			"(203,'HUACASCHUQUE',22),\r\n" + 
			"(204,'HUANDOVAL',22),\r\n" + 
			"(205,'LACABAMBA',22),\r\n" + 
			"(206,'LLAPO',22),\r\n" + 
			"(207,'PALLASCA',22),\r\n" + 
			"(208,'PAMPAS',22),\r\n" + 
			"(209,'SANTA ROSA',22),\r\n" + 
			"(210,'TAUCA',22),\r\n" + 
			"(211,'HUAYLLAN',23),\r\n" + 
			"(212,'PAROBAMBA',23),\r\n" + 
			"(213,'POMABAMBA',23),\r\n" + 
			"(214,'QUINUABAMBA',23),\r\n" + 
			"(215,'CATAC',24),\r\n" + 
			"(216,'COTAPARACO',24),\r\n" + 
			"(217,'HUAYLLAPAMPA',24),\r\n" + 
			"(218,'LLACLLIN',24),\r\n" + 
			"(219,'MARCA',24),\r\n" + 
			"(220,'PAMPAS CHICO',24),\r\n" + 
			"(221,'PARARIN',24),\r\n" + 
			"(222,'RECUAY',24),\r\n" + 
			"(223,'TAPACOCHA',24),\r\n" + 
			"(224,'TICAPAMPA',24),\r\n" + 
			"(225,'CACERES DEL PERU',25),\r\n" + 
			"(226,'CHIMBOTE',25),\r\n" + 
			"(227,'COISHCO',25),\r\n" + 
			"(228,'MACATE',25),\r\n" + 
			"(229,'MORO',25),\r\n" + 
			"(230,'NEPENA',25),\r\n" + 
			"(231,'NUEVO CHIMBOTE',25),\r\n" + 
			"(232,'SAMANCO',25),\r\n" + 
			"(233,'SANTA',25),\r\n" + 
			"(234,'ACOBAMBA',26),\r\n" + 
			"(235,'ALFONSO UGARTE',26),\r\n" + 
			"(236,'CASHAPAMPA',26),\r\n" + 
			"(237,'CHINGALPO',26),\r\n" + 
			"(238,'HUAYLLABAMBA',26),\r\n" + 
			"(239,'QUICHES',26),\r\n" + 
			"(240,'RAGASH',26),\r\n" + 
			"(241,'SAN JUAN',26),\r\n" + 
			"(242,'SICSIBAMBA',26),\r\n" + 
			"(243,'SIHUAS',26),\r\n" + 
			"(244,'CASCAPARA',27),\r\n" + 
			"(245,'MANCOS',27),\r\n" + 
			"(246,'MATACOTO',27),\r\n" + 
			"(247,'QUILLO',27),\r\n" + 
			"(248,'RANRAHIRCA',27),\r\n" + 
			"(249,'SHUPLUY',27),\r\n" + 
			"(250,'YANAMA',27),\r\n" + 
			"(251,'YUNGAY',27),\r\n" + 
			"(252,'ABANCAY',28),\r\n" + 
			"(253,'CHACOCHE',28),\r\n" + 
			"(254,'CIRCA',28),\r\n" + 
			"(255,'CURAHUASI',28),\r\n" + 
			"(256,'HUANIPACA',28),\r\n" + 
			"(257,'LAMBRAMA',28),\r\n" + 
			"(258,'PICHIRHUA',28),\r\n" + 
			"(259,'SAN PEDRO DE CACHORA',28),\r\n" + 
			"(260,'TAMBURCO',28),\r\n" + 
			"(261,'ANDAHUAYLAS',29),\r\n" + 
			"(262,'ANDARAPA',29),\r\n" + 
			"(263,'CHIARA',29),\r\n" + 
			"(264,'HUANCARAMA',29),\r\n" + 
			"(265,'HUANCARAY',29),\r\n" + 
			"(266,'HUAYANA',29),\r\n" + 
			"(267,'KAQUIABAMBA',29),\r\n" + 
			"(268,'KISHUARA',29),\r\n" + 
			"(269,'PACOBAMBA',29),\r\n" + 
			"(270,'PACUCHA',29),\r\n" + 
			"(271,'PAMPACHIRI',29),\r\n" + 
			"(272,'POMACOCHA',29),\r\n" + 
			"(273,'SAN ANTONIO DE CACHI',29),\r\n" + 
			"(274,'SAN JERONIMO',29),\r\n" + 
			"(275,'SAN MIGUEL DE CHACCRAMPA',29),\r\n" + 
			"(276,'STA MARIA DE CHICMO',29),\r\n" + 
			"(277,'TALAVERA',29),\r\n" + 
			"(278,'TUMAY HUARACA',29),\r\n" + 
			"(279,'TURPO',29),\r\n" + 
			"(280,'ANTABAMBA',30),\r\n" + 
			"(281,'EL ORO',30),\r\n" + 
			"(282,'HUAQUIRCA',30),\r\n" + 
			"(283,'JUAN ESPINOZA MEDRANO',30),\r\n" + 
			"(284,'OROPESA',30),\r\n" + 
			"(285,'PACHACONAS',30),\r\n" + 
			"(286,'SABAINO',30),\r\n" + 
			"(287,'CAPAYA',31),\r\n" + 
			"(288,'CARAYBAMBA',31),\r\n" + 
			"(289,'CHALHUANCA',31),\r\n" + 
			"(290,'CHAPIMARCA',31),\r\n" + 
			"(291,'COLCABAMBA',31),\r\n" + 
			"(292,'COTARUSE',31),\r\n" + 
			"(293,'IHUAYLLO',31),\r\n" + 
			"(294,'JUSTO APU SAHUARAURA',31),\r\n" + 
			"(295,'LUCRE',31),\r\n" + 
			"(296,'POCOHUANCA',31),\r\n" + 
			"(297,'SAN JUAN DE CHACNA',31),\r\n" + 
			"(298,'SANAICA',31),\r\n" + 
			"(299,'SORAYA',31),\r\n" + 
			"(300,'TAPAIRIHUA',31),\r\n" + 
			"(301,'TINTAY',31),\r\n" + 
			"(302,'TORAYA',31),\r\n" + 
			"(303,'YANACA',31),\r\n" + 
			"(304,'ANCO HUALLO',32),\r\n" + 
			"(305,'CHINCHEROS',32),\r\n" + 
			"(306,'COCHARCAS',32),\r\n" + 
			"(307,'HUACCANA',32),\r\n" + 
			"(308,'OCOBAMBA',32),\r\n" + 
			"(309,'ONGOY',32),\r\n" + 
			"(310,'RANRACANCHA',32),\r\n" + 
			"(311,'URANMARCA',32),\r\n" + 
			"(312,'CHALLHUAHUACHO',33),\r\n" + 
			"(313,'COTABAMBAS',33),\r\n" + 
			"(314,'COYLLURQUI',33),\r\n" + 
			"(315,'HAQUIRA',33),\r\n" + 
			"(316,'HAQUIRA',33),\r\n" + 
			"(317,'MARA',33),\r\n" + 
			"(318,'TAMBOBAMBA',33),\r\n" + 
			"(319,'CHUQUIBAMBILLA',34),\r\n" + 
			"(320,'CURASCO',34),\r\n" + 
			"(321,'CURPAHUASI',34),\r\n" + 
			"(322,'HUAILLATI',34),\r\n" + 
			"(323,'MAMARA',34),\r\n" + 
			"(324,'MARISCAL GAMARRA',34),\r\n" + 
			"(325,'MICAELA BASTIDAS',34),\r\n" + 
			"(326,'PATAYPAMPA',34),\r\n" + 
			"(327,'PROGRESO',34),\r\n" + 
			"(328,'SAN ANTONIO',34),\r\n" + 
			"(329,'SANTA ROSA',34),\r\n" + 
			"(330,'TURPAY',34),\r\n" + 
			"(331,'VILCABAMBA',34),\r\n" + 
			"(332,'VIRUNDO',34),\r\n" + 
			"(333,'ALTO SELVA ALEGRE',35),\r\n" + 
			"(334,'AREQUIPA',35),\r\n" + 
			"(335,'CAYMA',35),\r\n" + 
			"(336,'CERRO COLORADO',35),\r\n" + 
			"(337,'CHARACATO',35),\r\n" + 
			"(338,'CHIGUATA',35),\r\n" + 
			"(339,'JACOBO HUNTER',35),\r\n" + 
			"(340,'JOSE LUIS BUSTAMANTE Y RIVERO',35),\r\n" + 
			"(341,'LA JOYA',35),\r\n" + 
			"(342,'MARIANO MELGAR',35),\r\n" + 
			"(343,'MIRAFLORES',35),\r\n" + 
			"(344,'MOLLEBAYA',35),\r\n" + 
			"(345,'PAUCARPATA',35),\r\n" + 
			"(346,'POCSI',35),\r\n" + 
			"(347,'POLOBAYA',35),\r\n" + 
			"(348,'QUEQUENA',35),\r\n" + 
			"(349,'SABANDIA',35),\r\n" + 
			"(350,'SACHACA',35),\r\n" + 
			"(351,'SAN JUAN DE SIGUAS',35),\r\n" + 
			"(352,'SAN JUAN DE TARUCANI',35),\r\n" + 
			"(353,'SANTA ISABEL DE SIGUAS',35),\r\n" + 
			"(354,'SOCABAYA',35),\r\n" + 
			"(355,'STA RITA DE SIGUAS',35),\r\n" + 
			"(356,'TIABAYA',35),\r\n" + 
			"(357,'UCHUMAYO',35),\r\n" + 
			"(358,'VITOR',35),\r\n" + 
			"(359,'YANAHUARA',35),\r\n" + 
			"(360,'YARABAMBA',35),\r\n" + 
			"(361,'YURA',35),\r\n" + 
			"(362,'CAMANA',36),\r\n" + 
			"(363,'JOSE MARIA QUIMPER',36),\r\n" + 
			"(364,'MARIANO N VALCARCEL',36),\r\n" + 
			"(365,'MARISCAL CACERES',36),\r\n" + 
			"(366,'NICOLAS DE PIEROLA',36),\r\n" + 
			"(367,'OCONA',36),\r\n" + 
			"(368,'QUILCA',36),\r\n" + 
			"(369,'SAMUEL PASTOR',36),\r\n" + 
			"(370,'ACARI',37),\r\n" + 
			"(371,'ATICO',37),\r\n" + 
			"(372,'ATIQUIPA',37),\r\n" + 
			"(373,'BELLA UNION',37),\r\n" + 
			"(374,'CAHUACHO',37),\r\n" + 
			"(375,'CARAVELI',37),\r\n" + 
			"(376,'CHALA',37),\r\n" + 
			"(377,'CHAPARRA',37),\r\n" + 
			"(378,'HUANUHUANU',37),\r\n" + 
			"(379,'JAQUI',37),\r\n" + 
			"(380,'LOMAS',37),\r\n" + 
			"(381,'QUICACHA',37),\r\n" + 
			"(382,'YAUCA',37),\r\n" + 
			"(383,'ANDAGUA',38),\r\n" + 
			"(384,'APLAO',38),\r\n" + 
			"(385,'AYO',38),\r\n" + 
			"(386,'CHACHAS',38),\r\n" + 
			"(387,'CHILCAYMARCA',38),\r\n" + 
			"(388,'CHOCO',38),\r\n" + 
			"(389,'HUANCARQUI',38),\r\n" + 
			"(390,'MACHAGUAY',38),\r\n" + 
			"(391,'ORCOPAMPA',38),\r\n" + 
			"(392,'PAMPACOLCA',38),\r\n" + 
			"(393,'TIPAN',38),\r\n" + 
			"(394,'UNON',38),\r\n" + 
			"(395,'URACA',38),\r\n" + 
			"(396,'VIRACO',38),\r\n" + 
			"(397,'ACHOMA',39),\r\n" + 
			"(398,'CABANACONDE',39),\r\n" + 
			"(399,'CALLALLI',39),\r\n" + 
			"(400,'CAYLLOMA',39),\r\n" + 
			"(401,'CHIVAY',39),\r\n" + 
			"(402,'COPORAQUE',39),\r\n" + 
			"(403,'HUAMBO',39),\r\n" + 
			"(404,'HUANCA',39),\r\n" + 
			"(405,'ICHUPAMPA',39),\r\n" + 
			"(406,'LARI',39),\r\n" + 
			"(407,'LLUTA',39),\r\n" + 
			"(408,'MACA',39),\r\n" + 
			"(409,'MADRIGAL',39),\r\n" + 
			"(410,'MAJES',39),\r\n" + 
			"(411,'SAN ANTONIO DE CHUCA',39),\r\n" + 
			"(412,'SIBAYO',39),\r\n" + 
			"(413,'TAPAY',39),\r\n" + 
			"(414,'TISCO',39),\r\n" + 
			"(415,'TUTI',39),\r\n" + 
			"(416,'YANQUE',39),\r\n" + 
			"(417,'ANDARAY',40),\r\n" + 
			"(418,'CAYARANI',40),\r\n" + 
			"(419,'CHICHAS',40),\r\n" + 
			"(420,'CHUQUIBAMBA',40),\r\n" + 
			"(421,'IRAY',40),\r\n" + 
			"(422,'RIO GRANDE',40),\r\n" + 
			"(423,'SALAMANCA',40),\r\n" + 
			"(424,'YANAQUIHUA',40),\r\n" + 
			"(425,'COCACHACRA',41),\r\n" + 
			"(426,'DEAN VALDIVIA',41),\r\n" + 
			"(427,'ISLAY',41),\r\n" + 
			"(428,'MEJIA',41),\r\n" + 
			"(429,'MOLLENDO',41),\r\n" + 
			"(430,'PUNTA DE BOMBON',41),\r\n" + 
			"(431,'ALCA',42),\r\n" + 
			"(432,'CHARCANA',42),\r\n" + 
			"(433,'COTAHUASI',42),\r\n" + 
			"(434,'HUAYNACOTAS',42),\r\n" + 
			"(435,'PAMPAMARCA',42),\r\n" + 
			"(436,'PUYCA',42),\r\n" + 
			"(437,'QUECHUALLA',42),\r\n" + 
			"(438,'SAYLA',42),\r\n" + 
			"(439,'TAURIA',42),\r\n" + 
			"(440,'TOMEPAMPA',42),\r\n" + 
			"(441,'TORO',42),\r\n" + 
			"(442,'CANGALLO',43),\r\n" + 
			"(443,'CHUSCHI',43),\r\n" + 
			"(444,'LOS MOROCHUCOS',43),\r\n" + 
			"(445,'MARIA PARADO DE BELLIDO',43),\r\n" + 
			"(446,'PARAS',43),\r\n" + 
			"(447,'TOTOS',43),\r\n" + 
			"(448,'ACOCRO',44),\r\n" + 
			"(449,'ACOS VINCHOS',44),\r\n" + 
			"(450,'AYACUCHO',44),\r\n" + 
			"(451,'CARMEN ALTO',44),\r\n" + 
			"(452,'CHIARA',44),\r\n" + 
			"(453,'JESUS NAZARENO',44),\r\n" + 
			"(454,'OCROS',44),\r\n" + 
			"(455,'PACAYCASA',44),\r\n" + 
			"(456,'QUINUA',44),\r\n" + 
			"(457,'SAN JOSE DE TICLLAS',44),\r\n" + 
			"(458,'SAN JUAN BAUTISTA',44),\r\n" + 
			"(459,'SANTIAGO DE PISCHA',44),\r\n" + 
			"(460,'SOCOS',44),\r\n" + 
			"(461,'TAMBILLO',44),\r\n" + 
			"(462,'VINCHOS',44),\r\n" + 
			"(463,'CARAPO',45),\r\n" + 
			"(464,'SACSAMARCA',45),\r\n" + 
			"(465,'SANCOS',45),\r\n" + 
			"(466,'SANTIAGO DE LUCANAMARCA',45),\r\n" + 
			"(467,'AYAHUANCO',46),\r\n" + 
			"(468,'HUAMANGUILLA',46),\r\n" + 
			"(469,'HUANTA',46),\r\n" + 
			"(470,'IGUAIN',46),\r\n" + 
			"(471,'LLOCHEGUA',46),\r\n" + 
			"(472,'LURICOCHA',46),\r\n" + 
			"(473,'SANTILLANA',46),\r\n" + 
			"(474,'SIVIA',46),\r\n" + 
			"(475,'ANCO',47),\r\n" + 
			"(476,'AYNA',47),\r\n" + 
			"(477,'CHILCAS',47),\r\n" + 
			"(478,'CHUNGUI',47),\r\n" + 
			"(479,'LUIS CARRANZA',47),\r\n" + 
			"(480,'SAN MIGUEL',47),\r\n" + 
			"(481,'SANTA ROSA',47),\r\n" + 
			"(482,'TAMBO',47),\r\n" + 
			"(483,'AUCARA',48),\r\n" + 
			"(484,'CABANA',48),\r\n" + 
			"(485,'CARMEN SALCEDO',48),\r\n" + 
			"(486,'CHAVINA',48),\r\n" + 
			"(487,'CHIPAO',48),\r\n" + 
			"(488,'HUAC-HUAS',48),\r\n" + 
			"(489,'LARAMATE',48),\r\n" + 
			"(490,'LEONCIO PRADO',48),\r\n" + 
			"(491,'LLAUTA',48),\r\n" + 
			"(492,'LUCANAS',48),\r\n" + 
			"(493,'OCANA',48),\r\n" + 
			"(494,'OTOCA',48),\r\n" + 
			"(495,'PUQUIO',48),\r\n" + 
			"(496,'SAISA',48),\r\n" + 
			"(497,'SAN CRISTOBAL',48),\r\n" + 
			"(498,'SAN JUAN',48),\r\n" + 
			"(499,'SAN PEDRO',48),\r\n" + 
			"(500,'SAN PEDRO DE PALCO',48),\r\n" + 
			"(501,'SANCOS',48),\r\n" + 
			"(502,'SANTA LUCIA',48),\r\n" + 
			"(503,'STA ANA DE HUAYCAHUACHO',48),\r\n" + 
			"(504,'CHUMPI',49),\r\n" + 
			"(505,'CORACORA',49),\r\n" + 
			"(506,'CORONEL CASTANEDA',49),\r\n" + 
			"(507,'PACAPAUSA',49),\r\n" + 
			"(508,'PULLO',49),\r\n" + 
			"(509,'PUYUSCA',49),\r\n" + 
			"(510,'SAN FCO DE RAVACAYCO',49),\r\n" + 
			"(511,'UPAHUACHO',49),\r\n" + 
			"(512,'COLTA',50),\r\n" + 
			"(513,'CORCULLA',50),\r\n" + 
			"(514,'LAMPA',50),\r\n" + 
			"(515,'MARCABAMBA',50),\r\n" + 
			"(516,'OYOLO',50),\r\n" + 
			"(517,'PARARCA',50),\r\n" + 
			"(518,'PAUSA',50),\r\n" + 
			"(519,'SAN JAVIER DE ALPABAMBA',50),\r\n" + 
			"(520,'SAN JOSE DE USHUA',50),\r\n" + 
			"(521,'SARA SARA',50),\r\n" + 
			"(522,'BELEN',51),\r\n" + 
			"(523,'CHALCOS',51),\r\n" + 
			"(524,'CHILCAYOC',51),\r\n" + 
			"(525,'HUACANA',51),\r\n" + 
			"(526,'MORCOLLA',51),\r\n" + 
			"(527,'PAICO',51),\r\n" + 
			"(528,'QUEROBAMBA',51),\r\n" + 
			"(529,'SAN PEDRO DE LARCAY',51),\r\n" + 
			"(530,'SAN SALVADOR DE QUIJE',51),\r\n" + 
			"(531,'SANTIAGO DE PAUCARAY',51),\r\n" + 
			"(532,'SORAS',51),\r\n" + 
			"(533,'ALCAMENCA',52),\r\n" + 
			"(534,'APONGO',52),\r\n" + 
			"(535,'ASQUIPATA',52),\r\n" + 
			"(536,'CANARIA',52),\r\n" + 
			"(537,'CAYARA',52),\r\n" + 
			"(538,'COLCA',52),\r\n" + 
			"(539,'HUAMANQUIQUIA',52),\r\n" + 
			"(540,'HUANCAPI',52),\r\n" + 
			"(541,'HUANCARAYLLA',52),\r\n" + 
			"(542,'HUAYA',52),\r\n" + 
			"(543,'SARHUA',52),\r\n" + 
			"(544,'VILCANCHOS',52),\r\n" + 
			"(545,'ACCOMARCA',53),\r\n" + 
			"(546,'CARHUANCA',53),\r\n" + 
			"(547,'CONCEPCION',53),\r\n" + 
			"(548,'HUAMBALPA',53),\r\n" + 
			"(549,'INDEPENDENCIA',53),\r\n" + 
			"(550,'SAURAMA',53),\r\n" + 
			"(551,'VILCAS HUAMAN',53),\r\n" + 
			"(552,'VISCHONGO',53),\r\n" + 
			"(553,'CACHACHI',54),\r\n" + 
			"(554,'CAJABAMBA',54),\r\n" + 
			"(555,'CONDEBAMBA',54),\r\n" + 
			"(556,'SITACOCHA',54),\r\n" + 
			"(557,'ASUNCION',55),\r\n" + 
			"(558,'CAJAMARCA',55),\r\n" + 
			"(559,'CHETILLA',55),\r\n" + 
			"(560,'COSPAN',55),\r\n" + 
			"(561,'ENCANADA',55),\r\n" + 
			"(562,'JESUS',55),\r\n" + 
			"(563,'LLACANORA',55),\r\n" + 
			"(564,'LOS BANOS DEL INCA',55),\r\n" + 
			"(565,'MAGDALENA',55),\r\n" + 
			"(566,'MATARA',55),\r\n" + 
			"(567,'NAMORA',55),\r\n" + 
			"(568,'SAN JUAN',55),\r\n" + 
			"(569,'CELENDIN',56),\r\n" + 
			"(570,'CHUMUCH',56),\r\n" + 
			"(571,'CORTEGANA',56),\r\n" + 
			"(572,'HUASMIN',56),\r\n" + 
			"(573,'JORGE CHAVEZ',56),\r\n" + 
			"(574,'JOSE GALVEZ',56),\r\n" + 
			"(575,'LA LIBERTAD DE PALLAN',56),\r\n" + 
			"(576,'MIGUEL IGLESIAS',56),\r\n" + 
			"(577,'OXAMARCA',56),\r\n" + 
			"(578,'SOROCHUCO',56),\r\n" + 
			"(579,'SUCRE',56),\r\n" + 
			"(580,'UTCO',56),\r\n" + 
			"(581,'ANGUIA',57),\r\n" + 
			"(582,'CHADIN',57),\r\n" + 
			"(583,'CHALAMARCA',57),\r\n" + 
			"(584,'CHIGUIRIP',57),\r\n" + 
			"(585,'CHIMBAN',57),\r\n" + 
			"(586,'CHOROPAMPA',57),\r\n" + 
			"(587,'CHOTA',57),\r\n" + 
			"(588,'COCHABAMBA',57),\r\n" + 
			"(589,'CONCHAN',57),\r\n" + 
			"(590,'HUAMBOS',57),\r\n" + 
			"(591,'LAJAS',57),\r\n" + 
			"(592,'LLAMA',57),\r\n" + 
			"(593,'MIRACOSTA',57),\r\n" + 
			"(594,'PACCHA',57),\r\n" + 
			"(595,'PION',57),\r\n" + 
			"(596,'QUEROCOTO',57),\r\n" + 
			"(597,'SAN JUAN DE LICUPIS',57),\r\n" + 
			"(598,'TACABAMBA',57),\r\n" + 
			"(599,'TOCMOCHE',57),\r\n" + 
			"(600,'CHILETE',58),\r\n" + 
			"(601,'CONTUMAZA',58),\r\n" + 
			"(602,'CUPISNIQUE',58),\r\n" + 
			"(603,'GUZMANGO',58),\r\n" + 
			"(604,'SAN BENITO',58),\r\n" + 
			"(605,'STA CRUZ DE TOLEDO',58),\r\n" + 
			"(606,'TANTARICA',58),\r\n" + 
			"(607,'YONAN',58),\r\n" + 
			"(608,'CALLAYUC',59),\r\n" + 
			"(609,'CHOROS',59),\r\n" + 
			"(610,'CUJILLO',59),\r\n" + 
			"(611,'CUTERVO',59),\r\n" + 
			"(612,'LA RAMADA',59),\r\n" + 
			"(613,'PIMPINGOS',59),\r\n" + 
			"(614,'QUEROCOTILLO',59),\r\n" + 
			"(615,'SAN ANDRES DE CUTERVO',59),\r\n" + 
			"(616,'SAN JUAN DE CUTERVO',59),\r\n" + 
			"(617,'SAN LUIS DE LUCMA',59),\r\n" + 
			"(618,'SANTA CRUZ',59),\r\n" + 
			"(619,'SANTO DOMINGO DE LA CAPILLA',59),\r\n" + 
			"(620,'SANTO TOMAS',59),\r\n" + 
			"(621,'SOCOTA',59),\r\n" + 
			"(622,'TORIBIO CASANOVA',59),\r\n" + 
			"(623,'BAMBAMARCA',60),\r\n" + 
			"(624,'CHUGUR',60),\r\n" + 
			"(625,'HUALGAYOC',60),\r\n" + 
			"(626,'BELLAVISTA',61),\r\n" + 
			"(627,'CHONTALI',61),\r\n" + 
			"(628,'COLASAY',61),\r\n" + 
			"(629,'HUABAL',61),\r\n" + 
			"(630,'JAEN',61),\r\n" + 
			"(631,'LAS PIRIAS',61),\r\n" + 
			"(632,'POMAHUACA',61),\r\n" + 
			"(633,'PUCARA',61),\r\n" + 
			"(634,'SALLIQUE',61),\r\n" + 
			"(635,'SAN FELIPE',61),\r\n" + 
			"(636,'SAN JOSE DEL ALTO',61),\r\n" + 
			"(637,'SANTA ROSA',61),\r\n" + 
			"(638,'CHIRINOS',62),\r\n" + 
			"(639,'HUARANGO',62),\r\n" + 
			"(640,'LA COIPA',62),\r\n" + 
			"(641,'NAMBALLE',62),\r\n" + 
			"(642,'SAN IGNACIO',62),\r\n" + 
			"(643,'SAN JOSE DE LOURDES',62),\r\n" + 
			"(644,'TABACONAS',62),\r\n" + 
			"(645,'CHANCAY',63),\r\n" + 
			"(646,'EDUARDO VILLANUEVA',63),\r\n" + 
			"(647,'GREGORIO PITA',63),\r\n" + 
			"(648,'ICHOCAN',63),\r\n" + 
			"(649,'JOSE MANUEL QUIROZ',63),\r\n" + 
			"(650,'JOSE SABOGAL',63),\r\n" + 
			"(651,'PEDRO GALVEZ',63),\r\n" + 
			"(652,'BOLIVAR',64),\r\n" + 
			"(653,'CALQUIS',64),\r\n" + 
			"(654,'CATILLUC',64),\r\n" + 
			"(655,'EL PRADO',64),\r\n" + 
			"(656,'LA FLORIDA',64),\r\n" + 
			"(657,'LLAPA',64),\r\n" + 
			"(658,'NANCHOC',64),\r\n" + 
			"(659,'NIEPOS',64),\r\n" + 
			"(660,'SAN GREGORIO',64),\r\n" + 
			"(661,'SAN MIGUEL',64),\r\n" + 
			"(662,'SAN SILVESTRE DE COCHAN',64),\r\n" + 
			"(663,'TONGOD',64),\r\n" + 
			"(664,'UNION AGUA BLANCA',64),\r\n" + 
			"(665,'SAN BERNARDINO',65),\r\n" + 
			"(666,'SAN LUIS',65),\r\n" + 
			"(667,'SAN PABLO',65),\r\n" + 
			"(668,'TUMBADEN',65),\r\n" + 
			"(669,'ANDABAMBA',66),\r\n" + 
			"(670,'CATACHE',66),\r\n" + 
			"(671,'CHANCAIBANOS',66),\r\n" + 
			"(672,'LA ESPERANZA',66),\r\n" + 
			"(673,'NINABAMBA',66),\r\n" + 
			"(674,'PULAN',66),\r\n" + 
			"(675,'SANTA CRUZ',66),\r\n" + 
			"(676,'SAUCEPAMPA',66),\r\n" + 
			"(677,'SEXI',66),\r\n" + 
			"(678,'UTICYACU',66),\r\n" + 
			"(679,'YAUYUCAN',66),\r\n" + 
			"(680,'ACOMAYO',67),\r\n" + 
			"(681,'ACOPIA',67),\r\n" + 
			"(682,'ACOS',67),\r\n" + 
			"(683,'MOSOC LLACTA',67),\r\n" + 
			"(684,'POMACANCHI',67),\r\n" + 
			"(685,'RONDOCAN',67),\r\n" + 
			"(686,'SANGARARA',67),\r\n" + 
			"(687,'ANCAHUASI',68),\r\n" + 
			"(688,'ANTA',68),\r\n" + 
			"(689,'CACHIMAYO',68),\r\n" + 
			"(690,'CHINCHAYPUJIO',68),\r\n" + 
			"(691,'HUAROCONDO',68),\r\n" + 
			"(692,'LIMATAMBO',68),\r\n" + 
			"(693,'MOLLEPATA',68),\r\n" + 
			"(694,'PUCYURA',68),\r\n" + 
			"(695,'ZURITE',68),\r\n" + 
			"(696,'CALCA',69),\r\n" + 
			"(697,'COYA',69),\r\n" + 
			"(698,'LAMAY',69),\r\n" + 
			"(699,'LARES',69),\r\n" + 
			"(700,'PISAC',69),\r\n" + 
			"(701,'SAN SALVADOR',69),\r\n" + 
			"(702,'TARAY',69),\r\n" + 
			"(703,'YANATILE',69),\r\n" + 
			"(704,'CHECCA',70),\r\n" + 
			"(705,'KUNTURKANKI',70),\r\n" + 
			"(706,'LANGUI',70),\r\n" + 
			"(707,'LAYO',70),\r\n" + 
			"(708,'PAMPAMARCA',70),\r\n" + 
			"(709,'QUEHUE',70),\r\n" + 
			"(710,'TUPAC AMARU',70),\r\n" + 
			"(711,'YANAOCA',70),\r\n" + 
			"(712,'CHECACUPE',71),\r\n" + 
			"(713,'COMBAPATA',71),\r\n" + 
			"(714,'MARANGANI',71),\r\n" + 
			"(715,'PITUMARCA',71),\r\n" + 
			"(716,'SAN PABLO',71),\r\n" + 
			"(717,'SAN PEDRO',71),\r\n" + 
			"(718,'SICUANI',71),\r\n" + 
			"(719,'TINTA',71),\r\n" + 
			"(720,'CAPACMARCA',72),\r\n" + 
			"(721,'CHAMACA',72),\r\n" + 
			"(722,'COLQUEMARCA',72),\r\n" + 
			"(723,'LIVITACA',72),\r\n" + 
			"(724,'LLUSCO',72),\r\n" + 
			"(725,'QUINOTA',72),\r\n" + 
			"(726,'SANTO TOMAS',72),\r\n" + 
			"(727,'VELILLE',72),\r\n" + 
			"(728,'CCORCA',73),\r\n" + 
			"(729,'CUSCO',73),\r\n" + 
			"(730,'POROY',73),\r\n" + 
			"(731,'SAN JERONIMO',73),\r\n" + 
			"(732,'SAN SEBASTIAN',73),\r\n" + 
			"(733,'SANTIAGO',73),\r\n" + 
			"(734,'SAYLLA',73),\r\n" + 
			"(735,'WANCHAQ',73),\r\n" + 
			"(736,'ALTO PICHIGUA',74),\r\n" + 
			"(737,'CONDOROMA',74),\r\n" + 
			"(738,'COPORAQUE',74),\r\n" + 
			"(739,'ESPINAR',74),\r\n" + 
			"(740,'OCORURO',74),\r\n" + 
			"(741,'PALLPATA',74),\r\n" + 
			"(742,'PICHIGUA',74),\r\n" + 
			"(743,'SUYKUTAMBO',74),\r\n" + 
			"(744,'ECHARATE',75),\r\n" + 
			"(745,'HUAYOPATA',75),\r\n" + 
			"(746,'KIMBIRI',75),\r\n" + 
			"(747,'MARANURA',75),\r\n" + 
			"(748,'OCOBAMBA',75),\r\n" + 
			"(749,'PICHARI',75),\r\n" + 
			"(750,'QUELLOUNO',75),\r\n" + 
			"(751,'SANTA ANA',75),\r\n" + 
			"(752,'SANTA TERESA',75),\r\n" + 
			"(753,'VILCABAMBA',75),\r\n" + 
			"(754,'ACCHA',76),\r\n" + 
			"(755,'CCAPI',76),\r\n" + 
			"(756,'COLCHA',76),\r\n" + 
			"(757,'HUANOQUITE',76),\r\n" + 
			"(758,'OMACHA',76),\r\n" + 
			"(759,'PACCARITAMBO',76),\r\n" + 
			"(760,'PARURO',76),\r\n" + 
			"(761,'PILLPINTO',76),\r\n" + 
			"(762,'YAURISQUE',76),\r\n" + 
			"(763,'CAICAY',77),\r\n" + 
			"(764,'CHALLABAMBA',77),\r\n" + 
			"(765,'COLQUEPATA',77),\r\n" + 
			"(766,'COSNIPATA',77),\r\n" + 
			"(767,'HUANCARANI',77),\r\n" + 
			"(768,'PAUCARTAMBO',77),\r\n" + 
			"(769,'ANDAHUAYLILLAS',78),\r\n" + 
			"(770,'CAMANTI',78),\r\n" + 
			"(771,'CCARHUAYO',78),\r\n" + 
			"(772,'CCATCA',78),\r\n" + 
			"(773,'CUSIPATA',78),\r\n" + 
			"(774,'HUARO',78),\r\n" + 
			"(775,'LUCRE',78),\r\n" + 
			"(776,'MARCAPATA',78),\r\n" + 
			"(777,'OCONGATE',78),\r\n" + 
			"(778,'OROPESA',78),\r\n" + 
			"(779,'QUIQUIJANA',78),\r\n" + 
			"(780,'URCOS',78),\r\n" + 
			"(781,'CHINCHERO',79),\r\n" + 
			"(782,'HUAYLLABAMBA',79),\r\n" + 
			"(783,'MACHUPICCHU',79),\r\n" + 
			"(784,'MARAS',79),\r\n" + 
			"(785,'OLLANTAYTAMBO',79),\r\n" + 
			"(786,'URUBAMBA',79),\r\n" + 
			"(787,'YUCAY',79),\r\n" + 
			"(788,'ACOBAMBA',80),\r\n" + 
			"(789,'ANDABAMBA',80),\r\n" + 
			"(790,'ANTA',80),\r\n" + 
			"(791,'CAJA',80),\r\n" + 
			"(792,'MARCAS',80),\r\n" + 
			"(793,'PAUCARA',80),\r\n" + 
			"(794,'POMACOCHA',80),\r\n" + 
			"(795,'ROSARIO',80),\r\n" + 
			"(796,'ANCHONGA',81),\r\n" + 
			"(797,'CALLANMARCA',81),\r\n" + 
			"(798,'CCOCHACCASA',81),\r\n" + 
			"(799,'CHINCHO',81),\r\n" + 
			"(800,'CONGALLA',81),\r\n" + 
			"(801,'HUANCA HUANCA',81),\r\n" + 
			"(802,'HUAYLLAY GRANDE',81),\r\n" + 
			"(803,'JULCAMARCA',81),\r\n" + 
			"(804,'LIRCAY',81),\r\n" + 
			"(805,'SAN ANTONIO DE ANTAPARCO',81),\r\n" + 
			"(806,'SECCLLA',81),\r\n" + 
			"(807,'STO TOMAS DE PATA',81),\r\n" + 
			"(808,'ARMA',82),\r\n" + 
			"(809,'AURAHUA',82),\r\n" + 
			"(810,'CAPILLAS',82),\r\n" + 
			"(811,'CASTROVIRREYNA',82),\r\n" + 
			"(812,'CHUPAMARCA',82),\r\n" + 
			"(813,'COCAS',82),\r\n" + 
			"(814,'HUACHOS',82),\r\n" + 
			"(815,'HUAMATAMBO',82),\r\n" + 
			"(816,'MOLLEPAMPA',82),\r\n" + 
			"(817,'SAN JUAN',82),\r\n" + 
			"(818,'SANTA ANA',82),\r\n" + 
			"(819,'TANTARA',82),\r\n" + 
			"(820,'TICRAPO',82),\r\n" + 
			"(821,'ANCO',83),\r\n" + 
			"(822,'CHINCHIHUASI',83),\r\n" + 
			"(823,'CHURCAMPA',83),\r\n" + 
			"(824,'EL CARMEN',83),\r\n" + 
			"(825,'LA MERCED',83),\r\n" + 
			"(826,'LOCROJA',83),\r\n" + 
			"(827,'PACHAMARCA',83),\r\n" + 
			"(828,'PAUCARBAMBA',83),\r\n" + 
			"(829,'SAN MIGUEL DE MAYOC',83),\r\n" + 
			"(830,'SAN PEDRO DE CORIS',83),\r\n" + 
			"(831,'ACOBAMBILLA',84),\r\n" + 
			"(832,'ACORIA',84),\r\n" + 
			"(833,'ASCENCION',84),\r\n" + 
			"(834,'CONAYCA',84),\r\n" + 
			"(835,'CUENCA',84),\r\n" + 
			"(836,'HUACHOCOLPA',84),\r\n" + 
			"(837,'HUANCAVELICA',84),\r\n" + 
			"(838,'HUANDO',84),\r\n" + 
			"(839,'HUAYLLAHUARA',84),\r\n" + 
			"(840,'IZCUCHACA',84),\r\n" + 
			"(841,'LARIA',84),\r\n" + 
			"(842,'MANTA',84),\r\n" + 
			"(843,'MARISCAL CACERES',84),\r\n" + 
			"(844,'MOYA',84),\r\n" + 
			"(845,'NUEVO OCCORO',84),\r\n" + 
			"(846,'PALCA',84),\r\n" + 
			"(847,'PILCHACA',84),\r\n" + 
			"(848,'VILCA',84),\r\n" + 
			"(849,'YAULI',84),\r\n" + 
			"(850,'AYAVI',85),\r\n" + 
			"(851,'CORDOVA',85),\r\n" + 
			"(852,'HUAYACUNDO ARMA',85),\r\n" + 
			"(853,'HUAYTARA',85),\r\n" + 
			"(854,'LARAMARCA',85),\r\n" + 
			"(855,'OCOYO',85),\r\n" + 
			"(856,'PILPICHACA',85),\r\n" + 
			"(857,'QUERCO',85),\r\n" + 
			"(858,'QUITO ARMA',85),\r\n" + 
			"(859,'SAN ANTONIO DE CUSICANCHA',85),\r\n" + 
			"(860,'SAN FRANCISCO DE SANGAYAICO',85),\r\n" + 
			"(861,'SAN ISIDRO',85),\r\n" + 
			"(862,'SANTIAGO DE CHOCORVOS',85),\r\n" + 
			"(863,'SANTIAGO DE QUIRAHUARA',85),\r\n" + 
			"(864,'SANTO DOMINGO DE CAPILLA',85),\r\n" + 
			"(865,'TAMBO',85),\r\n" + 
			"(866,'ACOSTAMBO',86),\r\n" + 
			"(867,'ACRAQUIA',86),\r\n" + 
			"(868,'AHUAYCHA',86),\r\n" + 
			"(869,'COLCABAMBA',86),\r\n" + 
			"(870,'DANIEL HERNANDEZ',86),\r\n" + 
			"(871,'HUACHOCOLPA',86),\r\n" + 
			"(872,'HUARIBAMBA',86),\r\n" + 
			"(873,'NAHUIMPUQUIO',86),\r\n" + 
			"(874,'PAMPAS',86),\r\n" + 
			"(875,'PAZOS',86),\r\n" + 
			"(876,'QUISHUAR',86),\r\n" + 
			"(877,'SALCABAMBA',86),\r\n" + 
			"(878,'SALCAHUASI',86),\r\n" + 
			"(879,'SAN MARCOS DE ROCCHAC',86),\r\n" + 
			"(880,'SURCUBAMBA',86),\r\n" + 
			"(881,'TINTAY PUNCU',86),\r\n" + 
			"(882,'AMBO',87),\r\n" + 
			"(883,'CAYNA',87),\r\n" + 
			"(884,'COLPAS',87),\r\n" + 
			"(885,'CONCHAMARCA',87),\r\n" + 
			"(886,'HUACAR',87),\r\n" + 
			"(887,'SAN FRANCISCO',87),\r\n" + 
			"(888,'SAN RAFAEL',87),\r\n" + 
			"(889,'TOMAY KICHWA',87),\r\n" + 
			"(890,'CHUQUIS',88),\r\n" + 
			"(891,'LA UNION',88),\r\n" + 
			"(892,'MARIAS',88),\r\n" + 
			"(893,'PACHAS',88),\r\n" + 
			"(894,'QUIVILLA',88),\r\n" + 
			"(895,'RIPAN',88),\r\n" + 
			"(896,'SHUNQUI',88),\r\n" + 
			"(897,'SILLAPATA',88),\r\n" + 
			"(898,'YANAS',88),\r\n" + 
			"(899,'CANCHABAMBA',89),\r\n" + 
			"(900,'COCHABAMBA',89),\r\n" + 
			"(901,'HUACAYBAMBA',89),\r\n" + 
			"(902,'PINRA',89),\r\n" + 
			"(903,'ARANCAY',90),\r\n" + 
			"(904,'CHAVIN DE PARIARCA',90),\r\n" + 
			"(905,'JACAS GRANDE',90),\r\n" + 
			"(906,'JIRCAN',90),\r\n" + 
			"(907,'LLATA',90),\r\n" + 
			"(908,'MIRAFLORES',90),\r\n" + 
			"(909,'MONZON',90),\r\n" + 
			"(910,'PUNCHAO',90),\r\n" + 
			"(911,'PUNOS',90),\r\n" + 
			"(912,'SINGA',90),\r\n" + 
			"(913,'TANTAMAYO',90),\r\n" + 
			"(914,'AMARILIS',91),\r\n" + 
			"(915,'CHINCHAO',91),\r\n" + 
			"(916,'CHURUBAMBA',91),\r\n" + 
			"(917,'HUANUCO',91),\r\n" + 
			"(918,'MARGOS',91),\r\n" + 
			"(919,'PILLCO MARCA',91),\r\n" + 
			"(920,'QUISQUI',91),\r\n" + 
			"(921,'SAN FCO DE CAYRAN',91),\r\n" + 
			"(922,'SAN PEDRO DE CHAULAN',91),\r\n" + 
			"(923,'STA MARIA DEL VALLE',91),\r\n" + 
			"(924,'YARUMAYO',91),\r\n" + 
			"(925,'BANOS',92),\r\n" + 
			"(926,'JESUS',92),\r\n" + 
			"(927,'JIVIA',92),\r\n" + 
			"(928,'QUEROPALCA',92),\r\n" + 
			"(929,'RONDOS',92),\r\n" + 
			"(930,'SAN FRANCISCO DE ASIS',92),\r\n" + 
			"(931,'SAN MIGUEL DE CAURI',92),\r\n" + 
			"(932,'DANIEL ALOMIA ROBLES',93),\r\n" + 
			"(933,'HERMILIO VALDIZAN',93),\r\n" + 
			"(934,'JOSE CRESPO Y CASTILLO',93),\r\n" + 
			"(935,'LUYANDO',93),\r\n" + 
			"(936,'MARIANO DAMASO BERAUN',93),\r\n" + 
			"(937,'RUPA RUPA',93),\r\n" + 
			"(938,'CHOLON',94),\r\n" + 
			"(939,'HUACRACHUCO',94),\r\n" + 
			"(940,'SAN BUENAVENTURA',94),\r\n" + 
			"(941,'CHAGLLA',95),\r\n" + 
			"(942,'MOLINO',95),\r\n" + 
			"(943,'PANAO',95),\r\n" + 
			"(944,'UMARI',95),\r\n" + 
			"(945,'CODO DEL POZUZO',96),\r\n" + 
			"(946,'HONORIA',96),\r\n" + 
			"(947,'PUERTO INCA',96),\r\n" + 
			"(948,'TOURNAVISTA',96),\r\n" + 
			"(949,'YUYAPICHIS',96),\r\n" + 
			"(950,'APARICIO POMARES (CHUPAN)',97),\r\n" + 
			"(951,'CAHUAC',97),\r\n" + 
			"(952,'CHACABAMBA',97),\r\n" + 
			"(953,'CHAVINILLO',97),\r\n" + 
			"(954,'CHORAS',97),\r\n" + 
			"(955,'JACAS CHICO',97),\r\n" + 
			"(956,'OBAS',97),\r\n" + 
			"(957,'PAMPAMARCA',97),\r\n" + 
			"(958,'ALTO LARAN',98),\r\n" + 
			"(959,'CHAVIN',98),\r\n" + 
			"(960,'CHINCHA ALTA',98),\r\n" + 
			"(961,'CHINCHA BAJA',98),\r\n" + 
			"(962,'EL CARMEN',98),\r\n" + 
			"(963,'GROCIO PRADO',98),\r\n" + 
			"(964,'PUEBLO NUEVO',98),\r\n" + 
			"(965,'SAN JUAN DE YANAC',98),\r\n" + 
			"(966,'SAN PEDRO DE HUACARPANA',98),\r\n" + 
			"(967,'SUNAMPE',98),\r\n" + 
			"(968,'TAMBO DE MORA',98),\r\n" + 
			"(969,'ICA',99),\r\n" + 
			"(970,'LA TINGUINA',99),\r\n" + 
			"(971,'LOS AQUIJES',99),\r\n" + 
			"(972,'OCUCAJE',99),\r\n" + 
			"(973,'PACHACUTEC',99),\r\n" + 
			"(974,'PARCONA',99),\r\n" + 
			"(975,'PUEBLO NUEVO',99),\r\n" + 
			"(976,'SALAS',99),\r\n" + 
			"(977,'SAN JOSE DE LOS MOLINOS',99),\r\n" + 
			"(978,'SAN JUAN BAUTISTA',99),\r\n" + 
			"(979,'SANTIAGO',99),\r\n" + 
			"(980,'SUBTANJALLA',99),\r\n" + 
			"(981,'TATE',99),\r\n" + 
			"(982,'YAUCA DEL ROSARIO',99),\r\n" + 
			"(983,'CHANGUILLO',100),\r\n" + 
			"(984,'EL INGENIO',100),\r\n" + 
			"(985,'MARCONA',100),\r\n" + 
			"(986,'NAZCA',100),\r\n" + 
			"(987,'VISTA ALEGRE',100),\r\n" + 
			"(988,'LLIPATA',101),\r\n" + 
			"(989,'PALPA',101),\r\n" + 
			"(990,'RIO GRANDE',101),\r\n" + 
			"(991,'SANTA CRUZ',101),\r\n" + 
			"(992,'TIBILLO',101),\r\n" + 
			"(993,'HUANCANO',102),\r\n" + 
			"(994,'HUMAY',102),\r\n" + 
			"(995,'INDEPENDENCIA',102),\r\n" + 
			"(996,'PARACAS',102),\r\n" + 
			"(997,'PISCO',102),\r\n" + 
			"(998,'SAN ANDRES',102),\r\n" + 
			"(999,'SAN CLEMENTE',102),\r\n" + 
			"(1000,'TUPAC AMARU INCA',102),\r\n" + 
			"(1001,'CHANCHAMAYO',103),\r\n" + 
			"(1002,'PERENE',103),\r\n" + 
			"(1003,'PICHANAQUI',103),\r\n" + 
			"(1004,'SAN LUIS DE SHUARO',103),\r\n" + 
			"(1005,'SAN RAMON',103),\r\n" + 
			"(1006,'VITOC',103),\r\n" + 
			"(1007,'AHUAC',104),\r\n" + 
			"(1008,'CHONGOS BAJO',104),\r\n" + 
			"(1009,'CHUPACA',104),\r\n" + 
			"(1010,'HUACHAC',104),\r\n" + 
			"(1011,'HUAMANCACA CHICO',104),\r\n" + 
			"(1012,'SAN JUAN DE ISCOS',104),\r\n" + 
			"(1013,'SAN JUAN DE JARPA',104),\r\n" + 
			"(1014,'TRES DE DICIEMBRE',104),\r\n" + 
			"(1015,'YANACANCHA',104),\r\n" + 
			"(1016,'ACO',105),\r\n" + 
			"(1017,'ANDAMARCA',105),\r\n" + 
			"(1018,'CHAMBARA',105),\r\n" + 
			"(1019,'COCHAS',105),\r\n" + 
			"(1020,'COMAS',105),\r\n" + 
			"(1021,'CONCEPCION',105),\r\n" + 
			"(1022,'HEROINAS TOLEDO',105),\r\n" + 
			"(1023,'MANZANARES',105),\r\n" + 
			"(1024,'MATAHUASI',105),\r\n" + 
			"(1025,'MCAL CASTILLA',105),\r\n" + 
			"(1026,'MITO',105),\r\n" + 
			"(1027,'NUEVE DE JULIO',105),\r\n" + 
			"(1028,'ORCOTUNA',105),\r\n" + 
			"(1029,'SAN JOSE DE QUERO',105),\r\n" + 
			"(1030,'STA ROSA DE OCOPA',105),\r\n" + 
			"(1031,'CARHUACALLANGA',106),\r\n" + 
			"(1032,'CHACAPAMPA',106),\r\n" + 
			"(1033,'CHICCHE',106),\r\n" + 
			"(1034,'CHILCA',106),\r\n" + 
			"(1035,'CHONGOS ALTO',106),\r\n" + 
			"(1036,'CHUPURO',106),\r\n" + 
			"(1037,'COLCA',106),\r\n" + 
			"(1038,'CULLHUAS',106),\r\n" + 
			"(1039,'EL TAMBO',106),\r\n" + 
			"(1040,'HUACRAPUQUIO',106),\r\n" + 
			"(1041,'HUALHUAS',106),\r\n" + 
			"(1042,'HUANCAN',106),\r\n" + 
			"(1043,'HUANCAYO',106),\r\n" + 
			"(1044,'HUASICANCHA',106),\r\n" + 
			"(1045,'HUAYUCACHI',106),\r\n" + 
			"(1046,'INGENIO',106),\r\n" + 
			"(1047,'PARIAHUANCA',106),\r\n" + 
			"(1048,'PILCOMAYO',106),\r\n" + 
			"(1049,'PUCARA',106),\r\n" + 
			"(1050,'QUICHUAY',106),\r\n" + 
			"(1051,'QUILCAS',106),\r\n" + 
			"(1052,'SAN AGUSTIN',106),\r\n" + 
			"(1053,'SAN JERONIMO DE TUNAN',106),\r\n" + 
			"(1054,'SANO',106),\r\n" + 
			"(1055,'SAPALLANGA',106),\r\n" + 
			"(1056,'SICAYA',106),\r\n" + 
			"(1057,'STO DOMINGO DE ACOBAMBA',106),\r\n" + 
			"(1058,'VIQUES',106),\r\n" + 
			"(1059,'ACOLLA',107),\r\n" + 
			"(1060,'APATA',107),\r\n" + 
			"(1061,'ATAURA',107),\r\n" + 
			"(1062,'CANCHAILLO',107),\r\n" + 
			"(1063,'CURICACA',107),\r\n" + 
			"(1064,'EL MANTARO',107),\r\n" + 
			"(1065,'HUAMALI',107),\r\n" + 
			"(1066,'HUARIPAMPA',107),\r\n" + 
			"(1067,'HUERTAS',107),\r\n" + 
			"(1068,'JANJAILLO',107),\r\n" + 
			"(1069,'JAUJA',107),\r\n" + 
			"(1070,'JULCAN',107),\r\n" + 
			"(1071,'LEONOR ORDONEZ',107),\r\n" + 
			"(1072,'LLOCLLAPAMPA',107),\r\n" + 
			"(1073,'MARCO',107),\r\n" + 
			"(1074,'MASMA',107),\r\n" + 
			"(1075,'MASMA CHICCHE',107),\r\n" + 
			"(1076,'MOLINOS',107),\r\n" + 
			"(1077,'MONOBAMBA',107),\r\n" + 
			"(1078,'MUQUI',107),\r\n" + 
			"(1079,'MUQUIYAUYO',107),\r\n" + 
			"(1080,'PACA',107),\r\n" + 
			"(1081,'PACCHA',107),\r\n" + 
			"(1082,'PANCAN',107),\r\n" + 
			"(1083,'PARCO',107),\r\n" + 
			"(1084,'POMACANCHA',107),\r\n" + 
			"(1085,'RICRAN',107),\r\n" + 
			"(1086,'SAN LORENZO',107),\r\n" + 
			"(1087,'SAN PEDRO DE CHUNAN',107),\r\n" + 
			"(1088,'SAUSA',107),\r\n" + 
			"(1089,'SINCOS',107),\r\n" + 
			"(1090,'TUNAN MARCA',107),\r\n" + 
			"(1091,'YAULI',107),\r\n" + 
			"(1092,'YAUYOS',107),\r\n" + 
			"(1093,'CARHUAMAYO',108),\r\n" + 
			"(1094,'JUNIN',108),\r\n" + 
			"(1095,'ONDORES',108),\r\n" + 
			"(1096,'ULCUMAYO',108),\r\n" + 
			"(1097,'COVIRIALI',109),\r\n" + 
			"(1098,'LLAYLLA',109),\r\n" + 
			"(1099,'MAZAMARI',109),\r\n" + 
			"(1100,'PAMPA HERMOSA',109),\r\n" + 
			"(1101,'PANGOA',109),\r\n" + 
			"(1102,'RIO NEGRO',109),\r\n" + 
			"(1103,'RIO TAMBO',109),\r\n" + 
			"(1104,'SATIPO',109),\r\n" + 
			"(1105,'ACOBAMBA',110),\r\n" + 
			"(1106,'HUARICOLCA',110),\r\n" + 
			"(1107,'HUASAHUASI',110),\r\n" + 
			"(1108,'LA UNION',110),\r\n" + 
			"(1109,'PALCA',110),\r\n" + 
			"(1110,'PALCAMAYO',110),\r\n" + 
			"(1111,'SAN PEDRO DE CAJAS',110),\r\n" + 
			"(1112,'TAPO',110),\r\n" + 
			"(1113,'TARMA',110),\r\n" + 
			"(1114,'CHACAPALPA',111),\r\n" + 
			"(1115,'HUAY HUAY',111),\r\n" + 
			"(1116,'LA OROYA',111),\r\n" + 
			"(1117,'MARCAPOMACOCHA',111),\r\n" + 
			"(1118,'MOROCOCHA',111),\r\n" + 
			"(1119,'PACCHA',111),\r\n" + 
			"(1120,'STA BARBARA DE CARHUACAYAN',111),\r\n" + 
			"(1121,'STA ROSA DE SACCO',111),\r\n" + 
			"(1122,'SUITUCANCHA',111),\r\n" + 
			"(1123,'YAULI',111),\r\n" + 
			"(1124,'ASCOPE',112),\r\n" + 
			"(1125,'CASA GRANDE',112),\r\n" + 
			"(1126,'CHICAMA',112),\r\n" + 
			"(1127,'CHOCOPE',112),\r\n" + 
			"(1128,'MAGDALENA DE CAO',112),\r\n" + 
			"(1129,'PAIJAN',112),\r\n" + 
			"(1130,'RAZURI',112),\r\n" + 
			"(1131,'SANTIAGO DE CAO',112),\r\n" + 
			"(1132,'BAMBAMARCA',113),\r\n" + 
			"(1133,'BOLIVAR',113),\r\n" + 
			"(1134,'CONDORMARCA',113),\r\n" + 
			"(1135,'LONGOTEA',113),\r\n" + 
			"(1136,'UCHUMARCA',113),\r\n" + 
			"(1137,'UCUNCHA',113),\r\n" + 
			"(1138,'CHEPEN',114),\r\n" + 
			"(1139,'PACANGA',114),\r\n" + 
			"(1140,'PUEBLO NUEVO',114),\r\n" + 
			"(1141,'CASCAS',115),\r\n" + 
			"(1142,'LUCMA',115),\r\n" + 
			"(1143,'MARMOT',115),\r\n" + 
			"(1144,'SAYAPULLO',115),\r\n" + 
			"(1145,'CALAMARCA',116),\r\n" + 
			"(1146,'CARABAMBA',116),\r\n" + 
			"(1147,'HUASO',116),\r\n" + 
			"(1148,'JULCAN',116),\r\n" + 
			"(1149,'AGALLPAMPA',117),\r\n" + 
			"(1150,'CHARAT',117),\r\n" + 
			"(1151,'HUARANCHAL',117),\r\n" + 
			"(1152,'LA CUESTA',117),\r\n" + 
			"(1153,'MACHE',117),\r\n" + 
			"(1154,'OTUZCO',117),\r\n" + 
			"(1155,'PARANDAY',117),\r\n" + 
			"(1156,'SALPO',117),\r\n" + 
			"(1157,'SINSICAP',117),\r\n" + 
			"(1158,'USQUIL',117),\r\n" + 
			"(1159,'GUADALUPE',118),\r\n" + 
			"(1160,'JEQUETEPEQUE',118),\r\n" + 
			"(1161,'PACASMAYO',118),\r\n" + 
			"(1162,'SAN JOSE',118),\r\n" + 
			"(1163,'SAN PEDRO DE LLOC',118),\r\n" + 
			"(1164,'BULDIBUYO',119),\r\n" + 
			"(1165,'CHILLIA',119),\r\n" + 
			"(1166,'HUANCASPATA',119),\r\n" + 
			"(1167,'HUAYLILLAS',119),\r\n" + 
			"(1168,'HUAYO',119),\r\n" + 
			"(1169,'ONGON',119),\r\n" + 
			"(1170,'PARCOY',119),\r\n" + 
			"(1171,'PATAZ',119),\r\n" + 
			"(1172,'PIAS',119),\r\n" + 
			"(1173,'SANTIAGO DE CHALLAS',119),\r\n" + 
			"(1174,'TAURIJA',119),\r\n" + 
			"(1175,'TAYABAMBA',119),\r\n" + 
			"(1176,'URPAY',119),\r\n" + 
			"(1177,'CHUGAY',120),\r\n" + 
			"(1178,'COCHORCO',120),\r\n" + 
			"(1179,'CURGOS',120),\r\n" + 
			"(1180,'HUAMACHUCO',120),\r\n" + 
			"(1181,'MARCABAL',120),\r\n" + 
			"(1182,'SANAGORAN',120),\r\n" + 
			"(1183,'SARIN',120),\r\n" + 
			"(1184,'SARTIMBAMBA',120),\r\n" + 
			"(1185,'ANGASMARCA',121),\r\n" + 
			"(1186,'CACHICADAN',121),\r\n" + 
			"(1187,'MOLLEBAMBA',121),\r\n" + 
			"(1188,'MOLLEPATA',121),\r\n" + 
			"(1189,'QUIRUVILCA',121),\r\n" + 
			"(1190,'SANTA CRUZ DE CHUCA',121),\r\n" + 
			"(1191,'SANTIAGO DE CHUCO',121),\r\n" + 
			"(1192,'SITABAMBA',121),\r\n" + 
			"(1193,'EL PORVENIR',122),\r\n" + 
			"(1194,'FLORENCIA DE MORA',122),\r\n" + 
			"(1195,'HUANCHACO',122),\r\n" + 
			"(1196,'LA ESPERANZA',122),\r\n" + 
			"(1197,'LAREDO',122),\r\n" + 
			"(1198,'MOCHE',122),\r\n" + 
			"(1199,'POROTO',122),\r\n" + 
			"(1200,'SALAVERRY',122),\r\n" + 
			"(1201,'SIMBAL',122),\r\n" + 
			"(1202,'TRUJILLO',122),\r\n" + 
			"(1203,'VICTOR LARCO HERRERA',122),\r\n" + 
			"(1204,'CHAO',123),\r\n" + 
			"(1205,'GUADALUPITO',123),\r\n" + 
			"(1206,'VIRU',123),\r\n" + 
			"(1207,'CAYALTI',124),\r\n" + 
			"(1208,'CHICLAYO',124),\r\n" + 
			"(1209,'CHONGOYAPE',124),\r\n" + 
			"(1210,'ETEN',124),\r\n" + 
			"(1211,'ETEN PUERTO',124),\r\n" + 
			"(1212,'JOSE LEONARDO ORTIZ',124),\r\n" + 
			"(1213,'LA VICTORIA',124),\r\n" + 
			"(1214,'LAGUNAS',124),\r\n" + 
			"(1215,'MONSEFU',124),\r\n" + 
			"(1216,'NUEVA ARICA',124),\r\n" + 
			"(1217,'OYOTUN',124),\r\n" + 
			"(1218,'PATAPO',124),\r\n" + 
			"(1219,'PICSI',124),\r\n" + 
			"(1220,'PIMENTEL',124),\r\n" + 
			"(1221,'POMALCA',124),\r\n" + 
			"(1222,'PUCALA',124),\r\n" + 
			"(1223,'REQUE',124),\r\n" + 
			"(1224,'SANA',124),\r\n" + 
			"(1225,'SANTA ROSA',124),\r\n" + 
			"(1226,'TUMAN',124),\r\n" + 
			"(1227,'CANARIS',125),\r\n" + 
			"(1228,'FERRENAFE',125),\r\n" + 
			"(1229,'INCAHUASI',125),\r\n" + 
			"(1230,'MANUEL ANTONIO MESONES MURO',125),\r\n" + 
			"(1231,'PITIPO',125),\r\n" + 
			"(1232,'PUEBLO NUEVO',125),\r\n" + 
			"(1233,'CHOCHOPE',126),\r\n" + 
			"(1234,'ILLIMO',126),\r\n" + 
			"(1235,'JAYANCA',126),\r\n" + 
			"(1236,'LAMBAYEQUE',126),\r\n" + 
			"(1237,'MOCHUMI',126),\r\n" + 
			"(1238,'MORROPE',126),\r\n" + 
			"(1239,'MOTUPE',126),\r\n" + 
			"(1240,'OLMOS',126),\r\n" + 
			"(1241,'PACORA',126),\r\n" + 
			"(1242,'SALAS',126),\r\n" + 
			"(1243,'SAN JOSE',126),\r\n" + 
			"(1244,'TUCUME',126),\r\n" + 
			"(1245,'BARRANCA',127),\r\n" + 
			"(1246,'PARAMONGA',127),\r\n" + 
			"(1247,'PATIVILCA',127),\r\n" + 
			"(1248,'SUPE',127),\r\n" + 
			"(1249,'SUPE PUERTO',127),\r\n" + 
			"(1250,'CAJATAMBO',128),\r\n" + 
			"(1251,'COPA',128),\r\n" + 
			"(1252,'GORGOR',128),\r\n" + 
			"(1253,'HUANCAPON',128),\r\n" + 
			"(1254,'MANAS',128),\r\n" + 
			"(1255,'ASIA',129),\r\n" + 
			"(1256,'CALANGO',129),\r\n" + 
			"(1257,'CERRO AZUL',129),\r\n" + 
			"(1258,'CHILCA',129),\r\n" + 
			"(1259,'COAYLLO',129),\r\n" + 
			"(1260,'IMPERIAL',129),\r\n" + 
			"(1261,'LUNAHUANA',129),\r\n" + 
			"(1262,'MALA',129),\r\n" + 
			"(1263,'NUEVO IMPERIAL',129),\r\n" + 
			"(1264,'PACARAN',129),\r\n" + 
			"(1265,'QUILMANA',129),\r\n" + 
			"(1266,'SAN ANTONIO',129),\r\n" + 
			"(1267,'SAN LUIS',129),\r\n" + 
			"(1268,'SAN VICENTE DE CANETE',129),\r\n" + 
			"(1269,'SANTA CRUZ DE FLORES',129),\r\n" + 
			"(1270,'ZUNIGA',129),\r\n" + 
			"(1271,'ARAHUAY',130),\r\n" + 
			"(1272,'CANTA',130),\r\n" + 
			"(1273,'HUAMANTANGA',130),\r\n" + 
			"(1274,'HUAROS',130),\r\n" + 
			"(1275,'LACHAQUI',130),\r\n" + 
			"(1276,'SAN BUENAVENTURA',130),\r\n" + 
			"(1277,'SANTA ROSA DE QUIVES',130),\r\n" + 
			"(1278,'ATAVILLOS ALTO',131),\r\n" + 
			"(1279,'ATAVILLOS BAJO',131),\r\n" + 
			"(1280,'AUCALLAMA',131),\r\n" + 
			"(1281,'CHANCAY',131),\r\n" + 
			"(1282,'HUARAL',131),\r\n" + 
			"(1283,'IHUARI',131),\r\n" + 
			"(1284,'LAMPIAN',131),\r\n" + 
			"(1285,'PACARAOS',131),\r\n" + 
			"(1286,'SAN MIGUEL DE ACOS',131),\r\n" + 
			"(1287,'STA CRUZ DE ANDAMARCA',131),\r\n" + 
			"(1288,'SUMBILCA',131),\r\n" + 
			"(1289,'VEINTISIETE DE NOVIEMBRE',131),\r\n" + 
			"(1290,'ANTIOQUIA',132),\r\n" + 
			"(1291,'CALLAHUANCA',132),\r\n" + 
			"(1292,'CARAMPOMA',132),\r\n" + 
			"(1293,'CASTA',132),\r\n" + 
			"(1294,'CHICLA',132),\r\n" + 
			"(1295,'CUENCA',132),\r\n" + 
			"(1296,'HUACHUPAMPA',132),\r\n" + 
			"(1297,'HUANZA',132),\r\n" + 
			"(1298,'HUAROCHIRI',132),\r\n" + 
			"(1299,'LAHUAYTAMBO',132),\r\n" + 
			"(1300,'LANGA',132),\r\n" + 
			"(1301,'LARAOS',132),\r\n" + 
			"(1302,'MARIATANA',132),\r\n" + 
			"(1303,'MATUCANA',132),\r\n" + 
			"(1304,'RICARDO PALMA',132),\r\n" + 
			"(1305,'SAN ANDRES DE TUPICOCHA',132),\r\n" + 
			"(1306,'SAN ANTONIO',132),\r\n" + 
			"(1307,'SAN BARTOLOME',132),\r\n" + 
			"(1308,'SAN DAMIAN',132),\r\n" + 
			"(1309,'SAN JUAN DE IRIS',132),\r\n" + 
			"(1310,'SAN JUAN DE TANTARANCHE',132),\r\n" + 
			"(1311,'SAN LORENZO DE QUINTI',132),\r\n" + 
			"(1312,'SAN MATEO',132),\r\n" + 
			"(1313,'SAN MATEO DE OTAO',132),\r\n" + 
			"(1314,'SAN PEDRO DE HUANCAYRE',132),\r\n" + 
			"(1315,'SAN PEDRO DE HUANCAYRE',132),\r\n" + 
			"(1316,'SANGALLAYA',132),\r\n" + 
			"(1317,'SANTA CRUZ DE COCACHACRA',132),\r\n" + 
			"(1318,'SANTA EULALIA',132),\r\n" + 
			"(1319,'SANTIAGO DE ANCHUCAYA',132),\r\n" + 
			"(1320,'SANTIAGO DE TUNA',132),\r\n" + 
			"(1321,'SURCO',132),\r\n" + 
			"(1322,'AMBAR',133),\r\n" + 
			"(1323,'CALETA DE CARQUIN',133),\r\n" + 
			"(1324,'CHECRAS',133),\r\n" + 
			"(1325,'HUACHO',133),\r\n" + 
			"(1326,'HUALMAY',133),\r\n" + 
			"(1327,'HUAURA',133),\r\n" + 
			"(1328,'LEONCIO PRADO',133),\r\n" + 
			"(1329,'PACCHO',133),\r\n" + 
			"(1330,'SANTA LEONOR',133),\r\n" + 
			"(1331,'SANTA MARIA',133),\r\n" + 
			"(1332,'SAYAN',133),\r\n" + 
			"(1333,'VEGUETA',133),\r\n" + 
			"(1334,'ANCON',134),\r\n" + 
			"(1335,'ATE',134),\r\n" + 
			"(1336,'BARRANCO',134),\r\n" + 
			"(1337,'BRENA',134),\r\n" + 
			"(1338,'CARABAYLLO',134),\r\n" + 
			"(1339,'CHACLACAYO',134),\r\n" + 
			"(1340,'CHORRILLOS',134),\r\n" + 
			"(1341,'CIENEGUILLA',134),\r\n" + 
			"(1342,'COMAS',134),\r\n" + 
			"(1343,'EL AGUSTINO',134),\r\n" + 
			"(1344,'INDEPENDENCIA',134),\r\n" + 
			"(1345,'JESUS MARIA',134),\r\n" + 
			"(1346,'LA MOLINA',134),\r\n" + 
			"(1347,'LA VICTORIA',134),\r\n" + 
			"(1348,'LIMA',134),\r\n" + 
			"(1349,'LINCE',134),\r\n" + 
			"(1350,'LOS OLIVOS',134),\r\n" + 
			"(1351,'LURIGANCHO',134),\r\n" + 
			"(1352,'LURIN',134),\r\n" + 
			"(1353,'MAGDALENA DEL MAR',134),\r\n" + 
			"(1354,'MIRAFLORES',134),\r\n" + 
			"(1355,'PACHACAMAC',134),\r\n" + 
			"(1356,'PUCUSANA',134),\r\n" + 
			"(1357,'PUEBLO LIBRE',134),\r\n" + 
			"(1358,'PUENTE PIEDRA',134),\r\n" + 
			"(1359,'PUNTA HERMOSA',134),\r\n" + 
			"(1360,'PUNTA NEGRA',134),\r\n" + 
			"(1361,'RIMAC',134),\r\n" + 
			"(1362,'SAN BARTOLO',134),\r\n" + 
			"(1363,'SAN BORJA',134),\r\n" + 
			"(1364,'SAN ISIDRO',134),\r\n" + 
			"(1365,'SAN JUAN DE LURIGANCHO',134),\r\n" + 
			"(1366,'SAN JUAN DE MIRAFLORES',134),\r\n" + 
			"(1367,'SAN LUIS',134),\r\n" + 
			"(1368,'SAN MIGUEL',134),\r\n" + 
			"(1369,'SANTA ANITA',134),\r\n" + 
			"(1370,'SANTA ROSA',134),\r\n" + 
			"(1371,'SANTIAGO DE SURCO',134),\r\n" + 
			"(1372,'STA MARIA DEL MAR',134),\r\n" + 
			"(1373,'SURQUILLO',134),\r\n" + 
			"(1374,'VILLA EL SALVADOR',134),\r\n" + 
			"(1375,'ANDAJES',135),\r\n" + 
			"(1376,'CAUJUL',135),\r\n" + 
			"(1377,'COCHAMARCA',135),\r\n" + 
			"(1378,'NAVAN',135),\r\n" + 
			"(1379,'OYON',135),\r\n" + 
			"(1380,'PACHANGARA',135),\r\n" + 
			"(1381,'ALIS',136),\r\n" + 
			"(1382,'AYAUCA',136),\r\n" + 
			"(1383,'AYAVIRI',136),\r\n" + 
			"(1384,'AZANGARO',136),\r\n" + 
			"(1385,'CACRA',136),\r\n" + 
			"(1386,'CARANIA',136),\r\n" + 
			"(1387,'CATAHUASI',136),\r\n" + 
			"(1388,'CHOCOS',136),\r\n" + 
			"(1389,'COCHAS',136),\r\n" + 
			"(1390,'COLONIA',136),\r\n" + 
			"(1391,'HONGOS',136),\r\n" + 
			"(1392,'HUAMPARA',136),\r\n" + 
			"(1393,'HUANCAYA',136),\r\n" + 
			"(1394,'HUANEC',136),\r\n" + 
			"(1395,'HUANGASCAR',136),\r\n" + 
			"(1396,'HUANTAN',136),\r\n" + 
			"(1397,'LARAOS',136),\r\n" + 
			"(1398,'LINCHA',136),\r\n" + 
			"(1399,'MADEAN',136),\r\n" + 
			"(1400,'MIRAFLORES',136),\r\n" + 
			"(1401,'OMAS',136),\r\n" + 
			"(1402,'PUTINZA',136),\r\n" + 
			"(1403,'QUINCHES',136),\r\n" + 
			"(1404,'QUINOCAY',136),\r\n" + 
			"(1405,'SAN JOAQUIN',136),\r\n" + 
			"(1406,'SAN PEDRO DE PILAS',136),\r\n" + 
			"(1407,'TANTA',136),\r\n" + 
			"(1408,'TAURIPAMPA',136),\r\n" + 
			"(1409,'TOMAS',136),\r\n" + 
			"(1410,'TUPE',136),\r\n" + 
			"(1411,'VINAC',136),\r\n" + 
			"(1412,'VITIS',136),\r\n" + 
			"(1413,'YAUYOS',136),\r\n" + 
			"(1414,'BALSAPUERTO',137),\r\n" + 
			"(1415,'JEBEROS',137),\r\n" + 
			"(1416,'LAGUNAS',137),\r\n" + 
			"(1417,'SANTA CRUZ',137),\r\n" + 
			"(1418,'TNTE CESAR LOPEZ ROJAS',137),\r\n" + 
			"(1419,'YURIMAGUAS',137),\r\n" + 
			"(1420,'ANDOAS',138),\r\n" + 
			"(1421,'BARRANCA',138),\r\n" + 
			"(1422,'CAHUAPANAS',138),\r\n" + 
			"(1423,'MANSERICHE',138),\r\n" + 
			"(1424,'MORONA',138),\r\n" + 
			"(1425,'PASTAZA',138),\r\n" + 
			"(1426,'NAUTA',139),\r\n" + 
			"(1427,'PARINARI',139),\r\n" + 
			"(1428,'TIGRE',139),\r\n" + 
			"(1429,'TROMPETEROS',139),\r\n" + 
			"(1430,'URARINAS',139),\r\n" + 
			"(1431,'MARISCAL RAMON CASTILLA',140),\r\n" + 
			"(1432,'PEBAS',140),\r\n" + 
			"(1433,'SAN PABLO',140),\r\n" + 
			"(1434,'YAVARI',140),\r\n" + 
			"(1435,'ALTO NANAY',141),\r\n" + 
			"(1436,'BELEN',141),\r\n" + 
			"(1437,'FERNANDO LORES',141),\r\n" + 
			"(1438,'INDIANA',141),\r\n" + 
			"(1439,'IQUITOS',141),\r\n" + 
			"(1440,'LAS AMAZONAS',141),\r\n" + 
			"(1441,'MAZAN',141),\r\n" + 
			"(1442,'NAPO',141),\r\n" + 
			"(1443,'PUNCHANA',141),\r\n" + 
			"(1444,'PUTUMAYO',141),\r\n" + 
			"(1445,'SAN JUAN BAUTISTA',141),\r\n" + 
			"(1446,'TNTE MANUEL CLAVERO',141),\r\n" + 
			"(1447,'TORRES CAUSANA',141),\r\n" + 
			"(1448,'ALTO TAPICHE',142),\r\n" + 
			"(1449,'CAPELO',142),\r\n" + 
			"(1450,'EMILIO SAN MARTIN',142),\r\n" + 
			"(1451,'JENARO HERRERA',142),\r\n" + 
			"(1452,'MAQUIA',142),\r\n" + 
			"(1453,'PUINAHUA',142),\r\n" + 
			"(1454,'REQUENA',142),\r\n" + 
			"(1455,'SAQUENA',142),\r\n" + 
			"(1456,'SOPLIN',142),\r\n" + 
			"(1457,'TAPICHE',142),\r\n" + 
			"(1458,'YAQUERANA',142),\r\n" + 
			"(1459,'CONTAMANA',143),\r\n" + 
			"(1460,'INAHUAYA',143),\r\n" + 
			"(1461,'PADRE MARQUEZ',143),\r\n" + 
			"(1462,'PAMPA HERMOZA',143),\r\n" + 
			"(1463,'SARAYACU',143),\r\n" + 
			"(1464,'VARGAS GUERRA',143),\r\n" + 
			"(1465,'FITZCARRALD',144),\r\n" + 
			"(1466,'HUEPETUHE',144),\r\n" + 
			"(1467,'MADRE DE DIOS',144),\r\n" + 
			"(1468,'MANU',144),\r\n" + 
			"(1469,'IBERIA',145),\r\n" + 
			"(1470,'INAPARI',145),\r\n" + 
			"(1471,'TAHUAMANU',145),\r\n" + 
			"(1472,'INAMBARI',146),\r\n" + 
			"(1473,'LABERINTO',146),\r\n" + 
			"(1474,'LAS PIEDRAS',146),\r\n" + 
			"(1475,'TAMBOPATA',146),\r\n" + 
			"(1476,'CHOJATA',147),\r\n" + 
			"(1477,'COALAQUE',147),\r\n" + 
			"(1478,'ICHUNA',147),\r\n" + 
			"(1479,'LA CAPILLA',147),\r\n" + 
			"(1480,'LLOQUE',147),\r\n" + 
			"(1481,'MATALAQUE',147),\r\n" + 
			"(1482,'OMATE',147),\r\n" + 
			"(1483,'PUQUINA',147),\r\n" + 
			"(1484,'QUINISTAQUILLAS',147),\r\n" + 
			"(1485,'UBINAS',147),\r\n" + 
			"(1486,'YUNGA',147),\r\n" + 
			"(1487,'EL ALGARROBAL',148),\r\n" + 
			"(1488,'ILO',148),\r\n" + 
			"(1489,'PACOCHA',148),\r\n" + 
			"(1490,'CARUMAS',149),\r\n" + 
			"(1491,'CUCHUMBAYA',149),\r\n" + 
			"(1492,'MOQUEGUA',149),\r\n" + 
			"(1493,'SAMEGUA',149),\r\n" + 
			"(1494,'SAN CRISTOBAL',149),\r\n" + 
			"(1495,'TORATA',149),\r\n" + 
			"(1496,'CHACAYAN',150),\r\n" + 
			"(1497,'GOYLLARISQUIZGA',150),\r\n" + 
			"(1498,'PAUCAR',150),\r\n" + 
			"(1499,'SAN PEDRO DE PILLAO',150),\r\n" + 
			"(1500,'SANTA ANA DE TUSI',150),\r\n" + 
			"(1501,'TAPUC',150),\r\n" + 
			"(1502,'VILCABAMBA',150),\r\n" + 
			"(1503,'YANAHUANCA',150),\r\n" + 
			"(1504,'CHONTABAMBA',151),\r\n" + 
			"(1505,'HUANCABAMBA',151),\r\n" + 
			"(1506,'OXAPAMPA',151),\r\n" + 
			"(1507,'PALCAZU',151),\r\n" + 
			"(1508,'POZUZO',151),\r\n" + 
			"(1509,'PUERTO BERMUDEZ',151),\r\n" + 
			"(1510,'VILLA RICA',151),\r\n" + 
			"(1511,'CHAUPIMARCA',152),\r\n" + 
			"(1512,'HUACHON',152),\r\n" + 
			"(1513,'HUARIACA',152),\r\n" + 
			"(1514,'HUAYLLAY',152),\r\n" + 
			"(1515,'NINACACA',152),\r\n" + 
			"(1516,'PALLANCHACRA',152),\r\n" + 
			"(1517,'PAUCARTAMBO',152),\r\n" + 
			"(1518,'SAN FCO DE ASIS DE YARUSYACAN',152),\r\n" + 
			"(1519,'SIMON BOLIVAR',152),\r\n" + 
			"(1520,'TICLACAYAN',152),\r\n" + 
			"(1521,'TINYAHUARCO',152),\r\n" + 
			"(1522,'VICCO',152),\r\n" + 
			"(1523,'YANACANCHA',152),\r\n" + 
			"(1524,'AYABACA',153),\r\n" + 
			"(1525,'FRIAS',153),\r\n" + 
			"(1526,'JILILI',153),\r\n" + 
			"(1527,'LAGUNAS',153),\r\n" + 
			"(1528,'MONTERO',153),\r\n" + 
			"(1529,'PACAIPAMPA',153),\r\n" + 
			"(1530,'PAIMAS',153),\r\n" + 
			"(1531,'SAPILLICA',153),\r\n" + 
			"(1532,'SICCHEZ',153),\r\n" + 
			"(1533,'SUYO',153),\r\n" + 
			"(1534,'CANCHAQUE',154),\r\n" + 
			"(1535,'EL CARMEN DE LA FRONTERA',154),\r\n" + 
			"(1536,'HUANCABAMBA',154),\r\n" + 
			"(1537,'HUARMACA',154),\r\n" + 
			"(1538,'LALAQUIZ',154),\r\n" + 
			"(1539,'SAN MIGUEL DE EL FAIQUE',154),\r\n" + 
			"(1540,'SONDOR',154),\r\n" + 
			"(1541,'SONDORILLO',154),\r\n" + 
			"(1542,'BUENOS AIRES',155),\r\n" + 
			"(1543,'CHALACO',155),\r\n" + 
			"(1544,'CHULUCANAS',155),\r\n" + 
			"(1545,'LA MATANZA',155),\r\n" + 
			"(1546,'MORROPON',155),\r\n" + 
			"(1547,'SALITRAL',155),\r\n" + 
			"(1548,'SAN JUAN DE BIGOTE',155),\r\n" + 
			"(1549,'SANTA CATALINA DE MOSSA',155),\r\n" + 
			"(1550,'SANTO DOMINGO',155),\r\n" + 
			"(1551,'YAMANGO',155),\r\n" + 
			"(1552,'AMOTAPE',156),\r\n" + 
			"(1553,'ARENAL',156),\r\n" + 
			"(1554,'LA HUACA',156),\r\n" + 
			"(1555,'PAITA',156),\r\n" + 
			"(1556,'PUEBLO NUEVO DE COLAN',156),\r\n" + 
			"(1557,'TAMARINDO',156),\r\n" + 
			"(1558,'VICHAYAL',156),\r\n" + 
			"(1559,'CASTILLA',157),\r\n" + 
			"(1560,'CATACAOS',157),\r\n" + 
			"(1561,'CURA MORI',157),\r\n" + 
			"(1562,'EL TALLAN',157),\r\n" + 
			"(1563,'LA ARENA',157),\r\n" + 
			"(1564,'LA UNION',157),\r\n" + 
			"(1565,'LAS LOMAS',157),\r\n" + 
			"(1566,'PIURA',157),\r\n" + 
			"(1567,'TAMBO GRANDE',157),\r\n" + 
			"(1568,'BELLAVISTA DE LA UNION',158),\r\n" + 
			"(1569,'BERNAL',158),\r\n" + 
			"(1570,'CRISTO NOS VALGA',158),\r\n" + 
			"(1571,'RINCONADA LLICUAR',158),\r\n" + 
			"(1572,'SECHURA',158),\r\n" + 
			"(1573,'VICE',158),\r\n" + 
			"(1574,'BELLAVISTA',159),\r\n" + 
			"(1575,'IGNACIO ESCUDERO',159),\r\n" + 
			"(1576,'LANCONES',159),\r\n" + 
			"(1577,'MARCAVELICA',159),\r\n" + 
			"(1578,'MIGUEL CHECA',159),\r\n" + 
			"(1579,'QUERECOTILLO',159),\r\n" + 
			"(1580,'SALITRAL',159),\r\n" + 
			"(1581,'SULLANA',159),\r\n" + 
			"(1582,'EL ALTO',160),\r\n" + 
			"(1583,'LA BREA',160),\r\n" + 
			"(1584,'LOBITOS',160),\r\n" + 
			"(1585,'LOS ORGANOS',160),\r\n" + 
			"(1586,'MANCORA',160),\r\n" + 
			"(1587,'PARINAS',160),\r\n" + 
			"(1588,'ACHAYA',161),\r\n" + 
			"(1589,'ARAPA',161),\r\n" + 
			"(1590,'ASILLO',161),\r\n" + 
			"(1591,'AZANGARO',161),\r\n" + 
			"(1592,'CAMINACA',161),\r\n" + 
			"(1593,'CHUPA',161),\r\n" + 
			"(1594,'JOSE DOMINGO CHOQUEHUANCA',161),\r\n" + 
			"(1595,'MUNANI',161),\r\n" + 
			"(1596,'POTONI',161),\r\n" + 
			"(1597,'SAMAN',161),\r\n" + 
			"(1598,'SAN ANTON',161),\r\n" + 
			"(1599,'SAN JOSE',161),\r\n" + 
			"(1600,'SAN JUAN DE SALINAS',161),\r\n" + 
			"(1601,'STGO DE PUPUJA',161),\r\n" + 
			"(1602,'TIRAPATA',161),\r\n" + 
			"(1603,'AJOYANI',162),\r\n" + 
			"(1604,'AYAPATA',162),\r\n" + 
			"(1605,'COASA',162),\r\n" + 
			"(1606,'CORANI',162),\r\n" + 
			"(1607,'CRUCERO',162),\r\n" + 
			"(1608,'ITUATA',162),\r\n" + 
			"(1609,'MACUSANI',162),\r\n" + 
			"(1610,'OLLACHEA',162),\r\n" + 
			"(1611,'SAN GABAN',162),\r\n" + 
			"(1612,'USICAYOS',162),\r\n" + 
			"(1613,'DESAGUADERO',163),\r\n" + 
			"(1614,'HUACULLANI',163),\r\n" + 
			"(1615,'JULI',163),\r\n" + 
			"(1616,'KELLUYO',163),\r\n" + 
			"(1617,'PISACOMA',163),\r\n" + 
			"(1618,'POMATA',163),\r\n" + 
			"(1619,'ZEPITA',163),\r\n" + 
			"(1620,'CAPASO',164),\r\n" + 
			"(1621,'CONDURIRI',164),\r\n" + 
			"(1622,'ILAVE',164),\r\n" + 
			"(1623,'PILCUYO',164),\r\n" + 
			"(1624,'SANTA ROSA',164),\r\n" + 
			"(1625,'COJATA',165),\r\n" + 
			"(1626,'HUANCANE',165),\r\n" + 
			"(1627,'HUATASANI',165),\r\n" + 
			"(1628,'INCHUPALLA',165),\r\n" + 
			"(1629,'PUSI',165),\r\n" + 
			"(1630,'ROSASPATA',165),\r\n" + 
			"(1631,'TARACO',165),\r\n" + 
			"(1632,'VILQUE CHICO',165),\r\n" + 
			"(1633,'CABANILLA',166),\r\n" + 
			"(1634,'CALAPUJA',166),\r\n" + 
			"(1635,'LAMPA',166),\r\n" + 
			"(1636,'NICASIO',166),\r\n" + 
			"(1637,'OCUVIRI',166),\r\n" + 
			"(1638,'PALCA',166),\r\n" + 
			"(1639,'PARATIA',166),\r\n" + 
			"(1640,'PUCARA',166),\r\n" + 
			"(1641,'SANTA LUCIA',166),\r\n" + 
			"(1642,'VILAVILA',166),\r\n" + 
			"(1643,'ANTAUTA',167),\r\n" + 
			"(1644,'AYAVIRI',167),\r\n" + 
			"(1645,'CUPI',167),\r\n" + 
			"(1646,'LLALLI',167),\r\n" + 
			"(1647,'MACARI',167),\r\n" + 
			"(1648,'NUNOA',167),\r\n" + 
			"(1649,'ORURILLO',167),\r\n" + 
			"(1650,'SANTA ROSA',167),\r\n" + 
			"(1651,'UMACHIRI',167),\r\n" + 
			"(1652,'CONIMA',168),\r\n" + 
			"(1653,'HUAYRAPATA',168),\r\n" + 
			"(1654,'MOHO',168),\r\n" + 
			"(1655,'TILALI',168),\r\n" + 
			"(1656,'ACORA',169),\r\n" + 
			"(1657,'AMANTANI',169),\r\n" + 
			"(1658,'ATUNCOLLA',169),\r\n" + 
			"(1659,'CAPACHICA',169),\r\n" + 
			"(1660,'CHUCUITO',169),\r\n" + 
			"(1661,'COATA',169),\r\n" + 
			"(1662,'HUATA',169),\r\n" + 
			"(1663,'MANAZO',169),\r\n" + 
			"(1664,'PAUCARCOLLA',169),\r\n" + 
			"(1665,'PICHACANI',169),\r\n" + 
			"(1666,'PLATERIA',169),\r\n" + 
			"(1667,'PUNO',169),\r\n" + 
			"(1668,'SAN ANTONIO',169),\r\n" + 
			"(1669,'TIQUILLACA',169),\r\n" + 
			"(1670,'VILQUE',169),\r\n" + 
			"(1671,'ANANEA',170),\r\n" + 
			"(1672,'PEDRO VILCA APAZA',170),\r\n" + 
			"(1673,'PUTINA',170),\r\n" + 
			"(1674,'QUILCA PUNCU',170),\r\n" + 
			"(1675,'SINA',170),\r\n" + 
			"(1676,'CABANA',171),\r\n" + 
			"(1677,'CABANILLAS',171),\r\n" + 
			"(1678,'CARACOTO',171),\r\n" + 
			"(1679,'JULIACA',171),\r\n" + 
			"(1680,'ALTO INAMBARI',172),\r\n" + 
			"(1681,'CUYOCUYO',172),\r\n" + 
			"(1682,'LIMBANI',172),\r\n" + 
			"(1683,'PATAMBUCO',172),\r\n" + 
			"(1684,'PHARA',172),\r\n" + 
			"(1685,'QUIACA',172),\r\n" + 
			"(1686,'SAN JUAN DEL ORO',172),\r\n" + 
			"(1687,'SAN PEDRO DE PUTINA PUNCO',172),\r\n" + 
			"(1688,'SANDIA',172),\r\n" + 
			"(1689,'YANAHUAYA',172),\r\n" + 
			"(1690,'ANAPIA',173),\r\n" + 
			"(1691,'COPANI',173),\r\n" + 
			"(1692,'CUTURAPI',173),\r\n" + 
			"(1693,'OLLARAYA',173),\r\n" + 
			"(1694,'TINICACHI',173),\r\n" + 
			"(1695,'UNICACHI',173),\r\n" + 
			"(1696,'YUNGUYO',173),\r\n" + 
			"(1697,'ALTO BIAVO',174),\r\n" + 
			"(1698,'BAJO BIAVO',174),\r\n" + 
			"(1699,'BELLAVISTA',174),\r\n" + 
			"(1700,'HUALLAGA',174),\r\n" + 
			"(1701,'SAN PABLO',174),\r\n" + 
			"(1702,'SAN RAFAEL',174),\r\n" + 
			"(1703,'AGUA BLANCA',175),\r\n" + 
			"(1704,'SAN JOSE DE SISA',175),\r\n" + 
			"(1705,'SAN MARTIN',175),\r\n" + 
			"(1706,'SANTA ROSA',175),\r\n" + 
			"(1707,'SHATOJA',175),\r\n" + 
			"(1708,'ALTO SAPOSOA',176),\r\n" + 
			"(1709,'EL ESLABON',176),\r\n" + 
			"(1710,'PISCOYACU',176),\r\n" + 
			"(1711,'SACANCHE',176),\r\n" + 
			"(1712,'SAPOSOA',176),\r\n" + 
			"(1713,'TINGO DE SAPOSOA',176),\r\n" + 
			"(1714,'ALONSO DE ALVARADO',177),\r\n" + 
			"(1715,'BARRANQUITA',177),\r\n" + 
			"(1716,'CAYNARACHI',177),\r\n" + 
			"(1717,'CUNUMBUQUI',177),\r\n" + 
			"(1718,'LAMAS',177),\r\n" + 
			"(1719,'PINTO RECODO',177),\r\n" + 
			"(1720,'RUMISAPA',177),\r\n" + 
			"(1721,'SAN ROQUE DE CUMBAZA',177),\r\n" + 
			"(1722,'SHANAO',177),\r\n" + 
			"(1723,'TABALOSOS',177),\r\n" + 
			"(1724,'ZAPATERO',177),\r\n" + 
			"(1725,'CAMPANILLA',178),\r\n" + 
			"(1726,'HUICUNGO',178),\r\n" + 
			"(1727,'JUANJUI',178),\r\n" + 
			"(1728,'PACHIZA',178),\r\n" + 
			"(1729,'PAJARILLO',178),\r\n" + 
			"(1730,'CALZADA',179),\r\n" + 
			"(1731,'HABANA',179),\r\n" + 
			"(1732,'JEPELACIO',179),\r\n" + 
			"(1733,'MOYOBAMBA',179),\r\n" + 
			"(1734,'SORITOR',179),\r\n" + 
			"(1735,'YANTALO',179),\r\n" + 
			"(1736,'BUENOS AIRES',180),\r\n" + 
			"(1737,'CASPIZAPA',180),\r\n" + 
			"(1738,'PICOTA',180),\r\n" + 
			"(1739,'PILLUANA',180),\r\n" + 
			"(1740,'PUCACACA',180),\r\n" + 
			"(1741,'SAN CRISTOBAL',180),\r\n" + 
			"(1742,'SAN HILARION',180),\r\n" + 
			"(1743,'SHAMBOYACU',180),\r\n" + 
			"(1744,'TINGO DE PONASA',180),\r\n" + 
			"(1745,'TRES UNIDOS',180),\r\n" + 
			"(1746,'AWAJUN',181),\r\n" + 
			"(1747,'ELIAS SOPLIN',181),\r\n" + 
			"(1748,'NUEVA CAJAMARCA',181),\r\n" + 
			"(1749,'PARDO MIGUEL',181),\r\n" + 
			"(1750,'POSIC',181),\r\n" + 
			"(1751,'RIOJA',181),\r\n" + 
			"(1752,'SAN FERNANDO',181),\r\n" + 
			"(1753,'YORONGOS',181),\r\n" + 
			"(1754,'YURACYACU',181),\r\n" + 
			"(1755,'ALBERTO LEVEAU',182),\r\n" + 
			"(1756,'CACATACHI',182),\r\n" + 
			"(1757,'CHAZUTA',182),\r\n" + 
			"(1758,'CHIPURANA',182),\r\n" + 
			"(1759,'EL PORVENIR',182),\r\n" + 
			"(1760,'HUIMBAYOC',182),\r\n" + 
			"(1761,'JUAN GUERRA',182),\r\n" + 
			"(1762,'LA BANDA DE SHILCAYO',182),\r\n" + 
			"(1763,'MORALES',182),\r\n" + 
			"(1764,'PAPAPLAYA',182),\r\n" + 
			"(1765,'SAN ANTONIO',182),\r\n" + 
			"(1766,'SAUCE',182),\r\n" + 
			"(1767,'SHAPAJA',182),\r\n" + 
			"(1768,'TARAPOTO',182),\r\n" + 
			"(1769,'NUEVO PROGRESO',183),\r\n" + 
			"(1770,'POLVORA',183),\r\n" + 
			"(1771,'SHUNTE',183),\r\n" + 
			"(1772,'TOCACHE',183),\r\n" + 
			"(1773,'UCHIZA',183),\r\n" + 
			"(1774,'CAIRANI',184),\r\n" + 
			"(1775,'CAMILACA',184),\r\n" + 
			"(1776,'CANDARAVE',184),\r\n" + 
			"(1777,'CURIBAYA',184),\r\n" + 
			"(1778,'HUANUARA',184),\r\n" + 
			"(1779,'QUILAHUANI',184),\r\n" + 
			"(1780,'ILABAYA',185),\r\n" + 
			"(1781,'ITE',185),\r\n" + 
			"(1782,'LOCUMBA',185),\r\n" + 
			"(1783,'ALTO DE LA ALIANZA',186),\r\n" + 
			"(1784,'CALANA',186),\r\n" + 
			"(1785,'CIUDAD NUEVA',186),\r\n" + 
			"(1786,'CORONEL GREGORIO ALBARRACIN L.ALFONSO UGARTE',186),\r\n" + 
			"(1787,'INCLAN',186),\r\n" + 
			"(1788,'PACHIA',186),\r\n" + 
			"(1789,'PALCA',186),\r\n" + 
			"(1790,'POCOLLAY',186),\r\n" + 
			"(1791,'SAMA',186),\r\n" + 
			"(1792,'TACNA',186),\r\n" + 
			"(1793,'CHUCATAMANI',187),\r\n" + 
			"(1794,'ESTIQUE',187),\r\n" + 
			"(1795,'ESTIQUE PAMPA',187),\r\n" + 
			"(1796,'SITAJARA',187),\r\n" + 
			"(1797,'SUSAPAYA',187),\r\n" + 
			"(1798,'TARATA',187),\r\n" + 
			"(1799,'TARUCACHI',187),\r\n" + 
			"(1800,'TICACO',187),\r\n" + 
			"(1801,'CANOAS DE PUNTA SAL',188),\r\n" + 
			"(1802,'CASITAS',188),\r\n" + 
			"(1803,'ZORRITOS',188),\r\n" + 
			"(1804,'CORRALES',189),\r\n" + 
			"(1805,'LA CRUZ',189),\r\n" + 
			"(1806,'PAMPAS DE HOSPITAL',189),\r\n" + 
			"(1807,'SAN JACINTO',189),\r\n" + 
			"(1808,'SAN JUAN DE LA VIRGEN',189),\r\n" + 
			"(1809,'TUMBES',189),\r\n" + 
			"(1810,'AGUAS VERDES',190),\r\n" + 
			"(1811,'MATAPALO',190),\r\n" + 
			"(1812,'PAPAYAL',190),\r\n" + 
			"(1813,'ZARUMILLA',190),\r\n" + 
			"(1814,'RAIMONDI',191),\r\n" + 
			"(1815,'SEPAHUA',191),\r\n" + 
			"(1816,'TAHUANIA',191),\r\n" + 
			"(1817,'YURUA',191),\r\n" + 
			"(1818,'CALLERIA',192),\r\n" + 
			"(1819,'CAMPOVERDE',192),\r\n" + 
			"(1820,'IPARIA',192),\r\n" + 
			"(1821,'MANANTAY',192),\r\n" + 
			"(1822,'MASISEA',192),\r\n" + 
			"(1823,'NUEVA REQUENA',192),\r\n" + 
			"(1824,'YARINACOCHA',192),\r\n" + 
			"(1825,'CURIMANA',193),\r\n" + 
			"(1826,'PADRE ABAD',193),\r\n" + 
			"(1827,'YRAZOLA',193),\r\n" + 
			"(1828,'PURUS',194),\r\n" + 
			"(1829,'BELLAVISTA',195),\r\n" + 
			"(1830,'CALLAO',195),\r\n" + 
			"(1831,'CARMEN DE LA LEGUA-REYNOSO',195),\r\n" + 
			"(1832,'LA PERLA',195),\r\n" + 
			"(1833,'LA PUNTA',195),\r\n" + 
			"(1834,'VENTANILLA',195);", nativeQuery = true)
	void insertarDistrito();
	
}
