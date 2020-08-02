package com.back.alquiler.utils;

public class WordConstante {
	
	public WordData titulo() {
		WordData titulo = new WordData();
		titulo.setText1("CONTRATO DE ARRENDAMIENTO");
		return titulo;
	}
	
	public WordData parrafo1() {
		WordData parrafo = new WordData();
		parrafo.setText1("Conste por el presente contrato que celebran de una parte como ");
		parrafo.setText2("ARRENDADOR(A) ");
		parrafo.setText3("el(la) Señor(a) $arrendero_nombres$ $arrendero_apellidos$ identificado(a) con ");
		parrafo.setText4("DNI N° $dni_arrendero$ ");
		parrafo.setText5(",con domicilio en la $direccion_arrendero$; y de la otra parte como ");
		parrafo.setText6("EL(LA) ARRENDATARIO(A) ");
		parrafo.setText7("el señor(a) $arrendatario_nombre$ $arrendatario_apellidos$. Identificado(a) con DNI N° "
				+ "$dni_arrendatario$  y domiciliado(a) en $direccion_arrendatario$; quienes convienen de mutuo " + 
				"acuerdo y regulado por las leyes vigentes sobre la materia, en los términos y condiciones siguientes:");
		return parrafo;
	}
	
	public WordData subtitulo_antecedente() {
		WordData titulo = new WordData();
		titulo.setText1("ANTECEDENTES:");
		return titulo;
	}
	
	public WordData parrafo2() {
		WordData parrafo = new WordData();
		parrafo.setText1("PRIMERA. - EL(LA) ARRENDADOR(A) ");
		parrafo.setText2("es propietario(a) del inmueble ubicado en $direccion_propiedad$"
				+ ", el mismo que se encuentra inscrito en la Partida N° $nro_partida_registral$,"
				+ "del Registro de la Propiedad Inmueble, cuya área, linderos y medidas perimétricas "
				+ "se hallan consignadas en el referido documento registral.");
		return parrafo;
	}
	
	public WordData parrafo3() {
		WordData parrafo = new WordData();
		parrafo.setText1("SEGUNDA. - EL(LA) ARRENDADOR(A) ");
		parrafo.setText2("deja constancia que el inmueble al que se refiere la cláusula "
				+ "anterior se encuentra desocupado, en buen estado de conservación y habitabilidad, "
				+ "y sin mayor desgaste que el producido por el uso normal y ordinario");
		return parrafo;
	}
	
	public WordData subtitulo_obj_contrato() {
		WordData parrafo = new WordData();
		parrafo.setText1("OBJETO DEL CONTRATO:");
		return parrafo;
	}
	
	public WordData parrafo4() {
		WordData parrafo = new WordData();
		parrafo.setText1("TERCERA. - ");
		parrafo.setText2("Mediante el presente contrato ");
		parrafo.setText3("EL(LA) ARRENDADOR(A) ");
		parrafo.setText4("da en alquiler al ");
		parrafo.setText5("ARRENDATARIO(A), ");
		parrafo.setText6(" el inmueble descrito en la cláusula primera para destinarlo únicamente a " + 
				"vivienda, el cual es recibido en perfecto estado de operatividad, conforme a lo señalado en " + 
				"la cláusula segunda. Por su parte, el ");
		parrafo.setText7("ARRENDATARIO(A) ");
		parrafo.setText8("se obliga a pagar a ");
		parrafo.setText9("EL(LA) ARRENDADOR(A) ");
		parrafo.setText10("el monto de la renta pactada en la cláusula siguiente, en la forma y oportunidad convenidas.");
		return parrafo;
	}
	
	public WordData subitulo_forma_oportunidad() {
		WordData parrafo = new WordData();
		parrafo.setText1("RENTA: FORMA Y OPORTUNIDAD DE PAGO:");
		return parrafo;
	}
	
