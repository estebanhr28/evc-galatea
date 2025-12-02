# EVC Galatea — Clue Detector (Java / Spring Boot)

Proyecto que implementa la detección de pistas (secuencias de 4 letras iguales en línea) en manuscritos.

## Qué incluye
- Implementación de la función `boolean containsArtifactClue(String[] manuscript)` en Java.
- API REST con Spring Boot:
  - `POST /clue/` acepta JSON `{ "manuscript": ["RTHGQW","XRLORE", ...] }`.
    - Devuelve HTTP 200 si se encontró pista; HTTP 403 si no.
  - `GET /stats` devuelve estadísticas `{ "count_clue_found": X, "count_no_clue": Y, "ratio": R }`.
- Persistencia en H2 para almacenar manuscritos únicos procesados.
- Tests unitarios para la utilidad de detección.

## Ejecutar localmente
Se requiere JDK 17 y Maven.

1. Construir:
   ```
   mvn -f evc-galatea/pom.xml clean package
   ```

2. Ejecutar:
   ```
   mvn -f evc-galatea spring-boot:run
   ```
   La API se expondrá por defecto en `http://localhost:8080`.

## Ejemplos
Request:
```
POST http://localhost:8080/clue/
Content-Type: application/json

{ "manuscript": ["RTHGQW","XRLORE","NARURR","REVRAL","EGSILE","BRINDS"] }
```

Respuesta: HTTP 200 (porque hay 4 'R' en diagonal)

Estadísticas:
```
GET http://localhost:8080/stats
```

## Notas
- La detección es insensible a mayúsculas/minúsculas.
- Manuscritos iguales (mismas líneas en el mismo orden) se registran solo una vez.
- Para producción y alta carga habría que usar una base de datos externa, cache distribuido y despliegue escalado (Kubernetes, autoscaling, etc.). Esto es una implementación funcional de ejemplo.

