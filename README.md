# EVC Galatea – API Challenge

EVC Galatea es una API REST desarrollada en **Java + Spring Boot**, cuyo propósito es analizar manuscritos y determinar si contienen un **“clue”** (patrón determinado).  
Además, expone métricas de uso como:

- Cantidad de manuscritos con clue  
- Cantidad de manuscritos sin clue  
- Ratio (proporción de detecciones)

El proyecto incluye persistencia con **Spring Data JPA**, controlador REST, capa de servicio, algoritmo de detección y endpoints RESTful.

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven**
- **Docker (opcional)**
- **Render / Railway** (para despliegue)

---

## 📂 Arquitectura del proyecto

```
src/main/java/com/galatea/
│
├── controller/
│     └── ClueController.java
│
├── service/
│     └── ClueService.java
│
├── repository/
│     └── ClueRepository.java
│
├── entity/
│     └── ClueRecord.java
│
└── EvcGalateaApplication.java
```

---

## 🧠 Lógica principal: Detección de “clue”

El algoritmo revisa un manuscrito expresado como un arreglo de strings:

```json
{
  "manuscript": [
    "ABCDE",
    "FGHIJ",
    "ZZZZZ",
    "QWERT",
    "FGHIJ"
  ]
}
```

Para encontrar un clue, el sistema busca secuencias siguiendo patrones horizontales, verticales o diagonales.

Pasos del algoritmo:

1. Convierte el arreglo en matriz `char[][]`.
2. Valida que todas las filas tengan la misma longitud.
3. Recorre la matriz en 8 direcciones.
4. Si encuentra un patrón repetitivo → **es clue**.
5. Registra en la BD si hay o no clue.
6. Si el mismo manuscrito llega nuevamente, se usa el registro previo (idempotencia).

---

## 🗄 Base de datos

Se usa **H2 en memoria**.  
Se guarda:

- Resultado (clue o no clue)
- Hash único del manuscrito
- Fecha de análisis

---

## 📡 Endpoints

### **1️⃣ POST /clue**

Analiza si un manuscrito contiene clue.

Request:
```json
{
  "manuscript": ["ABCDE", "FGHIJ", "ZZZZZ", "QWERT", "FGHIJ"]
}
```

Responses:
- **200 OK** → `{ "clue": true }`
- **403 Forbidden** → `{ "clue": false }`

---

### **2️⃣ GET /stats**

Devuelve estadísticas globales:

```json
{
  "count_clue_found": 4,
  "count_no_clue": 6,
  "ratio": 0.4
}
```

---

## 🛠 Cómo ejecutar localmente

### 1. Clonar repo
```
git clone https://github.com/estebanhr28/evc-galatea
cd evc-galatea
```

### 2. Compilar
```
mvn clean package
```

### 3. Ejecutar
```
java -jar target/app.jar
```

---

## 🐳 Docker (opcional)

```
docker build -t evc-galatea .
docker run -p 8080:8080 evc-galatea
```

---

## 🌐 Despliegue en Render / Railway

Comandos usados:

```
mvn -DskipTests clean package
java -jar target/app.jar
```

---

## 📊 Performance Testing

Prueba realizada con Postman:

- 100 iteraciones  
- 10 usuarios en paralelo  

Resultados:

| Métrica | Valor |
|--------|--------|
| **TPS** | **2.18 transacciones/segundo** |
| **P90 /clue** | **205 ms** |
| **P90 /stats** | **212 ms** |

---

## 📝 Conclusiones

- El sistema es estable bajo carga moderada.
- P90 < 220ms incluso con concurrencia.
- TPS limitado por el plan Free de Render.
- Arquitectura clara, escalable y mantenible.

---

## 📬 Contacto

Proyecto realizado como parte de un challenge técnico.  
Mejoras, PRs e issues son bienvenidos.