	public WordData parrafo5() {
		WordData parrafo = new WordData();
		parrafo.setText1("CUARTA. - ");
		parrafo.setText2("Las partes acuerdan que el monto de la renta que pagará ");
		parrafo.setText3("EL(LA) ARRENDATARIO(A), ");
		parrafo.setText4("en calidad de contraprestación por el alquiler del inmueble, asciende a la suma de S/."
				+ "$monto_renta$ ($monto_renta_letras$), mensuales, cantidad que será cancelada en dinero, en la forma "
				+ "y oportunidad a que se refiere la cláusula siguiente.");
		return parrafo;
	}
	
	public WordData parrafo6() {
		WordData parrafo = new WordData();
		parrafo.setText1("A la firma del presente contrato ");
		parrafo.setText2("EL(LA) ARRENDATARIO(A), ");
		parrafo.setText3("entrega al ");
		parrafo.setText4("ARRENDADOR(A) ");
		parrafo.setText5("la suma de S/.$monto_garantia$ ($monto_garantia_letras$) por concepto de garantía del pago, "
				+ "los cuales no generaran intereses ni rentas y serán devueltas a la entrega del bien, "
				+ "previa verificación del cumplimiento de todos los pagos a los que está obligado ");
		parrafo.setText6("el(la) ARRENDATARIO(A).");
		return parrafo;
	}
	
	public WordData parrafo7() {
		WordData parrafo = new WordData();
		parrafo.setText1("QUINTA. - ");
		parrafo.setText2("La forma de pago de la renta será por mensualidades adelantadas que el(la) ");
		parrafo.setText3("ARRENDATARIO(A) ");
		parrafo.setText4("pagará en el domicilio de ");
		parrafo.setText5("EL(LA) ARRENDADOR(A) ");
		parrafo.setText6("el $dia_pago_mes$ día útil de cada mes.");
		return parrafo;
	}
	
	public WordData subtitulo_plazo_contrato() {
		WordData parrafo = new WordData();
		parrafo.setText1("PLAZO DEL CONTRATO: ");
		return parrafo;
	}
	
	public WordData parrafo8() {
		WordData parrafo = new WordData();
		parrafo.setText1("SEXTA. - ");
		parrafo.setText2("El plazo del presente contrato es de $tiempo_contrato$ meses, el cual regirá a partir del "
				+ "$fecha_contrato_inicio$ hasta el $fecha_contrato_fin$, a cuyo vencimiento podrá renovarse "
				+ "con el acuerdo de ambas partes, pudiendo variar las condiciones establecidas en el presente "
				+ "contrato, en cuanto al plazo, monto de la renta y uso del bien.");
		return parrafo;
	}
	
	public WordData subtitulo_obligaciones() {
		WordData parrafo = new WordData();
		parrafo.setText1("OBLIGACIONES DE LAS PARTES: ");
		return parrafo;
	}
	
	public WordData parrafo9() {
		WordData parrafo = new WordData();
		parrafo.setText1("SÉTIMA. - EL(LA) ARRENDADOR(A) ");
		parrafo.setText2("se obliga a entregar el bien objeto de la prestación a su cargo " + 
				"en la fecha establecida en el presente contrato, la misma que se verificará con la entrega de " + 
				"las llaves");
		return parrafo;
	}
	
	public WordData parrafo10() {
		WordData parrafo = new WordData();
		parrafo.setText1("OCTAVA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("se obliga a pagar puntualmente el monto de la renta, en la " + 
				"forma, oportunidad y lugar pactados, con sujeción a lo convenido en las cláusulas cuarta y " + 
				"quinta.");
		return parrafo;
	}
	
	public WordData parrafo11() {
		WordData parrafo = new WordData();
		parrafo.setText1("NOVENA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("está obligado a permitir la inspección del bien arrendado por parte del ");
		parrafo.setText3("ARRENDADOR(A), ");
		parrafo.setText4("para cuyo efecto éste deberá cursar previo aviso por escrito, con una anticipación no menor de dos días.");
		return parrafo;
	}
	
	public WordData parrafo12_reparacion_arrendatario() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("está obligado a efectuar por cuenta y costo " + 
				"propio las reparaciones y mantenimientos que sean necesarios para conservar el bien en el " + 
				"mismo estado en que fue recibido.");
		return parrafo;
	}
	
