# 🚀 Guía de Deployment - HouseArmony

Esta guía te ayudará a desplegar HouseArmony usando Railway (backend), Neon (database) y Vercel (frontend).

## 📋 Tabla de Contenidos

1. [Prerequisitos](#prerequisitos)
2. [Base de Datos - Neon PostgreSQL](#1-base-de-datos---neon-postgresql)
3. [Backend - Railway](#2-backend---railway)
4. [Frontend - Vercel](#3-frontend---vercel)
5. [Configuración Post-Deployment](#4-configuración-post-deployment)
6. [Testing](#5-testing)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisitos

- ✅ Cuenta de GitHub (para conectar repositorios)
- ✅ Código subido a GitHub
- ✅ Cuentas gratuitas en:
  - [Neon](https://neon.tech) - Base de datos PostgreSQL
  - [Railway](https://railway.app) - Backend Spring Boot
  - [Vercel](https://vercel.com) - Frontend React

---

## 1. Base de Datos - Neon PostgreSQL

### Paso 1.1: Crear Proyecto en Neon

1. Ve a [console.neon.tech](https://console.neon.tech)
2. Click en **"Create a project"**
3. Configuración:
   - **Project name:** `housearmony`
   - **PostgreSQL version:** 16 (o la más reciente)
   - **Region:** Selecciona la más cercana a ti
   - **Database name:** `housearmony`

### Paso 1.2: Obtener Connection String

1. En el dashboard del proyecto, ve a **"Connection Details"**
2. Copia el **Connection String** completo
   ```
   postgresql://username:password@ep-xxx-xxx.region.aws.neon.tech/housearmony?sslmode=require
   ```
3. **Guárdalo** - lo necesitarás para Railway

---

## 2. Backend - Railway

### Paso 2.1: Crear Proyecto en Railway

1. Ve a [railway.app](https://railway.app)
2. Click en **"New Project"**
3. Selecciona **"Deploy from GitHub repo"**
4. Autoriza Railway a acceder a tu repositorio
5. Selecciona el repositorio **housearmony**

### Paso 2.2: Configurar el Servicio

1. Railway detectará automáticamente el `Dockerfile`
2. En **Settings** → **Root Directory**, configura:
   ```
   backend
   ```
3. En **Settings** → **Service Name**, nombra:
   ```
   housearmony-backend
   ```

### Paso 2.3: Configurar Variables de Entorno

En **Variables** tab, agrega las siguientes variables:

```bash
# Spring Profile
SPRING_PROFILES_ACTIVE=prod

# Database (tu Connection String de Neon)
DATABASE_URL=postgresql://username:password@ep-xxx.region.aws.neon.tech/housearmony?sslmode=require

# Puerto (Railway lo asigna automáticamente, pero puedes especificarlo)
PORT=8080

# CORS - IMPORTANTE: Actualiza después de desplegar en Vercel
CORS_ALLOWED_ORIGINS=https://localhost:5173
```

> ⚠️ **Nota:** Actualizarás `CORS_ALLOWED_ORIGINS` después de desplegar el frontend

### Paso 2.4: Deploy

1. Click en **"Deploy"**
2. Espera a que el build termine (puede tomar 5-10 minutos la primera vez)
3. Una vez completado, Railway te dará una URL como:
   ```
   https://housearmony-backend-production.up.railway.app
   ```
4. **Copia esta URL** - la necesitarás para el frontend

### Paso 2.5: Verificar el Deployment

1. Ve a `https://tu-app.railway.app/api/profiles` en tu navegador
2. Deberías ver un array JSON vacío `[]` (está funcionando!)

---

## 3. Frontend - Vercel

### Paso 3.1: Preparar Variables de Entorno

Antes de desplegar, crea un archivo `.env.production` en `/frontend/`:

```bash
VITE_API_BASE_URL=https://tu-app.railway.app
```

> ⚠️ Reemplaza con tu URL real de Railway del Paso 2.4

### Paso 3.2: Crear Proyecto en Vercel

1. Ve a [vercel.com](https://vercel.com)
2. Click en **"Add New..."** → **"Project"**
3. Importa tu repositorio de GitHub **housearmony**
4. Configuración:
   - **Framework Preset:** Vite
   - **Root Directory:** `frontend`
   - **Build Command:** `npm run build`
   - **Output Directory:** `dist`

### Paso 3.3: Configurar Variables de Entorno

En **Environment Variables**, agrega:

```bash
VITE_API_BASE_URL=https://tu-app.railway.app
```

> ⚠️ Usa tu URL real de Railway

### Paso 3.4: Deploy

1. Click en **"Deploy"**
2. Espera a que termine (2-5 minutos)
3. Vercel te dará URLs como:
   ```
   https://housearmony.vercel.app
   https://housearmony-git-main-tu-usuario.vercel.app
   ```

---

## 4. Configuración Post-Deployment

### Paso 4.1: Actualizar CORS en Railway

Ahora que tienes la URL de Vercel, actualiza las variables de entorno en Railway:

1. Ve a tu proyecto en Railway
2. En **Variables**, actualiza `CORS_ALLOWED_ORIGINS`:
   ```bash
   CORS_ALLOWED_ORIGINS=https://housearmony.vercel.app,https://housearmony-git-main-tu-usuario.vercel.app
   ```
3. El servicio se redesplegará automáticamente

### Paso 4.2: Configurar Custom Domain (Opcional)

**En Vercel:**
1. Ve a **Settings** → **Domains**
2. Agrega tu dominio personalizado
3. Sigue las instrucciones de DNS

**En Railway:**
1. Ve a **Settings** → **Networking**
2. Click en **"Generate Domain"** o agrega uno personalizado

---

## 5. Testing

### Verificar Backend

```bash
# Obtener perfiles (debería devolver [])
curl https://tu-app.railway.app/api/profiles

# Crear un perfil de prueba
curl -X POST https://tu-app.railway.app/api/profiles \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","icon":"👤"}'
```

### Verificar Frontend

1. Abre `https://tu-app.vercel.app`
2. Ve a **Settings** → **Profiles**
3. Crea un perfil nuevo
4. Verifica que se muestre correctamente

---

## Troubleshooting

### ❌ Error: "Failed to fetch profiles"

**Solución:**
- Verifica que `CORS_ALLOWED_ORIGINS` en Railway incluya tu dominio de Vercel
- Verifica que `VITE_API_BASE_URL` en Vercel apunte a Railway
- Revisa los logs en Railway: **Deployments** → Click en el deployment → **View Logs**

### ❌ Error: Backend no inicia en Railway

**Solución:**
- Verifica que `DATABASE_URL` esté correctamente configurada
- Asegúrate de que el Root Directory sea `backend`
- Revisa los logs de build en Railway

### ❌ Error: Base de datos no conecta

**Solución:**
- Verifica que la Connection String de Neon incluya `?sslmode=require`
- Asegúrate de que el proyecto de Neon esté activo
- Verifica que no haya espacios extra en `DATABASE_URL`

### ❌ Error 500 en API

**Solución:**
- Revisa los logs en Railway
- Verifica que todas las variables de entorno estén correctas
- Asegúrate de que `SPRING_PROFILES_ACTIVE=prod`

### 🔍 Ver Logs en Railway

1. Ve a tu proyecto en Railway
2. Click en **Deployments**
3. Click en el deployment activo
4. Click en **View Logs**

---

## 📊 Monitoreo

### Railway
- **Metrics:** CPU, RAM, Network usage
- **Logs:** Real-time application logs
- **Alerts:** Configura notificaciones

### Neon
- **Database metrics:** Connections, queries, storage
- **Branches:** Crea branches para testing

### Vercel
- **Analytics:** Page views, performance
- **Logs:** Function logs
- **Deployments:** History y rollbacks

---

## 🎉 ¡Listo!

Tu aplicación HouseArmony ahora está desplegada en:

- **Frontend:** `https://tu-app.vercel.app`
- **Backend:** `https://tu-app.railway.app`
- **Database:** Neon PostgreSQL

### Siguientes Pasos

1. ✅ Configura dominios personalizados
2. ✅ Implementa autenticación (Spring Security + JWT)
3. ✅ Agrega más features (Tasks, Bills, etc.)
4. ✅ Configura CI/CD para deploys automáticos
5. ✅ Implementa monitoreo y alertas

---

## 📞 Soporte

- **Railway Docs:** https://docs.railway.app
- **Neon Docs:** https://neon.tech/docs
- **Vercel Docs:** https://vercel.com/docs
- **Spring Boot Docs:** https://spring.io/projects/spring-boot

---

## 💰 Límites del Free Tier

| Servicio | Límite Free Tier |
|----------|------------------|
| **Neon** | 0.5 GB storage, 512 MB RAM |
| **Railway** | $5/mes de crédito (~500 horas) |
| **Vercel** | 100 GB bandwidth, ilimitados deploys |

> **Tip:** Con estos límites puedes correr tu app sin costo durante desarrollo/demo. Para producción, considera upgradear según necesidad.
