insert into categoria(codigo, nombre) values (1, "Tecnologia");
insert into categoria(codigo, nombre) values (2, "Deporte");
insert into categoria(codigo, nombre) values (3, "Ropa");
insert into ciudad(codigo_ciudad, nombre_ciudad) values(1, "London");
insert into ciudad(codigo_ciudad, nombre_ciudad) values(2, "Milan");
insert into ciudad(codigo_ciudad, nombre_ciudad) values(3, "Rennes");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(1, "laura@hotmail", "laura", "laura1", 1);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (1, "3133333331", "celular");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(2, "gabriela@hotmail", "gabriela", "gabriela1", 2);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (2, "3133333332", "celular");
insert into usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values(3, "daniela@hotmail", "daniela", "daniela1", 3);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (3, "3133333333", "celular");
insert into Usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values (4, "luisa@123", "luisa", "luisa123", 1);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (4, "3133333334", "celular");
insert into Usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values (5, "angie@123", "angie", "angie123", 2);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (5, "3133333335", "celular");
insert into Usuario(codigo, email, nombre, password, ciudad_codigo_ciudad) values (6, "vanessa@123", "vanessa", "vanessa123", 3);
insert into usuario_telefonos(usuario_codigo, telefonos, telefonos_key) values (6, "3133333336", "celular");
insert into producto(codigo_producto, cantidad, descripcion, descuento, fecha, nombre, precio, ciudad_codigo_ciudad, vendedor_codigo) values (1, 20, "Televisor 45 pulgadas", 0, null, "televisor", 1000000, 1, 1);
insert into producto(codigo_producto, cantidad, descripcion, descuento, fecha, nombre, precio, ciudad_codigo_ciudad, vendedor_codigo) values (2, 20, "papa phone 7", 0, null, "celular", 1000000, 2, 2);
insert into producto(codigo_producto, cantidad, descripcion, descuento, fecha, nombre, precio, ciudad_codigo_ciudad, vendedor_codigo) values (3, 20, "2x1.5", 0, null, "cama", 700000, 3, 3);
insert into producto_categorias_producto(lista_productos_codigo_producto, categorias_producto_codigo) values (1, 1);
insert into producto_categorias_producto(lista_productos_codigo_producto, categorias_producto_codigo) values (2, 3);
insert into producto_categorias_producto(lista_productos_codigo_producto, categorias_producto_codigo) values (3, 3);
insert into comentario(codigo, calificacion, fecha, mensaje, respuesta, producto_codigo_producto, usuario_codigo) values(1, 4, null, "Buen producto pero se demoro en llegar", null, 1, 4);
insert into comentario(codigo, calificacion, fecha, mensaje, respuesta, producto_codigo_producto, usuario_codigo) values(2, 0, null, "Que mal producto, se quemo a la semana", null, 2, 5);
insert into comentario(codigo, calificacion, fecha, mensaje, respuesta, producto_codigo_producto, usuario_codigo) values(3, 5, null, "Es asombrosa, llevo 6 meses con ella", null, 3, 6);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (1, null, "paypal", 4);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (2, null, "efectivo", 5);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (3, null, "contraentrega", 6);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(1, 1000000, 3, 1, 1);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(2, 1000000, 8, 2, 2);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(3, 700000, 10, 3, 3);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (4, null, "paypal", 6);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (5, null, "efectivo", 5);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (6, null, "contraentrega", 4);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(4, 1000000, 5, 4, 1);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(5, 1000000, 3, 5, 2);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(6, 700000, 3, 6, 3);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (7, null, "paypal", 5);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (8, null, "efectivo", 6);
insert into compra(codigo, fecha, mediodpago, codigo_usuario_codigo) values (9, null, "contraentrega", 4);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(7, 1000000, 6, 7, 1);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(8, 1000000, 5, 8, 2);
insert into detalle_compra(codigo, precio, unidades, codigo_compra_codigo, producto_comprar_codigo_producto) values(9, 700000, 5, 9, 3);
insert into subasta(codigo, fecha_limite, producto_codigo_producto) values(1, null, 1);
insert into subasta(codigo, fecha_limite, producto_codigo_producto) values(2, null, 2);
insert into subasta(codigo, fecha_limite, producto_codigo_producto) values(3, null, 3);
insert into subasta_usuario(codigo, fecha, valor, subasta_codigo, usuario_codigo) values(1, null, 400000, 1, 4);
insert into subasta_usuario(codigo, fecha, valor, subasta_codigo, usuario_codigo) values(2, null, 600000, 2, 5);
insert into subasta_usuario(codigo, fecha, valor, subasta_codigo, usuario_codigo) values(3, null, 700000, 3, 6);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (1, 1, 4);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (2, 2, 5);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (3, 3, 6);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(1, 4, null, "Hola", 1);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(2, 5, null, "Hola1", 2);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(3, 6, null, "Hola2", 3);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (4, 1, 4);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (5, 2, 5);
insert into chat(codigo_chat, producto_comprar_codigo_producto, usuario_comprador_codigo) values (6, 3, 6);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(4, 4, null, "Hola3", 1);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(5, 5, null, "Hola4", 2);
insert into mensaje(codigo_mensaje, emisor, fecha, mensaje, codigo_chat_codigo_chat) values(6, 6, null, "Hola5", 3);