	public WordData parrafo12_reparacion_arrendero() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("no está obligado a efectuar por cuenta y costo " + 
				"propio las reparaciones y mantenimientos que sean necesarios para conservar el bien en el " + 
				"mismo estado en que fue recibido.");
		return parrafo;
	}
	
	public WordData parrafo13() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMO PRIMERA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("no podrá ceder a terceros el bien materia del presente contrato bojo "
				+ "ningún título, ni subarrendarlo, total o parcialmente, ni ceder su "
				+ "posición contractual, salvo que cuente con el consentimiento expreso y por escrito de ");
		parrafo.setText3("EL(LA) ARRENDADOR(A) ");
		parrafo.setText4("en cuyo caso se suscribirán los documentos que fueren necesarios.");
		return parrafo;
	}
	
	public WordData parrafo14() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMO SEGUNDA. - EL(LA) ARRENDATARIO(A) ");
		parrafo.setText2("se obliga a desocupar el bien arrendado en la " + 
				"fecha de vencimiento del plazo estipulado en la cláusula sexta de este contrato, salvo " + 
				"renovación del mismo.");
		return parrafo;
	}
	
	public WordData subtitulo_clausula_pena() {
		WordData parrafo = new WordData();
		parrafo.setText1("CLAUSULA PENAL:");
		return parrafo;
	}
	
	public WordData parrafo15() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMO TERCERA. - ");
		parrafo.setText2("En caso de incumplimiento de lo estipulado en la cláusula décimo segunda, ");
		parrafo.setText3("EL(LA) ARRENDATARIO(A) ");
		parrafo.setText4("deberá pagar en calidad de penalidad compensatorio un " + 
				"importe ascendente a S/. $penalidad$ ($penalidad_letras$), por cada día de " + 
				"demora en la entrega del bien.");
		return parrafo;
	}
	
	public WordData subtitulo_clausula_garantia() {
		WordData parrafo = new WordData();
		parrafo.setText1("CLAUSULA DE GARANTÍA:");
		return parrafo;
	}
	
	public WordData parrafo16() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMO CUARTA. - ");
		parrafo.setText2("El mencionado depósito en garantía le será devuelto al ");
		parrafo.setText3("ARRENDATARIO(A) ");
		parrafo.setText4("sin intereses o rentas, al vencimiento del presente contrato, siempre que no haya sido renovado, y una vez " + 
				"verificado el estado de conservación y funcionamiento del bien arrendado. Las partes dejan establecido que el "
				+ "depósito en garantía, no podrá ser destinado a cubrir el pago de la renta de ningún período.");
		return parrafo;
	}
	
	public WordData subtitulo_clausula_solucion_conflicto() {
		WordData parrafo = new WordData();
		parrafo.setText1("CLAUSULA DE SOLUCION DE CONFLICTOS:");
		return parrafo;
	}
	
	public WordData parrafo17() {
		WordData parrafo = new WordData();
		parrafo.setText1("DECIMO QUINTA. - ");
		parrafo.setText2("El mencionado depósito en garantía le será devuelto al ");
		parrafo.setText3("ARRENDATARIO(A) ");
		parrafo.setText4("sin intereses o rentas, al vencimiento del presente contrato, siempre que no haya sido renovado, y una vez " + 
				"verificado el estado de conservación y funcionamiento del bien arrendado. Las partes dejan establecido que el "
				+ "depósito en garantía, no podrá ser destinado a cubrir el pago de la renta de ningún período.");
		return parrafo;
	}
	
	public WordData firma() {
		WordData footer = new WordData();
		footer.setText1("ARRENDADOR                                         ARRENDATARIO");
		return footer;
	}
}
