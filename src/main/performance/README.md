# Performance Test — EVC Galatea API

Este documento presenta los resultados de la prueba de rendimiento realizada sobre la API:

https://evc-galatea.onrender.com

Se utilizó Postman Collection Runner ejecutando peticiones concurrentes para medir:

- Transacciones por segundo (TPS)
- Percentil 90 (P90) del tiempo de respuesta
- Comportamiento bajo carga

## Configuración de la prueba

| Parámetro | Valor |
|----------|-------|
| Herramienta | Postman |
| Iteraciones | 100 |
| Concurrencia | 10 usuarios |
| Delay | 0 ms |
| Endpoints probados | /clue (POST), /stats (GET) |
| Archivo de resultados | EVC Galatea.postman_test_run.json |

## Resultados principales

### Transacciones por segundo (TPS)

TPS = 2.18 transacciones/segundo

### Percentiles de tiempo de respuesta (P90)

| Endpoint | P90 | Promedio | Máximo |
|----------|------|----------|--------|
| POST /clue | ≈ 205 ms | ~190 ms | 6396 ms |
| GET /stats | ≈ 212 ms | ~187 ms | 982 ms |

## Conclusiones

- La API mantiene tiempos estables entre ~180–220 ms.
- El P90 está por debajo de 220 ms.
- El TPS está limitado por el plan gratuito de Render.
- No ocurrieron errores durante la prueba.

