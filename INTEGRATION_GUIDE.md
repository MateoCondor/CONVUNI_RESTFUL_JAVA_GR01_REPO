# Integración con WS_CONVUNI_RESTFUL_JAVA_GR01

## Configuración General

Los clientes (CLIWEB y CLIMOV) se conectan al servidor REST de Java en `WS_CONVUNI_RESTFUL_JAVA_GR01`.

### URL Base del Servidor

```
http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

### Configuración por Entorno

#### Desarrollo Local
```
EXPO_PUBLIC_API_BASE_URL=http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

#### Android Emulator
```
EXPO_PUBLIC_API_BASE_URL=http://10.0.2.2:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

#### Dispositivo Físico / Red Local
Reemplaza `<tu-ip-maquina>` con tu dirección IP:
```
EXPO_PUBLIC_API_BASE_URL=http://<tu-ip-maquina>:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

Ejemplo:
```
EXPO_PUBLIC_API_BASE_URL=http://192.168.100.158:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

## Endpoints Disponibles

### 1. Login
**POST** `/login`

**Request:**
```json
{
  "username": "MONSTER",
  "password": "MONSTER9"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful"
}
```

### 2. Convertir Longitud
**POST** `/convertLength`

**Request:**
```json
{
  "value": 100,
  "fromUnit": "m",
  "toUnit": "km"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Conversion successful",
  "category": "length",
  "inputValue": 100,
  "fromUnit": "m",
  "toUnit": "km",
  "convertedValue": 0.1
}
```

### 3. Convertir Masa
**POST** `/convertMass`

**Request:**
```json
{
  "value": 1000,
  "fromUnit": "g",
  "toUnit": "kg"
}
```

**Response:** Similar a convertLength

### 4. Convertir Temperatura
**POST** `/convertTemperature`

**Request:**
```json
{
  "value": 32,
  "fromUnit": "F",
  "toUnit": "C"
}
```

**Response:** Similar a convertLength

### 5. Health Check
**GET** `/healthCheck`

**Response:**
```
REST conversion service is running.
```

## Credenciales por Defecto

```
Usuario: MONSTER
Contraseña: MONSTER9
```

## Cambios Realizados en los Clientes

### Archivo: `services/conversion-api.ts`

#### Cambios principales:
1. **URL del servidor actualizada:**
   - ❌ Antes: `WS_CONVUNI_BRIDGE_REST_JAVA_GR01`
   - ✅ Ahora: `WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion`

2. **Endpoints ajustados:**
   - ❌ Antes: `/api/login` → ✅ Ahora: `/login`
   - ❌ Antes: `/api/convert/{category}` → ✅ Ahora: `/convertLength`, `/convertMass`, `/convertTemperature`

3. **Tipos TypeScript actualizado:**
   - `ConversionResponse` ahora coincide con la estructura real del servidor
   - Campos: `success`, `message`, `category`, `inputValue`, `fromUnit`, `toUnit`, `convertedValue`

## Variables de Entorno

Crea un archivo `.env.local` en la raíz de cada cliente (CLIWEB y CLIMOV):

```bash
EXPO_PUBLIC_API_BASE_URL=http://tu-servidor:8080/WS_CONVUNI_RESTFUL_JAVA_GR01/resources/conversion
```

**Nota:** Las variables de entorno en Expo deben comenzar con `EXPO_PUBLIC_` para ser accesibles desde el código frontend.

## Pruebas

### Con Postman
1. Abre el archivo: `WS_CONVUNI_RESTFUL_JAVA_GR01/postman/WS_CONVUNI_RESTFUL_JAVA_GR01.postman_collection.json`
2. Importa la colección en Postman
3. Usa el entorno: `local.postman_environment.json`

### Con los Clientes
1. Asegúrate de que el servidor Java esté ejecutándose
2. Configura la URL correcta en `.env.local` (o deja la predeterminada si es localhost)
3. Inicia el cliente: `npx expo start`
4. Prueba login y conversiones en la interfaz

## Troubleshooting

### Error: "Unable to connect to the server"
- Verifica que el servidor Java esté ejecutándose
- Verifica que la URL en `conversion-api.ts` sea correcta
- Revisa la consola del navegador (F12) para más detalles

### Error: "CORS issues"
- El servidor podría no tener CORS habilitado
- Asegúrate de que el servidor acepte solicitudes desde tu origen (localhost:3000, 192.168.100.x, etc.)

### Error: "Login failed"
- Verifica que uses las credenciales correctas: `MONSTER` / `MONSTER9`
- Asegúrate de que estés enviando las credenciales al endpoint `/login` (no `/api/login`)

### Error: "Conversion not working"
- Verifica que hayas hecho login primero
- Comprueba que estés usando el endpoint correcto (`/convertLength`, `/convertMass`, `/convertTemperature`)
- Asegúrate de que los parámetros se envíen correctamente

