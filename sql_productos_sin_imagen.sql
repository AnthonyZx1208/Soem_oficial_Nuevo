-- SOEM Oficial | Alta manual de 50 productos sin imagen (10 por línea)
-- Categorías reales: 1=Hombres, 2=Mujeres, 3=Niñas, 4=Niños
-- Subcategorías reales: 1=Pantalones(Hombres), 2=Camisas(Hombres), 3=Chaquetas(Hombres)
-- imagen_principal queda en NULL a propósito: se completa luego desde /admin/productos
-- o con un UPDATE cuando se tengan las URLs/archivos definitivos.
USE soem_oficial;

INSERT INTO producto (nombre_producto, descripcion, precio_producto, precio_oferta, cantidad_stock, imagen_principal, Categoria_id_categoria, SubCategoria_id_subcategoria, estado) VALUES
-- PANTALONES (Hombres)
('Pantalón de vestir slim gris', 'Pantalón de vestir corte slim en gris, ideal para oficina.', 99900, NULL, 30, NULL, 1, 1, 'Activo'),
('Pantalón cargo verde militar', 'Pantalón cargo con bolsillos laterales, tela resistente.', 109900, NULL, 25, NULL, 1, 1, 'Activo'),
('Pantalón chino beige', 'Pantalón chino clásico en beige, versátil para el día a día.', 89900, NULL, 35, NULL, 1, 1, 'Activo'),
('Pantalón jogger negro', 'Jogger negro con puños ajustados y bolsillos funcionales.', 79900, NULL, 40, NULL, 1, 1, 'Activo'),
('Pantalón de dril azul marino', 'Pantalón de dril azul marino, corte recto clásico.', 94900, NULL, 28, NULL, 1, 1, 'Activo'),
('Pantalón de vestir a cuadros', 'Pantalón de vestir con estampado de cuadros sutil.', 104900, NULL, 20, NULL, 1, 1, 'Activo'),
('Pantalón cargo negro', 'Pantalón cargo negro multibolsillo, estilo urbano.', 109900, NULL, 22, NULL, 1, 1, 'Activo'),
('Pantalón recto café', 'Pantalón recto en café, tela suave y cómoda.', 89900, NULL, 30, NULL, 1, 1, 'Activo'),
('Pantalón deportivo gris jaspe', 'Pantalón deportivo jaspeado, ideal para uso casual.', 74900, NULL, 45, NULL, 1, 1, 'Activo'),
('Pantalón de lino blanco', 'Pantalón de lino blanco, fresco y ligero para clima cálido.', 99900, NULL, 18, NULL, 1, 1, 'Activo'),
-- CAMISAS (Hombres)
('Camisa de lino blanca', 'Camisa de lino blanca, fresca y elegante.', 89900, NULL, 30, NULL, 1, 2, 'Activo'),
('Camisa a cuadros franela', 'Camisa de franela a cuadros, cálida y casual.', 79900, NULL, 35, NULL, 1, 2, 'Activo'),
('Camisa oxford celeste', 'Camisa oxford celeste de corte clásico.', 84900, NULL, 32, NULL, 1, 2, 'Activo'),
('Camisa negra de gala', 'Camisa negra de gala, ideal para ocasiones formales.', 99900, NULL, 20, NULL, 1, 2, 'Activo'),
('Camisa denim azul', 'Camisa denim azul, resistente y con estilo urbano.', 94900, NULL, 25, NULL, 1, 2, 'Activo'),
('Camisa estampada tropical', 'Camisa manga corta con estampado tropical.', 74900, NULL, 28, NULL, 1, 2, 'Activo'),
('Camisa de rayas azul y blanco', 'Camisa de rayas clásica azul y blanco.', 79900, NULL, 30, NULL, 1, 2, 'Activo'),
('Camisa manga larga gris', 'Camisa manga larga en gris, corte moderno.', 84900, NULL, 26, NULL, 1, 2, 'Activo'),
('Camisa casual verde oliva', 'Camisa casual en verde oliva, tela liviana.', 79900, NULL, 30, NULL, 1, 2, 'Activo'),
('Camisa de lino beige', 'Camisa de lino beige, cómoda para el diario.', 89900, NULL, 24, NULL, 1, 2, 'Activo'),
-- VESTIDOS (Mujeres)
('Vestido midi negro', 'Vestido midi negro de corte entallado.', 129900, NULL, 20, NULL, 2, NULL, 'Activo'),
('Vestido floral verano', 'Vestido floral ligero, perfecto para el verano.', 99900, NULL, 25, NULL, 2, NULL, 'Activo'),
('Vestido casual denim', 'Vestido casual en denim, estilo relajado.', 109900, NULL, 22, NULL, 2, NULL, 'Activo'),
('Vestido de fiesta dorado', 'Vestido de fiesta dorado con brillo sutil.', 159900, NULL, 12, NULL, 2, NULL, 'Activo'),
('Vestido camisero blanco', 'Vestido camisero blanco, versátil y elegante.', 94900, NULL, 28, NULL, 2, NULL, 'Activo'),
('Vestido largo estampado', 'Vestido largo con estampado floral, tela fluida.', 119900, NULL, 18, NULL, 2, NULL, 'Activo'),
('Vestido ajustado rojo', 'Vestido ajustado rojo, ideal para ocasiones especiales.', 109900, NULL, 15, NULL, 2, NULL, 'Activo'),
('Vestido de punto beige', 'Vestido de punto beige, cálido y cómodo.', 99900, NULL, 20, NULL, 2, NULL, 'Activo'),
('Vestido cruzado azul', 'Vestido cruzado azul, silueta favorecedora.', 104900, NULL, 18, NULL, 2, NULL, 'Activo'),
('Vestido corto negro', 'Vestido corto negro básico, para cualquier ocasión.', 89900, NULL, 30, NULL, 2, NULL, 'Activo'),
-- GABANES (Hombres, subcategoría Chaquetas)
('Gabán largo negro', 'Gabán largo negro de paño, corte elegante.', 219900, NULL, 12, NULL, 1, 3, 'Activo'),
('Gabán de paño gris', 'Gabán de paño gris, abrigo clásico de invierno.', 239900, NULL, 10, NULL, 1, 3, 'Activo'),
('Gabán camel clásico', 'Gabán camel de corte clásico y atemporal.', 249900, NULL, 8, NULL, 1, 3, 'Activo'),
('Gabán acolchado azul', 'Gabán acolchado azul, cálido y ligero.', 199900, NULL, 15, NULL, 1, 3, 'Activo'),
('Gabán oversize beige', 'Gabán oversize beige, tendencia streetwear.', 229900, NULL, 14, NULL, 1, 3, 'Activo'),
('Gabán cruzado negro', 'Gabán cruzado negro con cinturón.', 259900, NULL, 9, NULL, 1, 3, 'Activo'),
('Gabán de lana café', 'Gabán de lana café, abrigo premium de invierno.', 234900, NULL, 10, NULL, 1, 3, 'Activo'),
('Gabán deportivo verde militar', 'Gabán deportivo en verde militar, estilo urbano.', 209900, NULL, 16, NULL, 1, 3, 'Activo'),
('Gabán largo caqui', 'Gabán largo caqui, versátil para entretiempo.', 219900, NULL, 13, NULL, 1, 3, 'Activo'),
('Gabán con capucha gris oscuro', 'Gabán con capucha en gris oscuro, funcional y abrigado.', 229900, NULL, 11, NULL, 1, 3, 'Activo'),
-- CAMISAS DE NIÑOS (Niños)
('Camisa a cuadros niño', 'Camisa a cuadros para niño, tela suave.', 49900, NULL, 25, NULL, 4, NULL, 'Activo'),
('Camisa blanca formal niño', 'Camisa blanca formal para niño, ideal para eventos.', 44900, NULL, 22, NULL, 4, NULL, 'Activo'),
('Camisa manga corta estampada niño', 'Camisa manga corta con estampado divertido para niño.', 39900, NULL, 30, NULL, 4, NULL, 'Activo'),
('Camisa denim niño', 'Camisa denim resistente para niño.', 54900, NULL, 20, NULL, 4, NULL, 'Activo'),
('Camisa a rayas azul niño', 'Camisa a rayas azul y blanco para niño.', 44900, NULL, 24, NULL, 4, NULL, 'Activo'),
('Camisa polo niño', 'Camisa tipo polo para niño, cómoda y casual.', 39900, NULL, 35, NULL, 4, NULL, 'Activo'),
('Camisa safari niño', 'Camisa estilo safari para niño con bolsillos.', 49900, NULL, 18, NULL, 4, NULL, 'Activo'),
('Camisa de lino niño', 'Camisa de lino fresca para niño.', 44900, NULL, 20, NULL, 4, NULL, 'Activo'),
('Camisa gris casual niño', 'Camisa gris casual para niño, uso diario.', 39900, NULL, 28, NULL, 4, NULL, 'Activo'),
('Camisa dinosaurios niño', 'Camisa estampado de dinosaurios para niño.', 42900, NULL, 26, NULL, 4, NULL, 'Activo');
