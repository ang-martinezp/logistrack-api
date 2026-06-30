# Proyecto Semestral: LogisTrack API (Arquitectura de Microservicios)

Este repositorio contiene la **Evaluación Parcial 3** para la asignatura **Desarrollo FullStack 1 (DSY1103)**. 

El proyecto **LogisTrack** es una solución robusta para la gestión logística, control de inventarios, despachos, proveedores y pedidos en una cadena de suministro, estructurada bajo una arquitectura de microservicios distribuida con base de datos descentralizada.

---

## Información del Equipo

*   **Estudiantes:** Alfredo De La Hoz, Angel Martinez

---

## 1. Listado de Microservicios Implementados
El ecosistema consta de **10 microservicios independientes** organizados bajo el patrón de diseño **CSR (Controller-Service-Repository)**, un servidor de descubrimiento y un API Gateway centralizado:

1.  **`auth-service` (Puerto 8081):** Servicio encargado de validar credenciales en base de datos y generar tokens JWT para los clientes autorizados.
2.  **`user-service` (Puerto 8082):** Servicio de gestión y persistencia de perfiles y usuarios.
3.  **`product-service` (Puerto 8083):** Gestión del catálogo de productos, unidades de medida y categorías de inventario.
4.  **`inventory-service` (Puerto 8084):** Control de stock físico en almacenes, registro de movimientos y emisión de alertas de stock mínimo.
5.  **`order-service` (Puerto 8085):** Gestión de compras y generación de pedidos dirigidos a proveedores.
6.  **`dispatch-service` (Puerto 8086):** Coordinación y seguimiento de despachos de mercancía e integración de transportistas.
7.  **`receiving-service` (Puerto 8087):** Registro de recepciones físicas de productos y control de discrepancias en almacén.
8.  **`supplier_service` (Puerto 8088):** Directorio y gestión de proveedores autorizados.
9.  **`notification-service` (Puerto 8089):** Envío de alertas y eventos del sistema.
10. **`report-service` (Puerto 8090):** Generación consolidada de reportes operativos y analíticos.

### Componentes de Infraestructura:
*   **`eureka-server` (Puerto 8761):** Servidor de descubrimiento de servicios para registro y localización dinámica de instancias.
*   **`api-gateway` (Puerto 8080):** Puerta de entrada única y perimetral que enruta las solicitudes hacia los microservicios correctos mediante balanceo de carga (`lb://`) y aplica un filtro de seguridad JWT global (`JwtFilter`).

---

## 2. Comunicación entre Microservicios (OpenFeign)
La interoperabilidad e intercambio de datos entre microservicios se realiza de forma síncrona mediante **Spring Cloud OpenFeign**:
*   `dispatch-service` consume datos de `order-service` y `product-service` para enriquecer la información de despacho.
*   `inventory-service` consume datos de `product-service` para mapear los productos correspondientes al stock físico.
*   `order-service` consume información de `product-service` y `supplier-service` al crear órdenes de compra.
*   `receiving-service` interactúa con `order-service`, `product-service` y `supplier-service` para validar recepciones contra órdenes de compra físicas.

---

## 3. Seguridad Centralizada (API Gateway + JWT)
*   El **API Gateway** intercepta todas las solicitudes mediante un `JwtFilter`.
*   El endpoint de inicio de sesión (`POST /api/auth/login`) y registro (`POST /api/auth/registro`) son públicos.
*   Cualquier otra ruta requiere la cabecera `Authorization: Bearer <TOKEN>`. De lo contrario, el Gateway responde con un estado `401 Unauthorized` previniendo accesos indebidos.

---

## 4. Rutas Principales del Gateway (Puerto 8080)

Todas las peticiones deben dirigirse al Gateway:
*   **Autenticación:** `/api/auth/**` -> Redirige a `auth-service`
*   **Productos, Categorías y Unidades:** `/api/productos/**`, `/api/categorias/**`, `/api/unidades/**` -> Redirige a `product-service`
*   **Stock, Movimientos y Alertas:** `/api/stock/**`, `/api/movimientos/**`, `/api/alertas/**` -> Redirige a `inventory-service`
*   **Pedidos/Órdenes:** `/api/pedidos/**` -> Redirige a `order-service`
*   **Despachos:** `/api/despachos/**` -> Redirige a `dispatch-service`
*   **Recepciones:** `/api/recepciones/**` -> Redirige a `receiving-service`
*   **Proveedores:** `/api/proveedores/**` -> Redirige a `supplier_service`
*   **Usuarios:** `/api/usuarios/**` -> Redirige a `user-service`
*   **Notificaciones:** `/api/notificaciones/**` -> Redirige a `notification-service`
*   **Reportes:** `/api/reportes/**` -> Redirige a `report-service`

---

## 5. Pruebas Unitarias (JUnit 5 + Mockito + MockMvc)
Se desarrollaron pruebas unitarias completas bajo el formato **Given-When-Then** para validar:
*   Lógica del servicio de login y creación de usuarios (`AuthServiceTest`).
*   Códigos de respuesta HTTP y filtros de validación de esquemas JSON (`AuthControllerTest`).

### Comando para ejecutar las pruebas unitarias:
Desde el directorio del microservicio correspondiente (`auth-service`):
```bash
./mvnw.cmd clean test
```

---

## 6. Instrucciones de Ejecución Local

### Prerrequisitos:
*   Java JDK 21 instalado.
*   Base de datos MariaDB / MySQL activa en `localhost:3306` con las credenciales indicadas en el archivo `application.properties` de cada servicio.

### Orden de Encendido de los Microservicios:
1.  **`eureka-server`**: Compilar y ejecutar. Esperar a que la consola muestre que está listo.
2.  **`api-gateway`**: Compilar y ejecutar.
3.  **`auth-service`** y los demás microservicios de negocio.
4.  Comprobar el registro de los microservicios en el panel de Eureka: [http://localhost:8761](http://localhost:8761).
