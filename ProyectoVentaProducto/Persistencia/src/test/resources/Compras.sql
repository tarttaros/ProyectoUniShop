insert into ciudad(codigo_ciudad, nombre_ciudad) values(1, "Toscana");
insert into ciudad(codigo_ciudad, nombre_ciudad) values(2, "London");
insert into ciudad(codigo_ciudad, nombre_ciudad) values(3, "Rennes");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(1, "laura@hotmail", "laura", "laura1", 1);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (1, "3133333331", "celular");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(2, "gabriela@hotmail", "gabriela", "gabriela1", 2);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (2, "3133333332", "celular");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(3, "daniela@hotmail", "daniela", "daniela1", 3);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (3, "3133333333", "celular");
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (1, null, "paypal", 1);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (2, null, "efectivo", 2);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (3, null, "contraentrega", 3);